<template>

    <li class="tw-flex tw-flex-col md:tw-flex-row md:tw-items-center tw-relative tw-py-2.5">
        <router-link :to="{ name: 'product', params: { mask: props.item.id } }" class="ratio ratio-1x1 tw-w-full md:tw-w-2/12 tw-bg-gray-100 tw-rounded">
            <ImageExtensionCheckerComponent :class="`tw-object-contain tw-p-3`" :src="apiBaseURL + `/images/` + props.item.mask + `/image.png`" :alt="props.item.name"/>
        </router-link>
        <div class="tw-absolute tw-top-0 tw-right-0 tw-me-2.5 md:tw-me-0 tw-mt-5 md:tw-mt-2.5">
            <div class="tw-max-w-min tw-bg-gray-500/25 tw-flex tw-p-1 tw-space-x-1.5 tw-rounded-full">
                <button
                    class="tw-bg-white tw-text-black tw-rounded-full tw-w-8 tw-h-8 tw-flex tw-items-center tw-justify-center tw-shadow-md hover:tw-bg-gray-100 disabled:tw-opacity-50 tw-cursor-pointer tw-text-xl tw-transition tw-delay-150 tw-duration-300 tw-ease-in-out hover:tw--translate-y-1 hover:tw-scale-110"
                    :disabled="isDisabledAdd"
                    @click="increaseQuantity(props.item)"
                >
                    <i class="bi bi-plus"></i>
                </button>
                <button
                    class="tw-bg-black tw-text-white tw-rounded-full tw-w-8 tw-h-8 tw-flex tw-items-center tw-justify-center tw-shadow-md hover:tw-bg-gray-800 disabled:tw-opacity-50 tw-cursor-pointer tw-text-xl tw-transition tw-delay-150 tw-duration-300 tw-ease-in-out hover:tw--translate-y-1 hover:tw-scale-110"
                    @click="decreaseQuantity(props.item)"
                >
                    <i class="bi bi-dash"></i>
                </button>
            </div>              
        </div>
        <div class="tw-font-medium tw-w-full md:tw-w-10/12 md:tw-ps-2 tw-pt-2 md:tw-pt-0">
            <div class="tw-overflow-hidden tw-truncate dark:tw-text-white">
                {{ props.item.name }}
            </div>
            <div class="tw-text-sm tw-text-gray-500 dark:tw-text-gray-400">
                {{ props.item.price + '&#8364' }}
            </div>
            <div class="tw-text-sm tw-text-gray-500 dark:tw-text-gray-400">
                {{ props.item.quantity + (props.item.quantity > 1 ? ' uds' : ' ud') }}
            </div>
            <div class="tw-text-sm tw-text-gray-500 dark:tw-text-gray-400">
                {{ 'Total: ' + (props.item.price * props.item.quantity) + '&#8364' }}
            </div>
        </div>
        <div class="tw-absolute tw-bottom-0 tw-right-0 tw-my-2">
            <button class="tw-bg-black tw-text-white tw-rounded-md tw-h-8 tw-flex tw-items-center tw-justify-center tw-px-1.5 tw-shadow-md hover:tw-bg-gray-800 disabled:tw-opacity-50 tw-cursor-pointer tw-text-xl tw-transition tw-delay-150 tw-duration-300 tw-ease-in-out hover:tw--translate-y-1 hover:tw-scale-110" @click="removeFromCart(item.id)">
                {{ 'Remove' }}
            </button>
        </div>           
    </li>
    
</template>

<script setup>

    import { ref, computed } from 'vue';
    import { useCartStore } from '../../store/cart.js';
    import apiClient from '../../services/api.js';

    const apiBaseURL = ref(apiClient.getApiBaseURL());    
    const cartStore = useCartStore();

    const props = defineProps({
        item: {
            type: Object,
            required: true
        }
    });

    const isDisabledAdd = computed(() => {
        const itemInCart = cartStore.items.find(item => item.id === props.item.id);
        return itemInCart ? itemInCart.quantity >= itemInCart.stock : false;
    });

    function increaseQuantity(item) {
        cartStore.increaseQuantity(item.id);
    }

    function decreaseQuantity(item) {
        if (item.quantity > 1) {
            cartStore.decreaseQuantity(item.id);
        } else {
            cartStore.removeFromCart(item.id);
        }
    }

    function removeFromCart(productId) {
        cartStore.removeFromCart(productId);
    }

</script>