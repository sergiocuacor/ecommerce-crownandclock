<template>

    <!-- SUCCESS -->
    <section v-if="!loading && !error" class="tw-grid tw-grid-cols-1 sm:tw-grid-cols-2 md:tw-grid-cols-3 lg:tw-grid-cols-4 xl:tw-grid-cols-5 tw-gap-2">
        <BrandComponent v-for="brand in brands" :key="brand.id" :brandMask="brand.id"/>        
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
    <section v-if="error" class="tw-relative tw-h-screen tw-overflow-hidden">
        <div class="tw-absolute tw-top-1/2 tw-left-1/2 tw-transform tw--translate-x-1/2 tw--translate-y-1/2">
            <div class="flex flex-col tw-text-center tw-font-semibold tw-rounded-lg">
                <div class="tw-text-xl md:tw-text-6xl"><i class="bi bi-exclamation-circle-fill tw-text-red-500"></i></div>
                <div class="tw-text-xs md:tw-text-2xl">{{ 'Error al cargar las marcas' }}</div>
            </div>     
        </div>
    </section>
    
</template>

<script setup>

    import { ref, onMounted } from 'vue';
    import apiClient from '../../services/api.js';

    const brands = ref([]);
    const loading = ref(true);
    const error = ref(null);

    const fetchBrands = async () => {
        try {
            const response = await apiClient.getBrands();
            brands.value = response.data;
        } catch (err) {
            error.value = 'Error al cargar las marcas';
        }
    };

    onMounted(async () => {
        loading.value = true;
        await Promise.all([fetchBrands()]);
        loading.value = false;
    });

</script>