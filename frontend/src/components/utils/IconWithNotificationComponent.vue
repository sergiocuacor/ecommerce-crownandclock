<template>
    <i class="bi tw-relative" :class="props.icon">
        <span v-if="props.notificationColor" class="tw-absolute tw-h-1.5 tw-w-1.5 tw--right-1 tw--top-0 tw-border-1 tw-rounded-full" :class="props.notificationColor">
            <span class="visually-hidden">{{ 'New alerts' }}</span>
        </span>
    </i>
</template>

<script setup>

    import { defineProps } from 'vue';

    const props = defineProps({
        icon: {
            type: String,
            //default: '',
            required: true,
        },
        notificationColor: {
            type: String,
            //default: 'tw-bg-red-500',
            required: false,
        },
    });

</script>