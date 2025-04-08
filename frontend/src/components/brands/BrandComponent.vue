<template>

    <ElementComponent :toRoute="{ name: 'brand', params: { mask: props.brand.id } }" :imgSrc="`/no_image.png`" :imgAlt="`${props.brand.id}`" :imgCustomization="`object-fit-cover`">
        <template #middle-center>
            <div class="tw-uppercase tw-text-center">
                {{ props.brand.name }}
            </div>
        </template>      
    </ElementComponent>

</template>

<script setup>

    const props = defineProps({
        brand: {
            type: Object,
            default: [],
            required: true
        }
    });

</script>
