<template>

    <div v-if="!isAllValid" class="tw-w-fit tw-h-fit tw-bg-white tw-shadow-[0px_187px_75px_rgba(0,0,0,0.01),0px_105px_63px_rgba(0,0,0,0.05),0px_47px_47px_rgba(0,0,0,0.09),0px_12px_26px_rgba(0,0,0,0.1),0px_0px_0px_rgba(0,0,0,0.1)] tw-rounded-[26px] tw-max-w-[450px]">
        <form @submit.prevent="validateStripeInformation" class="tw-flex tw-flex-col tw-gap-5 tw-p-5">
            <div class="tw-flex tw-flex-col tw-gap-[15px]">
                <div class="tw-w-full tw-flex tw-flex-col tw-gap-[5px]">
                    <label class="tw-text-[10px] tw-text-[#8B8E98] tw-font-semibold">
                        {{ 'Email' }}
                    </label>
                    <input :disabled="isAllValid" v-model="emailInput" :title="`Holder name`" placeholder="Enter your full name" maxlength="50" type="email" class="tw-w-auto tw-h-[40px] tw-px-4 tw-rounded-[9px] tw-outline-none tw-bg-[#F2F2F2] tw-border tw-border-transparent focus:tw-border-transparent focus:tw-shadow-[0px_0px_0px_2px_#242424] focus:tw-bg-transparent tw-transition-all tw-duration-300 tw-ease-[cubic-bezier(0.15,0.83,0.66,1)]">
                    <p v-if="errorOnEmail" class="tw-text-red-500">
                        {{ errorOnEmail }}
                    </p>
                </div>
        
                <div class="tw-w-full tw-flex tw-flex-col tw-gap-[5px]">
                    <label class="tw-text-[10px] tw-text-[#8B8E98] tw-font-semibold">
                        {{ 'Password' }}
                    </label>
                    <input :disabled="isAllValid" v-model="passwordInput" :title="`Number`" placeholder="0000 0000 0000 0000" maxlength="19" type="password" class="tw-w-auto tw-h-[40px] tw-px-4 tw-rounded-[9px] tw-outline-none tw-bg-[#F2F2F2] tw-border tw-border-transparent focus:tw-border-transparent focus:tw-shadow-[0px_0px_0px_2px_#242424] focus:tw-bg-transparent tw-transition-all tw-duration-300 tw-ease-[cubic-bezier(0.15,0.83,0.66,1)]">
                    <p v-if="errorOnPassword" class="tw-text-red-500">
                        {{ errorOnPassword }}
                    </p>
                </div>
            </div>
    
            <button :disabled="isAllValid" type="submit" class="tw-h-[55px] tw-rounded-[11px] tw-border-0 tw-outline-none tw-text-white tw-text-[13px] tw-font-bold tw-bg-gradient-to-b tw-from-[#363636] tw-via-[#1B1B1B] tw-to-black tw-shadow-[0px_0px_0px_0px_#FFFFFF,0px_0px_0px_0px_#000000] hover:tw-shadow-[0px_0px_0px_2px_#FFFFFF,0px_0px_0px_4px_#0000003a] tw-transition-all tw-duration-300 tw-ease-[cubic-bezier(0.15,0.83,0.66,1)] disabled:tw-opacity-50 disabled:tw-cursor-not-allowed">
                {{ 'Log in' }}<i class="tw-ms-1 bi bi-stripe"></i>
            </button>
        </form>
    </div>

    <div v-else>

        <ButtonChangesTextOnHoverComponent @click="cancelStripePayment" :background="`tw-bg-gradient-to-b tw-from-gray-700 tw-to-black hover:tw-from-black hover:tw-to-red-900`" :shadow="`tw-shadow-[0px_4px_8px_0_rgba(0,0,0,.70)]`" :text1="`You're logged on your PayPal account`" :icon1="`bi-stripe`" :text2="`Log out your Stripe account`" :icon2="`bi-stripe`" />

    </div>

</template>

<script setup>

    import { defineEmits, ref } from 'vue';
    import { validateEmail, validateStringLength } from "../../../../../utils/validators.js";

    const emit = defineEmits(['validatedCreditCardInformation']);

    const isAllValid = ref(false);

    const emailInput = ref('');
    const passwordInput = ref('');

    const errorOnEmail = ref(null);
    const errorOnPassword = ref(null);

    const validateEmailInput = (input) => {

        let data = {
            valid: false,
            message: []
        };

        const firstValidation = validateEmail('Email', input);

        if(firstValidation.valid) {

            data.valid = true;                

        } else {

            data.message.push(firstValidation.message);

        }

        return data;

    };

    const validatePasswordInput = (input) => {

        let data = {
            valid: false,
            message: []
        };

        const firstValidation = validateStringLength('Password', input, 4, 50);

        if(firstValidation.valid) {

            data.valid = true;                

        } else {

            data.message.push(firstValidation.message);

        }

        return data;

    };

    const cancelStripePayment = () => {
        isAllValid.value = false;
        emit('validatedStripeInformation', false);
    };

    const validateStripeInformation = () => {

        errorOnEmail.value = null;
        errorOnPassword.value = null;

        const emailValidation = validateEmailInput(emailInput.value);
        if (!emailValidation.valid) {
            errorOnEmail.value = emailValidation.message.join(', ');
        }

        const numberValidation = validatePasswordInput(passwordInput.value);
        if (!numberValidation.valid) {
            errorOnPassword.value = numberValidation.message.join(', ');
        }

        if (errorOnEmail.value || errorOnPassword.value) {
            isAllValid.value = false;
        } else {
            isAllValid.value = true;
            emit('validatedStripeInformation', true);
        }
        
    };

</script>