<template>

    <div class="tw-flex tw-flex-col">

         <span>
            <strong>{{ props.text }}</strong>

            <span v-if="!isEditing">{{ props.value }}</span>

            <input v-if="isEditing" v-model="inputValue" :type="props.inputType" :placeholder="props.value">
        </span>

        <span v-if="error != null">
            {{ 'Error updating' }}
        </span>
    </div>
   

    <span v-if="isEditing" class="tw-flex tw-items-center tw-gap-2">
        <button            
            @click="executeConfirmButtonActions"
            type="button" 
            class="tw-flex tw-items-center tw-gap-2 tw-bg-black tw-text-white tw-px-3 tw-py-1.5 tw-rounded-md tw-text-sm tw-font-medium hover:tw-bg-gray-700 focus:tw-outline-none focus:tw-ring-2 focus:tw-ring-gray-500 active:tw-scale-95 tw-transition"
        >
            <i class="bi bi-check-square-fill"></i>
            {{ 'Confirm' }}
        </button>
        <button
            @click="isEditing = false"
            type="button" 
            class="tw-flex tw-items-center tw-gap-2 tw-bg-black tw-text-white tw-px-3 tw-py-1.5 tw-rounded-md tw-text-sm tw-font-medium hover:tw-bg-gray-700 focus:tw-outline-none focus:tw-ring-2 focus:tw-ring-gray-500 active:tw-scale-95 tw-transition"
        >
            <i class="bi bi-x-square-fill"></i>
            {{ 'Cancel' }}
        </button>
    </span>
    
    <button
        v-if="!isEditing"
        @click="isEditing = true"
        type="button" 
        class="tw-flex tw-items-center tw-gap-2 tw-bg-black tw-text-white tw-px-3 tw-py-1.5 tw-rounded-md tw-text-sm tw-font-medium hover:tw-bg-gray-700 focus:tw-outline-none focus:tw-ring-2 focus:tw-ring-gray-500 active:tw-scale-95 tw-transition"
    >
        <i class="bi bi-pen-fill"></i>
        {{ 'Edit' }}
    </button>

</template>

<script setup>

    import { ref } from "vue";
    import apiClient from "../../../services/api.js"

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
        }
    });

    const isEditing = ref(false);
    const loading = ref(false);
    const error = ref(null);
    const inputValue = ref('');

    const updateUserData = async (inputValue) => {

        loading.value = true;

        const response = await apiClient.putUserData(props.userId, inputValue);

        if(response.success) {

            error.value = null;

        } else {

            error.value = 'Error updating field';

        }

        loading.value = false;

    }
    
    const executeConfirmButtonActions = () => {

        isEditing.value = false;
        const inputValue = inputValue.value.trim();
        
        validateInputValue(inputValue.value)
    
    }

    const validateInputValue = (inputValue) => {

        let data = {};

        const characterLength = inputValue.length;

        switch (props.validationId) {
            case "firstName":

                if(characterLength > 50) {

                    error.value = "Name surpasses permitted length of 50 characters.";

                } else {

                    const regex = /^[\p{L}.'`]+$/u.test(inputValue);

                    if(regex) {

                        updateUserData(inputValue);

                    } else {

                        error.value = "This field only allow letters and symbols (., ', `)"

                    }

                }

                break;
            case "lastName":
                
                if(characterLength > 50) {

                    error.value = "Last name surpasses permitted length of 50 characters.";

                } else {

                    const regex = /^[\p{L}.'`]+$/u.test(inputValue);

                    if(regex) {

                        updateUserData(inputValue);

                    } else {

                        error.value = "This field only allow letters and symbols (., ', `)"

                    }

                }

                break;
            case "email":
                // lógica para validar email
                break;
            case "phoneNumber":

                if(characterLength === 9) {


                } else {


                }

                break;
            case "streetAddress":
                // lógica para validar streetAddress
                break;
            case "city":
                // lógica para validar city
                break;
            case "state":
                // lógica para validar state
                break;
            case "country":
                // lógica para validar country
                break;
            case "postalCode":

                if(characterLength === 5) {


                } else {


                }
                
                break;
            default:
                // lógica por defecto, si no coincide ningún caso
                break;
        }

    };

</script>