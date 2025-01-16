<template>
    <ul class="list-group">        
        <li v-for="item in items" :key="item.id" class="list-group-item">
            {{ item.title }}
        </li>
    </ul>
</template>

<script setup>

    import { useCartStore } from '../store/cart.js';

    const items = useCartStore().items;

</script>