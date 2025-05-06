<template>

    <ElementComponent :toRoute="{ name: 'brand', params: { mask: props.brand.id } }" :imgSrc="`/brands/${props.brand.id}.png`" :imgAlt="`${props.brand.id}`" :imgCustomization="`object-fit-contain p-4`" :title="props.brand.name">
        <template #bottom-right>
            <div :title="`Watches From This Brand`" class="tw-text-black tw-font-bold tw-text-xl tw-tracking-wider tw-mb-1.5 tw-me-2 tw-cursor-help">
                {{ props.brand.numberOfProducts }}
            </div>
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
