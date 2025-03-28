<template>

    <!-- SUCCESS -->
    <ElementComponent v-if="!loading && !error" :toRoute="{ name: 'product', params: { mask: item.id } }" :imgSrc="apiBaseURL + `/images/` + item.mask + `/image.png`" :imgAlt="`${item.mask}`">
        <template #bottom-right>
            <CartActionsButtonComponent :item="item" :class="'m-1'"/>
        </template>
        <template #footer>
            <div class="col text-truncate">
                {{ item.name }}
            </div>
            <div class="col-12 col-md-auto">
                {{ item.price + '&#8364' }}
            </div>
            <div class="col-12">
                {{ 'Disponibles: ' + item.stock }}
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

    const apiBaseURL = ref(apiClient.getApiBaseURL());

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

        const response = await apiClient.getItem(props.itemMask)

        if (response.success) {
            item.value = response.data;
        } else {
            error.value = response.error;
        }

    };

    onMounted(async () => {
        loading.value = true;
        await fetchItem();
        loading.value = false;
    });

</script>