<template>

    <!-- COMPONENT -->
    <section v-if="!loading && !error" class="tw-grid tw-grid-cols-1 sm:tw-grid-cols-2 md:tw-grid-cols-3 lg:tw-grid-cols-4 xl:tw-grid-cols-5 tw-gap-2">
        <CategoryComponent v-for="category in categories" :key="category.id" :categoryMask="category.id"/>        
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
    <section v-if="error" class="tw-aspect-square lg:tw-aspect-video tw-relative">
        <div class="tw-absolute tw-top-1/2 tw-left-1/2 tw-transform tw--translate-x-1/2 tw--translate-y-1/2">
            <div class="">
                {{ 'Error' }}
            </div>     
        </div>
    </section>
    
</template>

<script setup>

    import { ref, onMounted } from 'vue';
    import apiClient from '../../services/api.js';

    const categories = ref([]);
    const loading = ref(true);
    const error = ref(null);

    const fetchCategories = async () => {
        try {
            const response = await apiClient.getCategories();
            categories.value = response.data;
        } catch (err) {
            error.value = 'Error al cargar las categorías';
        }
    };

    onMounted(async () => {
        loading.value = true;
        await Promise.all([fetchCategories()]);
        loading.value = false;
    });

</script>