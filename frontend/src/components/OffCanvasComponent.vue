<template>
    <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
        <div class="offcanvas-header">
            <h5 class="offcanvas-title" id="offcanvasNavbarLabel">
                <i v-if="offCanvasContent === 'cart'" class="bi bi-cart-fill">{{ ' Carrito' }}</i>
            </h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body">
            <CartOffCanvasBodyComponent v-if="offCanvasContent === 'cart'" />
            <UserControlPanelOffCanvasBodyComponent v-if="offCanvasContent === 'controlPanel'" />
        </div>
    </div>
</template>

<script setup>

    import { useUtilsStore } from '../services/utils.js';

    const utilsStore = useUtilsStore();

    const offCanvasContent = utilsStore.offCanvasContent;

</script>