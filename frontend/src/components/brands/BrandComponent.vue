<template>

    <!-- COMPONENT -->
    <article v-if="!loading && !error" class="border border-3 rounded"> 
        <div class="position-relative bg-white">
            <router-link :to="{ name: 'brand' }" class="ratio ratio-1x1">
                <img class="object-fit-contain p-4" :src="brand.image" :alt="brand.id" loading="lazy" />
            </router-link>

            <!-- Top-left position -->
            <div class="position-absolute top-0 start-0">

            </div>

            <!-- Top-middle position -->
            <div class="position-absolute top-0 start-50 translate-middle-x">

            </div>

            <!-- Top-right position -->
            <div class="position-absolute top-0 end-0">

            </div>

            <!-- Middle-left position -->
            <div class="position-absolute top-50 start-0 translate-middle-y">

            </div>

            <!-- Middle-center position -->
            <div class="position-absolute top-50 start-50 translate-middle">

            </div>

            <!-- Middle-right position -->
            <div class="position-absolute top-50 end-0 translate-middle-y">

            </div>

            <!-- Bottom-left position -->
            <div class="position-absolute bottom-0 start-0">

            </div>

            <!-- Bottom-middle position -->
            <div class="position-absolute bottom-0 start-50 translate-middle-x">

            </div>

            <!-- Bottom-right position -->
            <div class="position-absolute bottom-0 end-0">

            </div>

            <!-- Footer Section -->
            <div class="d-block bg-primary-subtle">
                <div class="row p-2">
                    <div class="col-12">
                        {{ brand.name }};
                    </div>
                </div>
            </div>
        </div>
    </article>

    <!-- LOADING -->
    <article v-if="loading" class="border border-3 rounded">  
        <div class="position-relative bg-white">
            <div class="ratio ratio-1x1">

            </div>

            <!-- Middle-center position -->
            <div class="position-absolute top-50 start-50 translate-middle">
                <div class="spinner-border">
                    <span class="visually-hidden">{{ 'Loading...' }}</span>
                </div>
            </div>

            <!-- Footer Section -->
            <div class="d-block bg-primary-subtle">
                <div class="row p-2">
                    <div class="col-12 text-truncate placeholder-glow">
                        <span class="placeholder">{{ '??????????' }}</span>
                    </div>
                </div>
            </div>
        </div>
    </article>

    <!-- ERROR -->
    <article v-if="error">{{ error }}</article>

</template>

<script setup>

    import { ref, onMounted } from 'vue';
    import apiClient from '../services/api.js';

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
