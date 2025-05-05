<template>

    <ul v-if="props.orderItems.length > 0" class="tw-px-2.5 tw-divide-y-2 tw-divide-slate-400/25 tw-bg-gray-200 tw-border-2 tw-rounded-lg">
        <p class="tw-text-center tw-text-lg tw-font-semibold tw-py-2">{{  'Watches' }}</p>
        <OrderItemComponent :adminMode="props.adminMode" v-for="item, index in props.orderItems" :key="index" :item="item"/>
    </ul>

</template>

<script setup>

    import { defineProps } from 'vue';

    const props = defineProps({
        orderItems: {
            type: Array,
            default: [],
            required: true
        },
        adminMode: {
            type: Boolean,
            default: false,
        }
    });

</script>