<template>

    <!-- SUCCESS -->
    <ElementComponent v-if="!loading && !error" :imgSrc="props.imageURL" :imgAlt="props.imageAlt" :imgCustomization="`object-fit-cover`">
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

    // const loading = ref(true);
    const loading = ref(false);
    const error = ref(null);
    
    const props = defineProps({
        imageURL: {
            type: String,
            default: 'watch_front.avif',
            required: false,
        },
        imageAlt: {
            type: String,
            default: 'imagen',
            required: false,
        },
    });

</script>