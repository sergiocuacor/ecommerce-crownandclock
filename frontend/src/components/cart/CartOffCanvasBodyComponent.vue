<template>

    <!-- FULL -->
    <CartOffCanvasBodyItemsComponent v-if="cartStore.items.length > 0"/>

    <div v-if="cartStore.items.length > 0" class="tw-sticky tw-bottom-0 tw-py-2.5 tw-bg-white">        
        <router-link
            :to="{ name: 'cart' }"
            class="tw-bg-black tw-text-white tw-rounded-md tw-h-10 tw-flex tw-items-center tw-justify-center tw-px-1.5 tw-shadow-md hover:tw-bg-gray-800 tw-cursor-pointer tw-text-xl tw-transition tw-delay-150 tw-duration-300 tw-ease-in-out hover:tw--translate-y-1 hover:tw-scale-90">
            {{ 'Checkout' }}
        </router-link>
    </div>

    <!-- EMPTY -->
    <div v-else class="tw-w-full tw-h-full tw-relative">
        <div class="tw-absolute tw-top-1/2 tw-left-1/2 tw-transform tw--translate-x-1/2 tw--translate-y-1/2 tw-bg-gray-200 tw-px-2 tw-rounded">
            {{ 'Vacío' }}
        </div>
    </div>

</template>

<script setup>

    import { useCartStore } from '../../store/cart.js';

    const cartStore = useCartStore();

</script>