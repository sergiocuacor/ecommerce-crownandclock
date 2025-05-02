<template>

    <div class="tw-grid tw-grid-cols-12 tw-gap-5 tw-p-5 tw-bg-gray-400/25 tw-rounded-[10px]">

        <div class="tw-col-span-12 tw-flex tw-justify-center tw-items-center">
            <PaypalInformationFormComponent @validatedPaypalInformation="validatePaymentMethod"/>
        </div>

    </div>

</template>

<script setup>

    import { defineEmits, ref } from 'vue';

    const emit = defineEmits(['validatedPaymentMethod']);

    const creditCardInformation = ref({
        holderName: null,
        number: null,       
        expiryDate: null,
        cvv: null,
    });

    const validatePaymentMethod = (isValid) => {
        emit('validatedPaymentMethod', isValid);
    };

</script>