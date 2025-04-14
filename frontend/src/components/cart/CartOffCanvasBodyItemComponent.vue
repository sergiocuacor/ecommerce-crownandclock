<template>

    <li class="list-group-item">            
        <div class="tw-flex tw-items-center tw-gap-4">
            <router-link :to="{ name: 'product', params: { mask: item.id } }" class="ratio ratio-1x1 tw-w-3/12">
                <img class="tw-object-contain p-1" :src="apiBaseURL + `/images/` + item.mask + `/image.png`" :imgAlt="`${item.mask}`" :alt="item.mask" @error="changeImageExtension"/>
            </router-link>
            <div class="tw-font-medium tw-w-9/12">
                <div class="tw-w-64 tw-overflow-hidden tw-whitespace-nowrap tw-text-ellipsis tw-truncate dark:tw-text-white">
                    {{ item.name }}
                </div>
                <div class="tw-text-sm tw-text-gray-500 dark:tw-text-gray-400">
                    {{ item.price + '&#8364' }}
                </div>
                <div class="tw-text-sm tw-text-gray-500 dark:tw-text-gray-400">
                    {{ item.quantity + (item.quantity > 1 ? ' uds' : ' ud') }}
                </div>
                <div class="tw-text-sm tw-text-gray-500 dark:tw-text-gray-400">
                    {{ 'Total: ' + item.price * item.quantity + '&#8364' }}
                </div>
            </div>
        </div>
        <div class="row px-1">
            <div class="col text-truncate">
                <div class="btn-group">
                <button class="btn btn-sm btn-success" @click="increaseQuantity(item)">
                    <i class="bi bi-plus"></i>
                </button>
                <button class="btn btn-sm btn-danger" @click="decreaseQuantity(item)">
                    <i class="bi bi-dash"></i>
                </button>
            </div>
            </div>
            <div class="col-12 col-md-auto">
                <button class="btn btn-sm btn-danger" @click="removeFromCart(item.id)">
                    {{ 'Quitar' }}
                </button>
            </div>
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

    const changeImageExtension = (event) => {
        event.target.src = event.target.src.replace(/\.png$/, '.jpeg');
    };

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