<template>

    <!-- SUCCESS -->
    <ElementComponent v-if="!loading && !error" :toRoute="{ name: 'brand', params: { mask: brand.id } }" :imgSrc="brand.image" :imgAlt="`${brand.id}`">
        <template #footer>
            <div class="col-12">
                {{ brand.name }};
            </div>
        </template>        
    </ElementComponent>

    <!-- LOADING -->
    <ElementComponent v-if="loading">
        <template #middle-center>
            <div class="spinner-border">
                <span class="visually-hidden">{{ 'Loading...' }}</span>
            </div>
        </template>
        <template #footer>
            <div class="col-12 text-truncate placeholder-glow">
                <span class="placeholder">{{ '??????????' }}</span>
            </div>
        </template>      
    </ElementComponent>

    <!-- ERROR -->
    <ElementComponent v-if="error">
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

</template>

<script setup>

    import { ref, onMounted } from 'vue';
    import apiClient from '../../services/api.js';

    const props = defineProps({
        brandMask: {
            type: Number,
            required: true
        }
    });

    const brand = ref([]);
    const loading = ref(true);
    const error = ref(null);

    const fetchBrand = async () => {
        try {
            const response = await apiClient.getBrand(props.brandMask);
            brand.value = response.data;
        } catch (err) {
            error.value = 'Error al cargar el producto';
        }
    };

    onMounted(async () => {
        loading.value = true;
        await Promise.all([fetchBrand()]);
        loading.value = false;
    });

</script>
