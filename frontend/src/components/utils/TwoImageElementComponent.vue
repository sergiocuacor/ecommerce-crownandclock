<template>
    <div class="ratio ratio-1x1 bg-danger" @mouseover="isHovered = true" @mouseleave="isHovered = false">
        <img :src="isHovered ? '/watch_back.avif' : '/watch_front.avif'" alt="Imagen ejemplo" />
    </div>
</template>

<script setup>
            
    const isHovered = false;

</script>