<template>
    <article v-if="!loading && !error" class="border border-3 rounded"> 
        <div class="position-relative bg-white">
            <router-link :to="`/products/${item.id}`" class="ratio ratio-1x1">
                <img class="object-fit-contain p-4" :src="item.image" :alt="item.id" loading="lazy" />
            </router-link>

            <!-- Top-left position -->
            <div class="position-absolute top-0 start-0">
                <RatingComponent v-if="item.rating" :itemRating="item.rating" />
            </div>

            <!-- Top-middle position -->
            <div class="position-absolute top-0 start-50 translate-middle-x">

            </div>

            <!-- Top-right position -->
            <div class="position-absolute top-0 end-0">
                <button type="button" class="btn btn-sm btn-primary m-1" data-bs-toggle="modal" data-bs-target="#itemModal">
                    <i class="bi bi-info-circle"></i>
                </button>
            </div>

            <!-- Middle and Bottom positions -->
            <div class="position-absolute top-50 start-0 translate-middle-y"></div>
            <div class="position-absolute top-50 start-50 translate-middle"></div>
            <div class="position-absolute top-50 end-0 translate-middle-y"></div>
            <div class="position-absolute bottom-0 start-0"></div>
            <div class="position-absolute bottom-0 start-50 translate-middle-x"></div>
            <div class="position-absolute bottom-0 end-0"></div>

            <!-- Footer Section -->
            <div class="d-block bg-primary-subtle">
                <div class="row p-2">
                    <div class="col text-truncate">
                        Disponibles: {{ item.rating.count }}
                    </div>
                    <div class="col-12 col-md-auto">
                        {{ item.price }}&#8364;
                    </div>
                </div>
            </div>
        </div>
    </article>

    <article v-if="loading" class="border border-3 rounded">  
        <div class="position-relative bg-white">            
            <div class="ratio ratio-1x1">

            </div>

            <!-- Top-left position -->
            <div class="position-absolute top-0 start-0 placeholder-glow">
                
            </div>

            <!-- Top-middle position -->
            <div class="position-absolute top-0 start-50 translate-middle-x">

            </div>

            <!-- Top-right position -->
            <div class="position-absolute top-0 end-0">
                <button type="button" class="btn btn-sm btn-secondary m-1" data-bs-toggle="modal" data-bs-target="#itemModal" disabled>
                    <i class="bi bi-info-circle"></i>
                </button>
            </div>

            <!-- Middle and Bottom positions -->
            <div class="position-absolute top-50 start-0 translate-middle-y"></div>
            <div class="position-absolute top-50 start-50 translate-middle">
                <div class="spinner-border">
                    <span class="visually-hidden">Loading...</span>
                </div>
            </div>
            <div class="position-absolute top-50 end-0 translate-middle-y"></div>
            <div class="position-absolute bottom-0 start-0"></div>
            <div class="position-absolute bottom-0 start-50 translate-middle-x"></div>
            <div class="position-absolute bottom-0 end-0"></div>

            <!-- Footer Section -->
            <div class="d-block bg-primary-subtle">
                <div class="row p-2">
                    <div class="col text-truncate placeholder-glow">
                        <span class="placeholder">Disponibles: ?</span>
                    </div>
                    <div class="col-12 col-md-auto placeholder-glow">
                        <span class="placeholder">??????</span>
                    </div>
                </div>
            </div>
        </div>
    </article>
    <article v-if="error">{{ error }}</article>
</template>

<script>
    import { ref, onMounted } from 'vue';
    import apiClient from '../services/api.js';

    export default {
        name: 'Item',
        props: {
            itemMask: {
                type: Number,
                required: true,
            },
        },
        setup(props) {
            const item = ref([]);
            const loading = ref(true);
            const error = ref(null);

            const fetchItem = async () => {
                try {
                    const response = await apiClient.getItem(props.itemMask);
                    item.value = response.data;
                } catch (err) {
                    error.value = 'Error al cargar el producto';
                }
            };

            onMounted(async () => {
                loading.value = true;
                await Promise.all([fetchItem()]);
                loading.value = false;
            });

            return {
                item,
                loading,
                error,
            };
        }
    };
</script>