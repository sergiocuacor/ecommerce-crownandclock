<template>

    <!-- SUCCESS -->
    <section v-if="!loading && !error" class="tw-grid tw-grid-cols-1 sm:tw-grid-cols-2 md:tw-grid-cols-3 lg:tw-grid-cols-4 xl:tw-grid-cols-4 tw-gap-2">
        <ItemComponent v-for="item in items" :key="item.id" :itemMask="item.id"/>        
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
        <div class="tw-grid tw-grid-cols-1 sm:tw-grid-cols-2 md:tw-grid-cols-3 lg:tw-grid-cols-4 xl:tw-grid-cols-6 tw-gap-2">
            <ElementComponent v-if="error" v-for="index in 24" :key="index">
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
                <div class="tw-text-xs md:tw-text-2xl">{{ 'Error al cargar los productos' }}</div>
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