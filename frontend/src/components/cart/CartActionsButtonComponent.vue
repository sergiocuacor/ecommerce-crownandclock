<template>

    <div class="tw-bg-gray-500/25 tw-flex tw-p-1 tw-rounded-full tw-space-x-1.5">

        <button
            class="tw-bg-white tw-text-black tw-rounded-full tw-w-12 tw-h-12 sm:tw-w-10 sm:tw-h-10 tw-flex tw-items-center tw-justify-center tw-shadow-md hover:tw-bg-gray-100 disabled:tw-bg-gray-200 disabled:tw-text-gray-500 tw-cursor-pointer tw-text-xl tw-transition tw-delay-150 tw-duration-300 tw-ease-in-out hover:tw--translate-y-1 hover:tw-scale-110 disabled:hover:tw-scale-90 disabled:hover:tw--translate-y-0 disabled:tw-cursor-not-allowed"
            @click="addToCart"
            :disabled="isDisabledAdd"
            data-bs-toggle="offcanvas"
            data-bs-target="#offcanvasNavbar"
            :title="`Add to cart`"
        >
            <i class="bi bi-plus"></i>
        </button>
    
        <button
            type="button"
            class="tw-bg-black tw-text-white tw-rounded-full tw-w-12 tw-h-12 sm:tw-w-10 sm:tw-h-10 tw-flex tw-items-center tw-justify-center tw-shadow-md hover:tw-bg-gray-800 disabled:tw-bg-gray-500 disabled:tw-text-gray-200 tw-cursor-pointer tw-text-xl tw-transition tw-delay-150 tw-duration-300 tw-ease-in-out hover:tw--translate-y-1 hover:tw-scale-110 disabled:hover:tw-scale-90 disabled:hover:tw--translate-y-0 disabled:tw-cursor-not-allowed"
            @click="removeFromCart"
            :disabled="isDisabledRemove"
            data-bs-toggle="offcanvas"
            data-bs-target="#offcanvasNavbar"            
            :title="`Remove from cart`"
        >
            <i class="bi bi-dash"></i>
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
        if (props.item.stock <= 0) { return true; }
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
