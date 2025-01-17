<template>
    <div class="btn-group m-1" aria-label="Cart Buttons">
        <button class="btn btn-sm btn-success" @click="addToCart" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar">
            <i class="bi bi-bag-plus-fill"></i>
        </button>
        <button type="button" class="btn btn-sm btn-danger" @click="removeFromCart" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" :disabled="isDisabled">
            <i class="bi bi-bag-dash-fill"></i>
        </button>       
    </div>  
</template>

<script setup>

    import { ref, onMounted } from 'vue';
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

    const isDisabled = ref(true);

    const addToCart = () => {
        cartStore.addToCart(props.item);
        utilsStore.setOffCanvasContent('cart');
        isDisabled.value = false;
    };

    const removeFromCart = () => {
        cartStore.removeFromCart(props.item.id);
        utilsStore.setOffCanvasContent('cart');
        isDisabled.value = true;
    };

    onMounted(() => {
        isDisabled.value = !cartStore.items.some(item => item.id === props.item.id);
    });

</script>
