<template>

    <li class="list-group-item tw-divide-y-2">            
        <div class="tw-flex tw-items-center tw-gap-4">
            <router-link :to="{ name: 'product', params: { mask: props.item.id } }" class="ratio ratio-1x1 tw-w-3/12">
                <ImageExtensionCheckerComponent :class="`tw-object-contain tw-p-1`" :src="apiBaseURL + `/images/` + props.item.mask + `/image.png`" :alt="props.item.name"/>
            </router-link>
            <div class="tw-font-medium tw-w-9/12">
                <div class="tw-w-64 tw-overflow-hidden tw-whitespace-nowrap tw-text-ellipsis tw-truncate dark:tw-text-white">
                    {{ props.item.name }}
                </div>
                <div class="tw-text-sm tw-text-gray-500 dark:tw-text-gray-400">
                    {{ props.item.price + '&#8364' }}
                </div>
                <div class="tw-text-sm tw-text-gray-500 dark:tw-text-gray-400">
                    {{ props.item.quantity + (props.item.quantity > 1 ? ' uds' : ' ud') }}
                </div>
                <div class="tw-text-sm tw-text-gray-500 dark:tw-text-gray-400">
                    {{ 'Total: ' + props.item.price * props.item.quantity + '&#8364' }}
                </div>
            </div>
        </div>
        <div class="row tw-py-1.5 tw-mt-1.5">
            <div class="col">
                <div class="tw-max-w-min tw-bg-gray-500/25 tw-flex tw-p-1 tw-space-x-1.5 tw-rounded-full">
                    <button
                        class="tw-bg-white tw-text-black tw-rounded-full tw-w-7 tw-h-7 tw-flex tw-items-center tw-justify-center tw-shadow-md hover:tw-bg-gray-100 disabled:tw-opacity-50 tw-cursor-pointer tw-text-xl tw-transition tw-delay-150 tw-duration-300 tw-ease-in-out hover:tw--translate-y-1 hover:tw-scale-110"
                        :disabled="isDisabledAdd"
                        @click="increaseQuantity(props.item)"
                    >
                        <i class="bi bi-plus"></i>
                    </button>
                    <button
                        class="tw-bg-black tw-text-white tw-rounded-full tw-w-7 tw-h-7 tw-flex tw-items-center tw-justify-center tw-shadow-md hover:tw-bg-gray-800 disabled:tw-opacity-50 tw-cursor-pointer tw-text-xl tw-transition tw-delay-150 tw-duration-300 tw-ease-in-out hover:tw--translate-y-1 hover:tw-scale-110"
                        @click="decreaseQuantity(props.item)"
                    >
                        <i class="bi bi-dash"></i>
                    </button>
                </div>
            </div>
            <div class="col-auto">
                <div class="tw-flex tw-p-1">
                    <button 
                        class="tw-bg-black tw-text-white tw-rounded-md tw-h-7 tw-flex tw-items-center tw-justify-center tw-px-1.5 tw-shadow-md hover:tw-bg-gray-800 disabled:tw-opacity-50 tw-cursor-pointer tw-text-xl tw-transition tw-delay-150 tw-duration-300 tw-ease-in-out hover:tw--translate-y-1 hover:tw-scale-110"
                        @click="removeFromCart(props.item.id)"
                    >
                        {{ 'Quitar' }}
                    </button>
                </div>
            </div>
        </div>
    </li>
    
</template>

<script setup>

    import { ref } from 'vue';
    import { computed } from 'vue';
    import { useCartStore } from '../../store/cart.js';
    import apiClient from '../../services/api.js';

    const apiBaseURL = ref(apiClient.getApiBaseURL());

    const props = defineProps({
        item: {
            type: Object,
            required: true
        }
    });
    
    const cartStore = useCartStore();

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