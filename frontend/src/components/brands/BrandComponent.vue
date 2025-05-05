<template>

    <ElementComponent :toRoute="{ name: 'brand', params: { mask: props.brand.id } }" :imgSrc="`/no_image.png`" :imgAlt="`${props.brand.id}`" :imgCustomization="`object-fit-cover`" :title="props.brand.name">
        <template #top-right>
            <div :title="`Watches From This Brand`" class="tw-text-white tw-font-bold tw-text-xl tw-tracking-wider tw-bg-black tw-m-2 tw-p-2 tw-border tw-border-red-500 tw-rounded-[8px]">
                {{ props.brand.numberOfProducts }}
            </div>
        </template>
        <template #middle-center>
            <router-link :to="{ name: 'brand', params: { mask: props.brand.id } }" class="tw-block tw-font-bold tw-text-2xl tw-uppercase tw-text-center">
                {{ props.brand.name }}
            </router-link>
        </template>      
    </ElementComponent>

</template>

<script setup>

    const props = defineProps({
        brand: {
            type: Object,
            default: {},
            required: true
        }
    });

</script>
