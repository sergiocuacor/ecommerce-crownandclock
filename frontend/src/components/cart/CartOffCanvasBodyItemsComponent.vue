<template>

    <ul v-if="cartStore.items.length > 0" class="list-group list-group-flush">
        <CartOffCanvasBodyItemComponent v-for="item in cartStore.items" :key="item.id" :item="item"/>
    </ul>

</template>

<script setup>

    import { useCartStore } from '../../store/cart.js';

    const cartStore = useCartStore();

</script>