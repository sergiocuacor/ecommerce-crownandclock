<template>
    
    <label for="brandSelector" class="tw-block tw-text-md tw-font-medium tw-text-gray-700 tw-py-2">{{ 'Brands: ' }}</label>
    <select name="brandSelector" id="brandSelector" class="tw-block tw-w-full tw-border tw-border-gray-300 tw-rounded-md tw-py-2 tw-px-3 tw-shadow-md focus:tw-border-gray-400 focus:tw-ring-gray-400 tw-text-md" v-model="selectedBrand" @change="handleChange">
        
        <!-- SUCCESS -->
        <option v-if="!loading && !error" value="" selected>{{ '* select a brand *' }}</option>
        <option v-if="!loading && !error" v-for="brand in brands" :key="brand.id" :value="brand.id" :disabled="brand == selectedBrand">{{ brand.name }}</option>
        
        <!-- LOADING -->
        <option disabled selected v-if="loading">{{ 'Loading...' }}</option>

        <!-- ERROR -->
        <option disabled selected v-if="error">{{ 'Error' }}</option>

    </select>
    
</template>

<script setup>

    import { ref, onMounted } from 'vue';
    import { useRoute, useRouter } from 'vue-router';    
    import apiClient from '../../services/api.js';
    
    const route = useRoute();
    const router = useRouter();

    const props = defineProps({
        selectedBrand: {
            default: '',
            required: true,
        },
    });

    const brands = ref([]);
    const selectedBrand = ref(props.selectedBrand);
    const loading = ref(true);
    const error = ref(null);

    const fetchBrands = async () => {

        loading.value = true;

        const response = await apiClient.getBrands();

        if (response.success) {

            brands.value = response.data;

            const existsBrand = response.data.find(brand => brand.id == selectedBrand.value);

            if (!existsBrand) {
                selectedBrand.value = '';
            }

        } else {
            selectedBrand.value = '';
            error.value = response.error;
        }

        loading.value = false;

    };

    const handleChange = () => {
        
        const selectedBrandMatch = brands.value.find(brand => brand.id == selectedBrand.value);
        const newQuery = { ...route.query };
        
        newQuery.page = 1;

        if (selectedBrandMatch) {
            newQuery.brand = selectedBrandMatch.id;
        } else {
            delete newQuery.brand;
        }        
        
        router.push({ path: route.path, query: newQuery });

    };    

    onMounted(fetchBrands);

</script>
