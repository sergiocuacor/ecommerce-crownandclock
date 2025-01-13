<template>
    
    <select name="brandSelector" id="brandSelector" class="form-select" @change="handleChange">
        <option disabled selected>{{ 'Selecciona una marca' }}</option>
        <option v-for="brand in brands" :key="brand.id" :value="brand.id">{{ brand.name }}</option>
    </select>
    
</template>

<script setup>

    import { ref, onMounted } from 'vue';
    import apiClient from '../services/api.js';

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

    const handleChange = (event) => {
        const selectedBrandId = event.target.value;
        emit('brandSelected', selectedBrandId);
    };

    onMounted(async () => {
        loading.value = true;
        await Promise.all([fetchBrands()]);
        loading.value = false;
    });

</script>
