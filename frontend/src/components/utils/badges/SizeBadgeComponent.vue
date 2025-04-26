<template>

    <span class="tw-bg-black tw-text-white tw-py-0.5 tw-px-3 tw-rounded-full tw-font-semibold tw-text-sm">
        {{ 'Watches per page: ' + props.pageSize }}
    </span>

</template>

<script setup>

    import { defineProps } from 'vue';

    const props = defineProps({
        pageSize: {
            required: true,
        },
    });

</script>
