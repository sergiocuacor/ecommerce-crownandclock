<template>
    
    <select name="categorySelector" id="categorySelector" class="form-select" @change="handleChange">
        
        <!-- COMPONENT -->
        <option v-if="!loading && !error" disabled selected>{{ 'Selecciona una categoría' }}</option>
        <option v-if="!loading && !error" v-for="category in categories" :key="category.id" :value="category.id">{{ category.name }}</option>
        
        <!-- LOADING -->
        <option disabled selected v-if="loading">{{ 'Cargando...' }}</option>

        <!-- ERROR -->
        <option disabled selected v-if="error">{{ 'Error' }}</option>

    </select>
    
</template>

<script setup>

    import { ref, onMounted } from 'vue';
    import apiClient from '../../services/api.js';

    const categorys = ref([]);
    const loading = ref(true);
    const error = ref(null);

    const fetchCategories = async () => {
        try {
            const response = await apiClient.getCategories();
            categorys.value = response.data;
        } catch (err) {
            error.value = 'Error al cargar las marcas';
        }
    };

    const handleChange = (event) => {
        const selectedCategoryId = event.target.value;
        emit('categorySelected', selectedCategoryId);
    };

    onMounted(async () => {
        loading.value = true;
        await Promise.all([fetchCategories()]);
        loading.value = false;
    });

</script>