<template>

    <li class="tw-flex tw-flex-col md:tw-flex-row md:tw-items-center tw-relative tw-py-2.5">
        <router-link :to="{ name: 'product', params: { mask: props.item.productInfo.productMask } }" class="ratio ratio-1x1 tw-w-full md:tw-w-2/12 lg:tw-w-1/12 tw-bg-gray-100 tw-rounded">
            <ImageExtensionCheckerComponent :class="`tw-object-contain tw-p-8 md:tw-p-4`" :src="apiBaseURL + `/images/` + props.item.productInfo.productMask + `/image.png`" :alt="props.item.productInfo.productName"/>
        </router-link>
        <div class="tw-absolute tw-top-0 tw-right-0 tw-me-2.5 md:tw-me-0 tw-mt-5 md:tw-mt-2.5 tw-p-1 tw-rounded-md tw-block md:tw-hidden tw-bg-black tw-text-white">
            {{ props.item.productInfo.productName }}      
        </div>
        <div class="tw-font-medium tw-w-full md:tw-w-10/12 md:tw-ps-2 tw-pt-2 md:tw-pt-0">
            <div class="tw-overflow-hidden tw-truncate tw-hidden md:tw-block">
                {{ props.item.productInfo.productName }}
            </div>
            <div class="tw-text-sm tw-text-gray-500">
                {{ 'Price: ' + props.item.unitPrice + '&#8364' }}
            </div>
            <div class="tw-text-sm tw-text-gray-500">
                {{ 'Quantity: ' + props.item.quantity + (props.item.quantity > 1 ? ' uds' : ' ud') }}
            </div>
        </div>
        <div class="tw-absolute tw-bottom-0 tw-right-0 tw-font-bold tw-my-2">
            {{ 'Total: ' }}<strong>{{ (props.item.unitPrice * props.item.quantity) + '&#8364' }}</strong>
        </div>        
    </li>
    <li class="tw-flex tw-flex-col tw-items-center tw-py-2.5">
        <div class="tw-w-11/12 tw-space-y-2.5">
            <button
                @click="showReviewForm = !showReviewForm"
                type="button" 
                class="tw-flex tw-justify-center tw-w-full tw-gap-2 tw-bg-black tw-text-white tw-px-3 tw-py-1.5 tw-rounded-md tw-text-medium tw-font-medium hover:tw-bg-gray-700 focus:tw-outline-none focus:tw-ring-2 focus:tw-ring-gray-500 active:tw-scale-95 tw-transition"
            >
                <i class="bi bi-pen-fill"></i>
                <span class="tw-text-center">{{ 'Review' }}</span>
            </button>
            <ReviewFormComponent v-if="showReviewForm" :productId="item.productInfo.productId"/>
        </div>
    </li>
</template>

<script setup>

    import { ref } from 'vue';
    import apiClient from '../../services/api.js';

    const apiBaseURL = ref(apiClient.getApiBaseURL());

    const props = defineProps({
        item: {
            type: Object,
            required: true
        }
    });

    const showReviewForm = ref(false);

</script>