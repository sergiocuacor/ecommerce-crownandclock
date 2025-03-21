<template>

    <!-- SUCCESS -->
    <ElementComponent v-if="!loading && !error" :imgSrc="item.image" :imgAlt="item.alt" :imgCustomization="`object-fit-cover`">
        <template #bottom-right>    
            <button class="btn btn-sm btn-primary m-1">
                <i class="bi bi-pencil-fill"></i>
            </button>
        </template>
    </ElementComponent>

    <!-- LOADING -->
    <ElementComponent v-if="loading">
        <template #middle-center>
            <div class="spinner-border">
                <span class="visually-hidden">{{ 'Loading...' }}</span>
            </div>
        </template>     
    </ElementComponent>

    <!-- ERROR -->
    <ElementComponent v-if="error">
        <template #middle-center>
            <i class="bi bi-exclamation-diamond-fill tw-text-red-500 fs-1"></i>
        </template>   
    </ElementComponent>

</template>

<script setup>

    import { ref, onMounted } from 'vue';

    const item = ref([]);
    const loading = ref(true);
    const error = ref(null);
    
    item.value = {
        alt: 'user-image',
        image: 'watch_front.avif',
    };

    onMounted(() => {
        setTimeout(() => {
            loading.value = false;
        }, 2000);
    }); 

</script>