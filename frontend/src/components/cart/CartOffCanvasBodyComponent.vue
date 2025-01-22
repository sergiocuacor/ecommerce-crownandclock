<template>

    <!-- FULL -->
    <CartOffCanvasBodyItemsComponent v-if="cartStore.items.length > 0"/>

    <CartCheckoutComponent v-if="cartStore.items.length > 0" class="tw-sticky tw-bottom-0 tw-py-2.5 tw-bg-white"/>

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