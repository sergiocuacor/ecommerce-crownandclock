<template>
    
    <img :class="[imageLoaded ? '' : 'd-none']" @error="changeImageExtension" @load="imageLoaded = true"/>

</template>

<script setup>

    import { ref } from 'vue';

    const imageLoaded = ref(false);
    const extensions = ['.jpeg', '.jpg', '.png']; // More Extensions: '.webp', '.svg', '.gif', '.bmp', '.tiff', '.ico', '.jfif', '.webm', '.avif'
    let attemptIndex = 0;

    const changeImageExtension = (event) => {

        const imgElement = event.target;
        
        if (attemptIndex < extensions.length) {
            
            imgElement.src = imgElement.src.replace(/\.\w+$/, extensions[attemptIndex]);
            attemptIndex++;

        } else {
            
            imgElement.src = '/no_image.png';

        }

    };

</script>