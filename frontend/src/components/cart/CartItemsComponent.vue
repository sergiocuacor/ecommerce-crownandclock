<template>

    <ul v-if="cartStore.items.length > 0" class="tw-px-2.5 tw-divide-y-2 tw-divide-slate-400/25 tw-bg-gray-200 tw-border-2">
        <CartItemComponent v-for="item in cartItems" :key="item.id" :item="item"/>
    </ul>

</template>

<script setup>

    import { useCartStore } from '../../store/cart.js';

    const cartStore = useCartStore();

</script>