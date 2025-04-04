<template>
    
    <label for="brandSelector" class="form-label">{{ 'Marcas: ' }}</label>
    <select name="brandSelector" id="brandSelector" class="form-select" v-model="selectedBrand" @change="handleChange">
        
        <!-- SUCCESS -->
        <option v-if="!loading && !error" value="0" selected>{{ '* selecciona una marca *' }}</option>
        <option v-if="!loading && !error" v-for="brand in brands" :key="brand.id" :value="brand.id" :disabled="brand == selectedBrand">{{ brand.name }}</option>
        
        <!-- LOADING -->
        <option disabled selected v-if="loading">{{ 'Cargando...' }}</option>

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
            type: Number,
            default: 0,
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
                selectedBrand.value = 0;
            }

        } else {
            selectedBrand.value = 0;
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
