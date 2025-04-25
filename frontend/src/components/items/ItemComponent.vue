<template>

    <ElementComponent :toRoute="{ name: 'product', params: { mask: props.item.mask } }" :imgSrc="apiBaseURL + `/images/` + props.item.mask + `/image.png`" :imgAlt="`${props.item.mask}`">
        <template #top-left>
            <ItemRatingComponent :averageRating="props.item.averageRating" :ratingCount="props.item.ratingCount"/>
        </template>
        <template #bottom-right>
            <CartActionsButtonComponent :item="props.item" :class="'m-1'"/>
        </template>
        <template #footer>
            <div class="col text-truncate">
                {{ props.item.name }}
            </div>
            <div class="col-12 col-md-auto">
                {{ props.item.price + '&#8364' }}
            </div>
            <div class="col-12">
                {{ 'Disponibles: ' + props.item.stock }}
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
            default: {},
            required: true
        }
    });

</script>