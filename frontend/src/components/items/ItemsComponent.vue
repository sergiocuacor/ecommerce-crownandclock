<template>

    <article class="row py-3 g-2">
        
        <div class="col-12 col-md-6 col-lg-4">

            <BrandSelectorComponent :selectedBrand="selectedBrand"/>

        </div>

        <div class="col-12 col-md-6 col-lg-4">

            <SortSelectorComponent :sortBy="sortBy"/>

        </div>

        <div class="col-12 col-md-6 col-lg-4">

            <SizeSelectorComponent :pageSize="pageSize"/>

        </div>

        <div class="col-12 tw-pt-3 tw-space-x-2">

            <BrandBadgeComponent :selectedBrand="selectedBrand"/>
            
            <SortBadgeComponent :sortBy="sortBy"/>

            <SizeBadgeComponent :pageSize="pageSize"/>

        </div>

    </article> 

    <ItemPaginationComponent :currentPage="currentPage" :pageSize="pageSize" :totalElements="totalElements"/>

    <!-- SUCCESS -->
    <section v-if="!loading && !error">
        <div class="tw-grid tw-grid-cols-1 sm:tw-grid-cols-2 md:tw-grid-cols-3 lg:tw-grid-cols-4 xl:tw-grid-cols-4 tw-gap-2">
            <ItemComponent v-for="item, index in items.content" :key="index" :item="item"/>
        </div>
    </section>    
    
    <!-- LOADING -->
    <section v-if="loading" class="tw-aspect-square lg:tw-aspect-video tw-relative">
        <div class="tw-absolute tw-top-1/2 tw-left-1/2 tw-transform tw--translate-x-1/2 tw--translate-y-1/2">
            <div class="spinner-grow tw-text-sky-400/100">
                <span class="visually-hidden">{{ 'Loading...' }}</span>
            </div>
            <div class="spinner-grow tw-text-sky-400/100 tw-mx-1.5">
                <span class="visually-hidden">{{ 'Loading...' }}</span>
            </div>
            <div class="spinner-grow tw-text-sky-400/100">
                <span class="visually-hidden">{{ 'Loading...' }}</span>
            </div>      
        </div>
    </section>

    <!-- ERROR -->
    <section v-if="error" class="tw-relative">
        <div class="tw-grid tw-grid-cols-1 sm:tw-grid-cols-2 md:tw-grid-cols-3 lg:tw-grid-cols-4 xl:tw-grid-cols-4 tw-gap-2">
            <ElementComponent v-for="index in 12" :key="index">
                <template #middle-center>
                    <i class="bi bi-exclamation-diamond-fill tw-text-red-500 fs-1"></i>
                </template>
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
        <div class="tw-absolute tw-top-1/2 tw-left-1/2 tw-transform tw--translate-x-1/2 tw--translate-y-1/2 tw-bg-gray-200 tw-p-2.5 border border-3 rounded">
            <div class="flex flex-col tw-text-center tw-font-semibold tw-rounded-lg">
                <div class="tw-text-xl md:tw-text-6xl"><i class="bi bi-exclamation-circle-fill tw-text-red-500"></i></div>
                <div class="tw-text-xs md:tw-text-xl">{{ 'Error loading products' }}</div>
            </div>     
        </div>
    </section>
    
</template>

<script setup>

    import { ref, onMounted, watch } from 'vue';
    import { useRoute } from 'vue-router';
    import apiClient from '../../services/api.js';

    const route = useRoute();

    const items = ref([]);
    const loading = ref(true);
    const error = ref(null);
    
    const currentPage = ref(Number(route.query.page) || 1);
    const pageSize = ref(Number(route.query.size) || 12);
    const totalElements = ref(0);
    const sortBy = ref(route.query.sort || 'name,asc');
    const selectedBrand = ref(Number(route.query.brand) || '');
    const selectedCategory = ref(route.query.category || '');

    const fetchItems = async () => {

        loading.value = true;
        error.value = null;

        const response = await apiClient.getItemsPageable(currentPage.value - 1, pageSize.value, sortBy.value, selectedBrand.value);
        
        if (response.success) {
            items.value = response.data;
            totalElements.value = items.value.totalElements;
        } else {
            error.value = response.error;
        }

        loading.value = false;

    };

    watch(
        () => route.query,
        (newQuery) => {
            currentPage.value = Number(newQuery.page) || 1;
            pageSize.value = Number(newQuery.size) || 12;
            sortBy.value = newQuery.sort || 'name,asc';
            selectedBrand.value = Number(newQuery.brand) || '';
            selectedCategory.value = newQuery.category || '';

            fetchItems();
        },
        { deep: true }
    );

    onMounted(fetchItems);

</script>