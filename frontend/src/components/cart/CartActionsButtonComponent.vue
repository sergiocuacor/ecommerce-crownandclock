<template>
    <div class="btn-group m-1" role="group" aria-label="Cart Buttons">
        <button class="btn btn-sm btn-success" @click="addToCart">
            <i class="bi bi-bag-plus-fill"></i>
        </button>
        <button class="btn btn-sm btn-danger" @click="removeFromCart">
            <i class="bi bi-bag-dash-fill"></i>
        </button>
    </div>  
</template>

<script setup>

    import { useCartStore } from '../../store/cart.js';
    
    const props = defineProps({
        item: {
            type: Object,
            required: true,
        },
    });
    
    const cartStore = useCartStore();

    const addToCart = () => {
        cartStore.addToCart(props.item);
    };

    const removeFromCart = () => {
        cartStore.removeFromCart(props.item.id);
    };

</script>