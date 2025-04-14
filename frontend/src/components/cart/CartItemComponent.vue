<template>

    <li class="tw-flex tw-flex-col md:tw-flex-row md:tw-items-center tw-relative tw-py-2.5">
        <router-link :to="{ name: 'product', params: { mask: item.id } }" class="ratio ratio-1x1 tw-w-full md:tw-w-2/12 tw-bg-gray-100 tw-rounded">
            <ImageExtensionCheckerComponent :class="`tw-object-contain tw-p-3`" :src="apiBaseURL + `/images/` + item.mask + `/image.png`" :alt="item.name"/>
        </router-link>
        <div class="tw-absolute tw-top-0 tw-right-0 tw-me-2.5 md:tw-me-0 tw-mt-5 md:tw-mt-2.5">
            <div class="btn-group">
                <button class="btn btn-sm btn-success" @click="increaseQuantity(item)">
                    <i class="bi bi-plus"></i>
                </button>
                <button class="btn btn-sm btn-danger" @click="decreaseQuantity(item)">
                    <i class="bi bi-dash"></i>
                </button>
            </div>              
        </div>
        <div class="tw-font-medium tw-w-full md:tw-w-10/12 md:tw-ps-2">
            <div class="tw-overflow-hidden tw-truncate dark:tw-text-white">
                {{ item.title }}
            </div>
            <div class="tw-text-sm tw-text-gray-500 dark:tw-text-gray-400">
                {{ 'Precio: ' + item.price + '&#8364' }}
            </div>
            <div class="tw-text-sm tw-text-gray-500 dark:tw-text-gray-400">
                {{ item.quantity + (item.quantity > 1 ? ' uds' : ' ud') }}
            </div>
            <div class="tw-text-sm tw-text-gray-500 dark:tw-text-gray-400">
                {{ 'Total: ' + (item.price * item.quantity) + '&#8364' }}
            </div>
        </div>
        <div class="tw-absolute tw-bottom-0 tw-right-0 tw-my-2">
            <button class="btn btn-sm btn-danger" @click="removeFromCart(item.id)">
                {{ 'Quitar' }}
            </button>
        </div>           
    </li>
    
</template>

<script setup>

    import { ref } from 'vue';
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

    const item = props.item;

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