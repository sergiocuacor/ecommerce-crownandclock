<template>
    <div class="tw-relative tw-w-full tw-h-full lg:tw-h-screen">
        <video autoplay loop muted class="tw-absolute tw-top-0 tw-left-0 tw-w-full tw-h-full tw-object-cover">
            <source :src="videoSrc" type="video/mp4" />
        </video>

        <div v-if="hasSlotContent('main')" class="tw-relative tw-flex tw-items-center tw-justify-center tw-h-full tw-pt-20 tw-pb-5 tw-px-5 lg:tw-p-0">
            <slot name="main"></slot>
        </div>
    </div>
</template>

<script setup>

    defineProps({
        videoSrc: {
            type: String,
            default: '',
            required: true,
        },
    });

    const slots = defineSlots();

    const hasSlotContent = (slotName) => {

        return !!slots[slotName] && slots[slotName]().length > 0;

    };

</script>