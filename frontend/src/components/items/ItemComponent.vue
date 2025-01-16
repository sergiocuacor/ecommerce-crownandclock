<template>

    <!-- SUCCESS -->
    <ElementComponent v-if="!loading && !error" :toRoute="{ name: 'product', params: { mask: item.id } }" :imgSrc="item.image" :imgAlt="`${item.id}`">
        <template #top-left>
            <RatingComponent v-if="item.rating" :itemRating="item.rating" />
        </template>
        <template #bottom-right>
            <CartActionsButton :item="item"/>
        </template>
        <template #footer>
            <div class="col text-truncate">
                {{ 'Disponibles: ' + item.rating.count }}
            </div>
            <div class="col-12 col-md-auto">
                {{ item.price + '&#8364' }}
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
            <div class="col text-truncate placeholder-glow">
                <span class="placeholder">{{ 'Disponibles: ?' }}</span>
            </div>
            <div class="col-12 col-md-auto placeholder-glow">
                <span class="placeholder">{{ '??????' }}</span>
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
        itemMask: {
            type: Number,
            required: true,
        },
    });

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

</script>