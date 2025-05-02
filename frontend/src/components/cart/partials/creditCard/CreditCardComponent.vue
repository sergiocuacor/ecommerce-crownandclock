<template>

    <div class="tw-grid tw-grid-cols-12 tw-gap-5 tw-p-5 tw-bg-gray-400/25 tw-rounded-[10px]">

        <div class="tw-col-span-12 lg:tw-col-span-5 tw-flex tw-justify-center tw-items-center">
            <CreditCardInformationComponent :number="creditCardInformation.number" :holderName="creditCardInformation.holderName" :expiryDate="creditCardInformation.expiryDate" />
        </div>

        <div class="tw-col-span-12 lg:tw-col-span-7 tw-flex tw-justify-center tw-items-center">
            <CreditCardInformationFormComponent @validatedCreditCardInformation="saveCreditCardInformation"/>
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

    const saveCreditCardInformation = (data) => {
        creditCardInformation.value = data;
        emit('validatedPaymentMethod', true);
    };

</script>