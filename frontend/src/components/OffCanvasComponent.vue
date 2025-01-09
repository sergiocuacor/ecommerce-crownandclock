<template>
    <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
        <div class="offcanvas-header">
            <h5 class="offcanvas-title" id="offcanvasNavbarLabel"><i class="bi bi-hexagon-fill"> Sidebar</i></h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body">
            <OffCanvasBodyCartComponent v-if="content === 'cart'" />
            <OffCanvasBodyControlPanelComponent v-if="content === 'controlPanel'" />
            <p v-if="content === ''">Selecciona un contenido.</p>
        </div>
    </div>
</template>

<script>
    export default {
        props: {
            content: {
            type: String,
            default: "",
            },
        },
    };
</script>