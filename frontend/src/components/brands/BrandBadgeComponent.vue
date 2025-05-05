<template>

    <span v-if="!loading" class="tw-inline-block tw-bg-black tw-text-white tw-py-0.5 tw-px-3 tw-rounded-full tw-font-semibold tw-text-sm">
        {{ 'Brand: ' + selectedBrandName }}
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
    const selectedBrandName = ref('ALL');    
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

                selectedBrandName.value = 'Unknown';                
                error.value = response.error;

            }

        } else {
            
            selectedBrandName.value = 'ALL';

        }

        loading.value = false;

    };    

    watch(() => props.selectedBrand, (newVal) => {

        selectedBrand.value = newVal;
        fetchBrandName();

    });
    
    onMounted(fetchBrandName);

</script>
