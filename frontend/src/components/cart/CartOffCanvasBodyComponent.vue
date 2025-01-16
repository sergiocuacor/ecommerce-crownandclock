<template>
    <ul class="list-group list-group-flush">
        <li v-for="item in cartStore.items" :key="item.id" class="list-group-item">
            {{ item.title }}
        </li>
    </ul>
</template>

<script setup>

    import { useCartStore } from '../../store/cart.js';

    const cartStore = useCartStore();

</script>
