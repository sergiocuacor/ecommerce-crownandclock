<template>
    <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
        <div class="offcanvas-header">
            <h5 class="offcanvas-title" id="offcanvasNavbarLabel">
                <i v-if="utilsStore.offCanvasContent === 'cart'" class="bi bi-basket-fill">{{ ' Cart' }}</i>
            </h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body tw-py-0">
            <CartOffCanvasBodyComponent v-if="utilsStore.offCanvasContent === 'cart'" />
            <UserControlPanelOffCanvasBodyComponent v-if="utilsStore.offCanvasContent === 'controlPanel' && authStore.token"/>
        </div>
    </div>
</template>

<script setup>

    import { useUtilsStore } from '../services/utils.js';
    import { useAuthStore } from '../services/auth.js'

    const utilsStore = useUtilsStore();
    const authStore = useAuthStore();

</script>