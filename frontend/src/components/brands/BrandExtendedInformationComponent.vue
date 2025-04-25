<template>

    
    <div class="row tw-font-serif">

        <!-- SUCCESS -->
        <div v-if="!loading && !error" class="col-12 tw-bg-gray-200 tw-text-black tw-border tw-border-gray-300 tw-rounded tw-p-4">
            <div class="tw-font-extrabold tw-text-xl">
                {{ brand.name }}
            </div>
            <div class="tw-font-medium tw-text-md">
                {{ brand.description }}
            </div>
            <div class="tw-font-semibold tw-text-xs tw-mt-1">
                {{ 'Current model stock: '  }}<strong>{{ brand.numberOfProducts }}</strong>
            </div>         
        </div>

        <!-- LOADING -->
        <div v-if="loading" class="col-12 tw-bg-gray-200 tw-text-gray-500 tw-border tw-border-gray-300 tw-rounded tw-p-4">          
            <div class="d-flex align-items-center tw-font-extrabold tw-text-xl">
                {{ 'Name' }}
                <span class="spinner-border spinner-border-sm ms-2" role="status" aria-hidden="true"></span>                    
            </div>
            <div class="d-flex align-items-center tw-font-medium tw-text-md">                    
                {{ 'Description' }}
                <span class="spinner-border spinner-border-sm ms-2" role="status" aria-hidden="true"></span>
            </div>
            <div class="d-flex align-items-center tw-font-semibold tw-text-xs tw-mt-1">                
                {{ 'Models stock' }}
                <span class="spinner-border spinner-border-sm ms-2" role="status" aria-hidden="true"></span>
            </div>
        </div>

        <!-- ERROR -->
        <div v-if="error" class="col-12 tw-bg-red-100 tw-text-red-800 tw-border tw-border-red-300 tw-rounded tw-p-4">
            <div class="tw-font-extrabold tw-text-xl">
                {{ 'An error occurred' }}
            </div>
            <div class="tw-font-medium tw-text-md">
                {{ 'Unable to fetch brand details. Please try again later.' }}
            </div>
        </div>         
    
    </div>

    <BrandItemsComponent :brandId="brandId" v-if="!loading && !error"/>

</template>

<script setup>

    import { computed, defineProps, onMounted, ref } from 'vue';
    import { useRoute } from 'vue-router';
    import apiClient from '../../services/api.js';

    const apiBaseURL = ref(apiClient.getApiBaseURL());
    const route = useRoute();
    const brandId = computed(() => route.params.mask);    

    const brand = ref({});
    const loading = ref(false);
    const error = ref(null); 

    const fetchBrand = async () => {

        loading.value = true;

        const response = await apiClient.getBrandById(brandId.value);

        setTimeout(() => {

            if (response.success) {

                error.value = null;
                brand.value = response.data;                      

            } else {        
            
                error.value = response.error;

            }
            
            loading.value = false;

        }, 1000);

    };    

    onMounted(fetchBrand);

</script>