<template>
    <section v-if="!loading && !error" class="tw-grid tw-grid-cols-1 sm:tw-grid-cols-2 md:tw-grid-cols-3 lg:tw-grid-cols-4 xl:tw-grid-cols-5 tw-gap-2">
        <ItemComponent v-for="item in items" :key="item.id" :itemMask="item.id"/>        
    </section>
    
    <section v-if="loading" class="tw-aspect-square lg:tw-aspect-video tw-relative">
        <div class="tw-absolute tw-top-1/2 tw-left-1/2 tw-transform tw--translate-x-1/2 tw--translate-y-1/2">
            <div class="spinner-grow tw-text-sky-400/100">
                <span class="visually-hidden">Loading...</span>
            </div>
            <div class="spinner-grow tw-text-sky-400/100 tw-mx-1.5">
                <span class="visually-hidden">Loading...</span>
            </div>
            <div class="spinner-grow tw-text-sky-400/100">
                <span class="visually-hidden">Loading...</span>
            </div>      
        </div>
    </section>

    <section v-if="error" class="tw-aspect-square lg:tw-aspect-video tw-relative">
        <div class="tw-absolute tw-top-1/2 tw-left-1/2 tw-transform tw--translate-x-1/2 tw--translate-y-1/2">
            <div class="">
                Error
            </div>     
        </div>
    </section>
    
</template>

<script setup>

    import { ref, onMounted } from 'vue';
    import apiClient from '../../services/api.js';

    const items = ref([]);
    const loading = ref(true);
    const error = ref(null);

    const fetchItems = async () => {
        try {
            const response = await apiClient.getItems();
            items.value = response.data;
        } catch (err) {
            error.value = 'Error al cargar los productos';
        }
    };

    onMounted(async () => {
        loading.value = true;
        await Promise.all([fetchItems()]);
        loading.value = false;
    });

</script>