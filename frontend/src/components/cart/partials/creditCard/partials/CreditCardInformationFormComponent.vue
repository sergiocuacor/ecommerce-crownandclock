<template>

    <div class="tw-w-fit tw-h-fit tw-bg-white tw-shadow-[0px_187px_75px_rgba(0,0,0,0.01),0px_105px_63px_rgba(0,0,0,0.05),0px_47px_47px_rgba(0,0,0,0.09),0px_12px_26px_rgba(0,0,0,0.1),0px_0px_0px_rgba(0,0,0,0.1)] tw-rounded-[26px] tw-max-w-[450px]">
        <form @submit.prevent="validateCreditCardInformation" class="tw-flex tw-flex-col tw-gap-5 tw-p-5">
            <div class="tw-flex tw-flex-col tw-gap-[15px]">
                <div class="tw-w-full tw-flex tw-flex-col tw-gap-[5px]">
                    <label class="tw-text-[10px] tw-text-[#8B8E98] tw-font-semibold">
                        {{ 'Card holder full name' }}
                    </label>
                    <input :disabled="isAllValid" v-model="holderNameInput" :title="`Holder name`" placeholder="Enter your full name" maxlength="50" type="text" class="tw-w-auto tw-h-[40px] tw-px-4 tw-rounded-[9px] tw-outline-none tw-bg-[#F2F2F2] tw-border tw-border-transparent focus:tw-border-transparent focus:tw-shadow-[0px_0px_0px_2px_#242424] focus:tw-bg-transparent tw-transition-all tw-duration-300 tw-ease-[cubic-bezier(0.15,0.83,0.66,1)]">
                    <p v-if="errorOnHolderName" class="tw-text-red-500">
                        {{ errorOnHolderName }}
                    </p>
                </div>
        
                <div class="tw-w-full tw-flex tw-flex-col tw-gap-[5px]">
                    <label class="tw-text-[10px] tw-text-[#8B8E98] tw-font-semibold">
                        {{ 'Card Number' }}
                    </label>
                    <input :disabled="isAllValid" v-model="numberInput" :title="`Number`" placeholder="0000 0000 0000 0000" maxlength="19" type="text" class="tw-w-auto tw-h-[40px] tw-px-4 tw-rounded-[9px] tw-outline-none tw-bg-[#F2F2F2] tw-border tw-border-transparent focus:tw-border-transparent focus:tw-shadow-[0px_0px_0px_2px_#242424] focus:tw-bg-transparent tw-transition-all tw-duration-300 tw-ease-[cubic-bezier(0.15,0.83,0.66,1)]">
                    <p v-if="errorOnNumber" class="tw-text-red-500">
                        {{ errorOnNumber }}
                    </p>
                </div>
        
                <div class="tw-w-full tw-flex tw-flex-col tw-gap-[5px]">
                    <label for="expiry_cvv" class="tw-text-[10px] tw-text-[#8B8E98] tw-font-semibold">
                        {{ 'Expiry Date / CVV' }}
                    </label>
                    <div class="tw-grid tw-grid-cols-[4fr_2fr] tw-gap-[15px]">
                        <input :disabled="isAllValid" v-model="expiryDateInput" :title="`Expiry Date`" placeholder="06/25" maxlength="5" type="text" class="tw-w-full tw-h-[40px] tw-px-4 tw-rounded-[9px] tw-outline-none tw-bg-[#F2F2F2] tw-border tw-border-transparent focus:tw-border-transparent focus:tw-shadow-[0px_0px_0px_2px_#242424] focus:tw-bg-transparent tw-transition-all tw-duration-300 tw-ease-[cubic-bezier(0.15,0.83,0.66,1)]">
                        <input :disabled="isAllValid" v-model="cvvInput" :title="`CVV`" placeholder="CVV" maxlength="3" type="password" class="tw-w-full tw-h-[40px] tw-px-4 tw-rounded-[9px] tw-outline-none tw-bg-[#F2F2F2] tw-border tw-border-transparent focus:tw-border-transparent focus:tw-shadow-[0px_0px_0px_2px_#242424] focus:tw-bg-transparent tw-transition-all tw-duration-300 tw-ease-[cubic-bezier(0.15,0.83,0.66,1)]">
                    </div>
                    <p v-if="errorOnExpiryDate" class="tw-text-red-500">
                        {{ errorOnExpiryDate }}
                    </p>
                    <p v-if="errorOnCVV" class="tw-text-red-500">
                        {{ errorOnCVV }}
                    </p>
                </div>
            </div>
    
            <button :disabled="isAllValid" type="submit" class="tw-h-[55px] tw-rounded-[11px] tw-border-0 tw-outline-none tw-text-white tw-text-[13px] tw-font-bold tw-bg-gradient-to-b tw-from-[#363636] tw-via-[#1B1B1B] tw-to-black tw-shadow-[0px_0px_0px_0px_#FFFFFF,0px_0px_0px_0px_#000000] hover:tw-shadow-[0px_0px_0px_2px_#FFFFFF,0px_0px_0px_4px_#0000003a] tw-transition-all tw-duration-300 tw-ease-[cubic-bezier(0.15,0.83,0.66,1)] disabled:tw-opacity-50 disabled:tw-cursor-not-allowed">
                {{ 'Save' }}
            </button>
        </form>
    </div>

</template>

<script setup>

    import { defineEmits, ref } from 'vue';
    import { validateStringLength, validateName, validateCreditCardNumber, validateCreditCardExpiryDate, validateCreditCardCVV } from "../../../../../utils/validators.js";

    const emit = defineEmits(['validatedCreditCardInformation']);

    const isAllValid = ref(false);

    const holderNameInput = ref('');
    const numberInput = ref('');
    const expiryDateInput = ref('');
    const cvvInput = ref('');

    const errorOnHolderName = ref(null);
    const errorOnNumber = ref(null);
    const errorOnExpiryDate = ref(null);
    const errorOnCVV = ref(null);

    const validateHolderNameInput = (input) => {

        let data = {
            valid: false,
            message: []
        };

        const firstValidation = validateStringLength('Holder name', input, 4, 50);

        if(firstValidation.valid) {            
            
            const secondValidation = validateName('Holder name', input);

            if(secondValidation.valid) {

                data.valid = true;                

            } else {

                data.message.push(secondValidation.message);

            }

        } else {

            data.message.push(firstValidation.message);

        }

        return data;

    };

    const validateCreditCardNumberInput = (input) => {

        let data = {
            valid: false,
            message: []
        };

        const firstValidation = validateCreditCardNumber(input);

        if(firstValidation.valid) {

            data.valid = true;                

        } else {

            data.message.push(firstValidation.message);

        }

        return data;

    };

    const validateCreditCardExpiryDateInput = (input) => {

        let data = {
            valid: false,
            message: []
        };

        const firstValidation = validateCreditCardExpiryDate(input);

        if(firstValidation.valid) {

            data.valid = true;                

        } else {

            data.message.push(firstValidation.message);

        }

        return data;

    };

    const validateCreditCardCVVInput = (input) => {

        let data = {
            valid: false,
            message: []
        };

        const firstValidation = validateCreditCardCVV(input);

        if(firstValidation.valid) {

            data.valid = true;                

        } else {

            data.message.push(firstValidation.message);

        }

        return data;

    };

    const validateCreditCardInformation = () => {

        errorOnHolderName.value = null;
        errorOnNumber.value = null;
        errorOnExpiryDate.value = null;
        errorOnCVV.value = null;

        const nameValidation = validateHolderNameInput(holderNameInput.value);
        if (!nameValidation.valid) {
            errorOnHolderName.value = nameValidation.message.join(', ');
        }

        const numberValidation = validateCreditCardNumberInput(numberInput.value);
        if (!numberValidation.valid) {
            errorOnNumber.value = numberValidation.message.join(', ');
        }

        const expiryValidation = validateCreditCardExpiryDateInput(expiryDateInput.value);
        if (!expiryValidation.valid) {
            errorOnExpiryDate.value = expiryValidation.message.join(', ');
        }

        const cvvValidation = validateCreditCardCVVInput(cvvInput.value);
        if (!cvvValidation.valid) {
            errorOnCVV.value = cvvValidation.message.join(', ');
        }

        if (errorOnHolderName.value || errorOnNumber.value || errorOnExpiryDate.value || errorOnCVV.value) {
            isAllValid.value = false;
        } else {
            isAllValid.value = true;
            emit('validatedCreditCardInformation', {
                holderName: holderNameInput.value.trim(),
                number: numberInput.value.trim(),         
                expiryDate: expiryDateInput.value.trim(),
                cvv: cvvInput.value
            });
        }
        
    };

</script>