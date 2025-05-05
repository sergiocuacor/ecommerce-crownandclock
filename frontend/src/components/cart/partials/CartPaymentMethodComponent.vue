<template>

    <div v-if="authStore.token">

        <div class="ui-radio-inputs tw-mb-2">

            <label :title="`Credit Card`">
                <input
                    @change="handleSelection"
                    v-model="paymentMethod"
                    value="credit-card"
                    type="radio"
                    class="ui-radio-input"
                />
                <span class="ui-radio-tile">
                <span class="ui-radio-icon">
                    <i class="bi bi-credit-card-2-front-fill"></i>
                </span>
                <span class="ui-radio-label">
                    {{ 'Credit Card' }}
                </span>
                </span>
            </label>
        
            <label :title="`Paypal`">
                <input
                    @change="handleSelection"
                    v-model="paymentMethod"
                    value="paypal"
                    type="radio"                
                    class="ui-radio-input"
                />
                <span class="ui-radio-tile">
                <span class="ui-radio-icon">
                    <i class="bi bi-paypal"></i>
                </span>
                <span class="ui-radio-label">
                    {{ 'Paypal' }}
                </span>
                </span>
            </label>

            <label :title="`Stripe`">
                <input
                @change="handleSelection"
                    v-model="paymentMethod"
                    value="stripe"
                    type="radio"
                    class="ui-radio-input"
                />
                <span class="ui-radio-tile">
                <span class="ui-radio-icon">
                    <i class="bi bi-stripe"></i>
                </span>
                <span class="ui-radio-label">
                    {{ 'Stripe' }}
                </span>
                </span>
            </label>

        </div>

        <CreditCardComponent v-if="paymentMethod === 'credit-card'" @validatedPaymentMethod="validatePaymentMethod"/>
        <PaypalComponent v-if="paymentMethod === 'paypal'" @validatedPaymentMethod="validatePaymentMethod"/>
        <StripeComponent v-if="paymentMethod === 'stripe'" @validatedPaymentMethod="validatePaymentMethod"/>

    </div>

    <div v-else class="tw-flex tw-flex-col tw-gap-2 tw-p-2 tw-bg-gray-400/25 tw-rounded-[10px] tw-mt-2">
        <p class="tw-text-[14px] tw-font-semibold tw-text-black">
            {{ 'To select a payment method, you must be logged in.' }}
        </p>
    </div>    

</template>  

<script setup>

    import { ref } from 'vue';
    import { useAuthStore } from '../../../services/auth.js';

    const authStore = useAuthStore();
    const emit = defineEmits(['validatedPaymentMethod']);

    const paymentMethod = ref('credit-card');

    function handleSelection(event) {
        emit('validatedPaymentMethod', false);
    }

    const validatePaymentMethod = (isValid) => {
        emit('validatedPaymentMethod', isValid);
    };

</script>

<style scoped>

    .ui-radio-inputs {
        display: flex;
        justify-content: center;
        align-items: center;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
    }

    .ui-radio-inputs > * {
        margin: 6px;
    }

    .ui-radio-input:checked + .ui-radio-tile {
        border-color: rgb(0, 0, 0);
        box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
        color: rgb(0, 0, 0);
    }

    .ui-radio-input:checked + .ui-radio-tile:before {
        transform: scale(1);
        opacity: 1;
        background-color: rgb(0, 0, 0);
        border-color: rgb(0, 0, 0);
    }

    .ui-radio-input:checked + .ui-radio-tile .ui-radio-icon i {
        fill: rgb(0, 0, 0);
    }

    .ui-radio-input:checked + .ui-radio-tile .ui-radio-label {
        color: rgb(0, 0, 0);
    }

    .ui-radio-input:focus + .ui-radio-tile {
        border-color: rgb(0, 0, 0);
        box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1), 0 0 0 4px rgb(0, 0, 0, 0.25);
    }

    .ui-radio-input:focus + .ui-radio-tile:before {
        transform: scale(1);
        opacity: 1;
    }

    .ui-radio-tile {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        width: 80px;
        min-height: 80px;
        border-radius: 0.5rem;
        border: 2px solid rgb(0, 0, 0, 0.5);
        background-color: rgb(255, 255, 255);
        box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
        transition: 0.15s ease;
        cursor: pointer;
        position: relative;
    }

    .ui-radio-tile:before {
        content: "";
        position: absolute;
        display: block;
        width: 0.75rem;
        height: 0.75rem;
        border: 2px solid rgb(0, 0, 0, 0.5);
        background-color: rgb(255, 255, 255);
        border-radius: 50%;
        top: 0.25rem;
        left: 0.25rem;
        opacity: 0;
        transform: scale(0);
        transition: 0.25s ease;
    }

    .ui-radio-tile:hover {
        border-color: rgb(0, 0, 0);
    }

    .ui-radio-tile:hover:before {
        transform: scale(1);
        opacity: 1;
    }

    .ui-radio-icon svg {
        width: 2rem;
        height: 2rem;
        fill: rgb(73, 73, 73);
    }

    .ui-radio-label {
        color: rgb(112, 112, 112);
        transition: 0.375s ease;
        text-align: center;
        font-size: 13px;
    }

    .ui-radio-input {
        clip: rect(0 0 0 0);
        -webkit-clip-path: inset(100%);
        clip-path: inset(100%);
        height: 1px;
        overflow: hidden;
        position: absolute;
        white-space: nowrap;
        width: 1px;
    }

</style>