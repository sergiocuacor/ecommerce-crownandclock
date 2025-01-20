<template>

    <!-- FULL -->
    <ul v-if="cartStore.items.length > 0" class="tw-px-2.5 tw-divide-y-2 tw-divide-slate-400/25 tw-bg-gray-200 tw-border-2">
        <li v-for="item in cartItems" :key="item.id" class="tw-flex tw-flex-col md:tw-flex-row md:tw-items-center tw-relative tw-py-2.5">
            <router-link :to="{ name: 'product', params: { mask: item.id } }" class="ratio ratio-1x1 tw-w-full md:tw-w-2/12 tw-bg-gray-100 tw-rounded">
                <img class="tw-object-contain tw-p-3" :src="item.image" :alt="item.id">
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
    </ul>

    <div v-if="cartStore.items.length > 0" class="tw-pt-2.5">
        <div class="tw-text-2xl tw-text-center tw-p-5 space-y-2.5 tw-bg-gray-300 tw-border-2 tw-rounded-lg">
            {{ 'Total: ' + (cartTotal | currency) + '&#8364' }}         
            <button class="btn btn-primary tw-w-full tw-mt-2.5" @click="checkout">{{ 'Pagar' }}</button>            
        </div> 
    </div>

    <!-- EMPTY -->
    <section v-else class="tw-aspect-square lg:tw-aspect-video tw-relative">        
        <div class="tw-absolute tw-top-1/2 tw-left-1/2 tw-transform tw--translate-x-1/2 tw--translate-y-1/2 tw-bg-gray-200 tw-px-2 tw-rounded">
            {{ 'Vacío' }}
        </div>
    </section>

</template>

<script setup>

    import { computed } from 'vue';
    import { useCartStore } from '../../store/cart.js';    

    const cartStore = useCartStore();

    const cartItems = computed(() => cartStore.items);

    const cartTotal = computed(() => {
        return cartStore.items.reduce((total, item) => total + item.price * item.quantity, 0);
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

    function checkout() {
        alert('Proceeding to checkout...');
    }

    function currency(value) {
        return `$${value.toFixed(2)}`;
    }

</script>