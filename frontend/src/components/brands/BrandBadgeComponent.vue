<template>

    <span v-if="selectedBrand && !loading && !error" class="badge text-bg-primary">
        {{ 'Marca: ' + selectedBrandName }}
    </span>

</template>

<script setup>

    import { ref, onMounted } from 'vue';  
    import apiClient from '../../services/api.js';

    const props = defineProps({
        selectedBrand: {
            type: Number,
            default: 0,
            required: true,
        },
    });

    const selectedBrand = ref(props.selectedBrand);
    const selectedBrandName = ref('Desconocida');    
    const loading = ref(true);
    const error = ref(null);

    const fetchBrandName = async () => {

        loading.value = true;

        const response = await apiClient.getBrands();        

        if (response.success) {

            const selectedBrandInfo = response.data.find(brand => brand.id == selectedBrand.value);

            if (selectedBrandInfo) {
                selectedBrandName.value = selectedBrandInfo.name;
            } else {
                selectedBrandName.value = 'Desconocida';
            }

        } else {

            selectedBrandName.value = 'Desconocida';
            error.value = response.error;

        }

        loading.value = false;

    };

    onMounted(fetchBrandName)

</script>