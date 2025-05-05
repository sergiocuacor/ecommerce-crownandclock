<template>                           

    <div class="tw-mt-2 tw-p-2 tw-bg-gray-200 tw-border-2 tw-rounded-md tw-divide-y-2 tw-divide-gray-300">
        <div class="tw-text-center tw-text-lg tw-font-semibold tw-pb-2">
            {{ 'Invoice' }}
        </div>
        <div class="tw-py-2 tw-font-bold">
            {{ 'Subtotal: ' }}<strong>{{ cartSubtotal + '€' }}</strong>
        </div>
        <div class="tw-py-2 tw-font-bold">
            {{ 'Taxes (21%): ' }}<strong>{{ cartTaxes + '€' }}</strong>
        </div>
        <div v-if="props.order.discountPercentage > 0" class="tw-py-2 tw-font-bold">
            {{ 'Coupon ' + props.order.discountApplied + '(' + props.order.discountPercentage + '%): '}}<strong>{{ '-' + cartDiscount + '€' }}</strong>
        </div>
        <div class="tw-text-center tw-pt-2">
            {{ 'Total: ' }}<strong>{{ cartTotal + '€' }}</strong>
        </div>
    </div>

</template>

<script setup>

    import { computed } from 'vue';

    const props = defineProps({
        order: {
            type: Object,
            required: true
        }
    });

    const rawTotal = computed(() =>
        props.order.orderDetails.reduce((total, item) => total + item.subTotal, 0)
    );

    const cartTotal = computed(() => props.order.total.toFixed(2));
    const cartSubtotal = computed(() => (rawTotal.value * 0.79).toFixed(2));
    const cartTaxes = computed(() => (rawTotal.value * 0.21).toFixed(2));
    const cartDiscount = computed(() => (rawTotal.value * (props.order.discountPercentage / 100)).toFixed(2));

</script>