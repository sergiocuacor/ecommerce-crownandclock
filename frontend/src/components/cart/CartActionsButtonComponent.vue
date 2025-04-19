<template>

    <div class="tw-bg-gray-500/25 tw-flex tw-p-0.5 tw-rounded-md">

        <button
            class="tw-bg-white tw-text-black tw-rounded-s-md tw-w-12 tw-h-12 sm:tw-w-10 sm:tw-h-10 tw-flex tw-items-center tw-justify-center tw-shadow-md hover:tw-bg-gray-100 disabled:tw-opacity-50"
            @click="addToCart"
            :disabled="isDisabledAdd"
            data-bs-toggle="offcanvas"
            data-bs-target="#offcanvasNavbar"
            :title="`Add to cart`"
        >
            <i class="bi bi-bag-plus-fill"></i>
        </button>
    
        <button
            type="button"
            class="tw-bg-black tw-text-white tw-rounded-e-md tw-w-12 tw-h-12 sm:tw-w-10 sm:tw-h-10 tw-flex tw-items-center tw-justify-center tw-shadow-md hover:tw-bg-gray-800 disabled:tw-opacity-50"
            @click="removeFromCart"
            :disabled="isDisabledRemove"
            data-bs-toggle="offcanvas"
            data-bs-target="#offcanvasNavbar"            
            :title="`Remove from cart`"
        >
            <i class="bi bi-bag-dash-fill"></i>
        </button>

    </div>

</template>  

<script setup>

    import { computed } from 'vue';
    import { useCartStore } from '../../store/cart.js';
    import { useUtilsStore } from '../../services/utils.js';

    const props = defineProps({
        item: {
            type: Object,
            required: true,
        },
    });

    const cartStore = useCartStore();
    const utilsStore = useUtilsStore();

    const isDisabledRemove = computed(() => {
        return !cartStore.items.some(item => item.id === props.item.id);
    });

    const isDisabledAdd = computed(() => {
        const itemInCart = cartStore.items.find(item => item.id === props.item.id);
        return itemInCart ? itemInCart.quantity >= itemInCart.stock : false;
    });

    const addToCart = () => {
        cartStore.addToCart(props.item);
        utilsStore.setOffCanvasContent('cart');
    };

    const removeFromCart = () => {
        cartStore.removeFromCart(props.item.id);
        utilsStore.setOffCanvasContent('cart');
    };

</script>
