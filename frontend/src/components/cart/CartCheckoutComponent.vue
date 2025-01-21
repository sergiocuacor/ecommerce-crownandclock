<template>

    <div v-if="cartStore.items.length > 0" class="tw-pt-2.5">
        <div class="tw-text-2xl tw-text-center tw-p-5 space-y-2.5 tw-bg-gray-300 tw-border-2 tw-rounded-lg">
            {{ 'Total: ' + cartTotal + '&#8364' }}         
            <button class="btn btn-primary tw-w-full tw-mt-2.5" @click="checkout">{{ 'Pagar' }}</button>            
        </div> 
    </div>    

</template>

<script setup>

    import { computed } from 'vue';
    import { useCartStore } from '../../store/cart.js';

    const cartStore = useCartStore();

    const cartTotal = computed(() => {
        return cartStore.items.reduce((total, item) => total + item.price * item.quantity, 0).toFixed(2);
    });

    function checkout() {
        alert('Proceeding to checkout...');
    }

</script>