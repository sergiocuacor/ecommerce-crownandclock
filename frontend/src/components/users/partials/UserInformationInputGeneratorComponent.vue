<template>

    <div class="tw-flex tw-flex-col">

        <span>
            <strong>{{ props.text }}</strong>

            <span v-if="!isEditing">{{ props.value }}</span>

            <input v-if="isEditing" v-model="inputValue" :type="props.inputType" :placeholder="props.value">
        </span>

        <span v-if="error != null" class="tw-text-red-500 tw-text-sm">
            {{ 'Error updating' }}
        </span>
        <span v-if="inputValidationErrors.length > 0" class="tw-text-red-500 tw-text-sm">
            <p v-for="err, index in inputValidationErrors" :key="index">{{ err }}</p>
        </span>
    </div>

    <span v-if="isEditing" class="tw-flex tw-items-center tw-w-full sm:tw-w-auto tw-gap-2">
        <button            
            @click="executeConfirmButtonActions"
            type="button" 
            class="tw-flex tw-items-center tw-justify-center tw-w-full sm:tw-w-auto tw-gap-2 tw-bg-black tw-text-white tw-px-3 tw-py-1.5 tw-rounded-md tw-text-sm tw-font-medium hover:tw-bg-gray-700 focus:tw-outline-none focus:tw-ring-2 focus:tw-ring-gray-500 active:tw-scale-95 tw-transition"
        >
            <i class="bi bi-check-square-fill"></i>
            {{ 'Confirm' }}
        </button>
        <button
            @click="isEditing = false"
            type="button" 
            class="tw-flex tw-items-center tw-justify-center tw-w-full sm:tw-w-auto tw-gap-2 tw-bg-black tw-text-white tw-px-3 tw-py-1.5 tw-rounded-md tw-text-sm tw-font-medium hover:tw-bg-gray-700 focus:tw-outline-none focus:tw-ring-2 focus:tw-ring-gray-500 active:tw-scale-95 tw-transition"
        >
            <i class="bi bi-x-square-fill"></i>
            {{ 'Cancel' }}
        </button>
    </span>
    
    <button
        v-if="!isEditing"
        @click="isEditing = true"
        type="button" 
        class="tw-flex tw-items-center tw-justify-center tw-w-full sm:tw-w-auto tw-gap-2 tw-bg-black tw-text-white tw-px-3 tw-py-1.5 tw-rounded-md tw-text-sm tw-font-medium hover:tw-bg-gray-700 focus:tw-outline-none focus:tw-ring-2 focus:tw-ring-gray-500 active:tw-scale-95 tw-transition"
    >
        <i class="bi bi-pen-fill"></i>
        {{ 'Edit' }}
    </button>

</template>

<script setup>

    import { ref } from "vue";
    import apiClient from "../../../services/api.js"
    import { validateStringLength, validateName, validateEmail, validatePhoneNumber, validateStreetAddress, validatePostalCode } from "../../../utils/validators.js";
    
    const emit = defineEmits(['updatedField']); 
    const props = defineProps({
        text: {
            type: String,
            required: true
        },
        inputType: {
            type: String,
            required: true
        },
        value: {
            required: true
        },
        validationId: {
            type: String,                     
            required: true
        },
        userId: {
            required: true
        }
    });

    const isEditing = ref(false);
    const loading = ref(false);
    const error = ref(null);
    const inputValidationErrors = ref([]);
    const inputValue = ref('');

    const updateUserData = async (input) => {

        loading.value = true;

        const response = await apiClient.putUserData(props.userId, { ...input, enabled: true });

        if(response.success) {

            error.value = null;
            emit('updatedField');

        } else {

            error.value = 'Error updating field';

        }

        loading.value = false;

    }
    
    const executeConfirmButtonActions = () => {

        isEditing.value = false;
        const input = inputValue.value.trim();        
        validateInputValue(input);
    
    }

    const validateInputValue = (input) => {

        inputValidationErrors.value = [];
        let firstValidation;
        let secondValidation;

        switch (props.validationId) {

            case "firstName":

                firstValidation = validateStringLength('First name', input, 4, 50);

                if(firstValidation.valid) {

                    inputValidationErrors.value = [];
                    secondValidation = validateName('First name', input);

                    if(secondValidation.valid) {

                        inputValidationErrors.value = [];
                        updateUserData({ firstName: input });

                    } else {

                        inputValidationErrors.value.push(secondValidation.message);

                    }

                } else {

                    inputValidationErrors.value.push(firstValidation.message);

                }

                break;
            case "lastName":
                
                firstValidation = validateStringLength('Last name', input, 2, 50);

                if(firstValidation.valid) {

                    inputValidationErrors.value = [];
                    secondValidation = validateName('Last name', input);

                    if(secondValidation.valid) {

                        inputValidationErrors.value = [];
                        updateUserData({ lastName: input });

                    } else {

                        inputValidationErrors.value.push(secondValidation.message);

                    }

                } else {

                    inputValidationErrors.value.push(firstValidation.message);

                }

                break;
            case "email":

                firstValidation = validateEmail('Email', input);

                if(firstValidation.valid) {

                    inputValidationErrors.value = [];
                    updateUserData({ email: input });

                } else {

                    inputValidationErrors.value.push(firstValidation.message);

                }

                break;
            case "phoneNumber":

                firstValidation = validatePhoneNumber('Phone number', input);

                if(firstValidation.valid) {

                    inputValidationErrors.value = [];
                    updateUserData({ phoneNumber: input });

                } else {

                    inputValidationErrors.value.push(firstValidation.message);

                }

                break;
            case "streetAddress":

                firstValidation = validateStreetAddress('Street address', input);

                if(firstValidation.valid) {

                    inputValidationErrors.value = [];
                    updateUserData({ address: { streetAddress: input } });

                } else {

                    inputValidationErrors.value.push(firstValidation.message);

                }

                break;
            case "city":

                firstValidation = validateStringLength('City', input, 3, 50);

                if(firstValidation.valid) {

                    inputValidationErrors.value = [];
                    secondValidation = validateName('City', input);

                    if(secondValidation.valid) {

                        inputValidationErrors.value = [];
                        updateUserData({ address: { city: input } });

                    } else {

                        inputValidationErrors.value.push(secondValidation.message);

                    }

                } else {

                    inputValidationErrors.value.push(firstValidation.message);

                }

                break;
            case "state":

                firstValidation = validateStringLength('State', input, 4, 50);

                if(firstValidation.valid) {

                    inputValidationErrors.value = [];
                    secondValidation = validateName('State', input);

                    if(secondValidation.valid) {

                        inputValidationErrors.value = [];
                        updateUserData({ address: { state: input } });

                    } else {

                        inputValidationErrors.value.push(secondValidation.message);

                    }

                } else {

                    inputValidationErrors.value.push(firstValidation.message);

                }

                break;
            case "country":

                firstValidation = validateStringLength('Country', input, 4, 50);

                if(firstValidation.valid) {

                    inputValidationErrors.value = [];
                    secondValidation = validateName('Country', input);

                    if(secondValidation.valid) {

                        inputValidationErrors.value = [];
                        updateUserData({ address: { country: input } });

                    } else {

                        inputValidationErrors.value.push(secondValidation.message);

                    }

                } else {

                    inputValidationErrors.value.push(firstValidation.message);

                }

                break;
            case "postalCode":

                firstValidation = validatePostalCode('Postal code', input);

                if(firstValidation.valid) {

                    inputValidationErrors.value = [];
                    updateUserData({ address: { postalCode: input } });

                } else {

                    inputValidationErrors.value.push(firstValidation.message);

                }
                
                break;
            default:

                inputValidationErrors.value.push('Error validating input value');

                break;
        }

        inputValue.value = '';

    };

</script>