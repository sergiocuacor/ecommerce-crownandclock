<template>

    <section class="container tw-overflow-y-auto tw-h-[80vh]" ref="containerItems" @scroll="onScroll">

        <!-- SUCCESS -->
        <div class="tw-grid tw-grid-cols-1 sm:tw-grid-cols-2 md:tw-grid-cols-3 lg:tw-grid-cols-4 xl:tw-grid-cols-4 tw-gap-2 tw-p-2">
            <ItemComponent v-for="item in visibleItems" :key="item.id" :item="item" />
        </div>
        
        <!-- LOADING -->
        <SandClockLoaderComponent v-show="loading" />        
        <div v-show="loading" class="tw-grid tw-grid-cols-1 sm:tw-grid-cols-2 md:tw-grid-cols-3 lg:tw-grid-cols-4 xl:tw-grid-cols-4 tw-gap-2 tw-p-2">            
            <ElementComponent v-for="index in pageSize" :key="index" :imgSrc="`no_image.png`" :imgAlt="`loading`">
                <template #footer>
                    <div class="col text-truncate placeholder-glow">
                        <span class="placeholder">{{ 'Disponibles: ?' }}</span>
                    </div>
                    <div class="col-12 col-md-auto placeholder-glow">
                        <span class="placeholder">{{ '??????' }}</span>
                    </div>
                </template>      
            </ElementComponent>  
        </div>

    </section>

</template>  

<script setup>

    import { ref, onMounted, watch } from 'vue';
    import { useRoute } from 'vue-router';
    import apiClient from '../../services/api.js';

    const route = useRoute();

    const items = ref([]);
    const visibleItems = ref([]);
    const loading = ref(false);
    const error = ref(null);

    const currentPage = ref(Number(route.query.page) || 1);
    const pageSize = ref(Number(route.query.size) || 12);
    const totalElements = ref(0);
    const sortBy = ref(route.query.sort || 'name,asc');
    const selectedBrand = ref(route.query.brand || 0);
    const selectedCategory = ref(route.query.category || '');

    const containerItems = ref(null);
    const isFetching = ref(false);

    const fetchItems = async () => {

        if (isFetching.value) return;

        loading.value = true;
        isFetching.value = true;

        const response = await apiClient.getItemsPageable(currentPage.value - 1, pageSize.value, sortBy.value);

        setTimeout(() => {

        if (response.success) {
            const newItems = response.data.content || [];
            visibleItems.value.push(...newItems);
            totalElements.value = response.data.totalElements;

        } else {

            error.value = response.error;         
            
        }
        
            loading.value = false;
            isFetching.value = false;
    
        }, 1500);

    };

    const onScroll = () => {
    const el = containerItems.value;
    if (!el) return;

    const bottomReached = el.scrollTop + el.clientHeight >= el.scrollHeight - 10;
    const moreToLoad = visibleItems.value.length < totalElements.value;

    if (bottomReached && moreToLoad && !isFetching.value) {
        currentPage.value += 1;
        fetchItems();
    }
    };

    watch(
    () => route.query,
    (newQuery) => {
        currentPage.value = Number(newQuery.page) || 1;
        pageSize.value = Number(newQuery.size) || 12;
        sortBy.value = newQuery.sort || 'name,asc';
        selectedBrand.value = newQuery.brand || 0;
        selectedCategory.value = newQuery.category || '';

        visibleItems.value = [];  // Reiniciamos para nueva búsqueda
        fetchItems();
    },
    { deep: true }
    );

    onMounted(() => {

        fetchItems();

    });

</script>