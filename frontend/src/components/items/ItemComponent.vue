<template>

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

</template>

<script setup>

    import { ref } from 'vue';
    import apiClient from '../../services/api.js';

    const apiBaseURL = ref(apiClient.getApiBaseURL());

    const props = defineProps({
        item: {
            type: Object,
            default: [],
            required: true
        }
    });

</script>