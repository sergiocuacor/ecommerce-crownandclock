<template>

    <span v-if="!loading" class="badge text-bg-primary">
        {{ 'Marca: ' + selectedBrandName }}
    </span>

</template>

<script setup>

    import { ref, onMounted, watch } from 'vue';  
    import apiClient from '../../services/api.js';

    const props = defineProps({
        selectedBrand: {
            default: '',
            required: true,
        },
    });

    const selectedBrand = ref(props.selectedBrand);
    const selectedBrandName = ref('TODAS');    
    const loading = ref(true);
    const error = ref(null);

    const fetchBrandName = async () => {

        loading.value = true;

        if(selectedBrand.value != '') {

            const response = await apiClient.getBrandById(selectedBrand.value);        

            if (response.success) {
                
                selectedBrandName.value = response.data.name;
                error.value = null;

            } else {

                selectedBrandName.value = 'Desconocida';                
                error.value = response.error;

            }

        } else {
            
            selectedBrandName.value = 'TODAS';

        }

        loading.value = false;

    };    

    watch(() => props.selectedBrand, (newVal) => {

        selectedBrand.value = newVal;
        fetchBrandName();

    });
    
    onMounted(fetchBrandName);

</script>
