<template>

    <div class="tw-grid tw-grid-cols-12 tw-gap-5 tw-p-5 tw-bg-gray-400/25 tw-rounded-[10px]">

        <div class="tw-col-span-12 tw-flex tw-justify-center tw-items-center">
            <StripeInformationFormComponent @validatedStripeInformation="validatePaymentMethod"/>
        </div>

    </div>

</template>

<script setup>

    import { defineEmits } from 'vue';

    const emit = defineEmits(['validatedPaymentMethod']);

    const validatePaymentMethod = (isValid) => {
        emit('validatedPaymentMethod', isValid);
    };

</script>