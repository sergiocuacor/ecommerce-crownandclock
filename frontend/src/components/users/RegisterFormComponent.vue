<template>

    <form @submit.prevent="sendUserRegistrationCredentials" class="container d-flex flex-column align-items-center justify-content-center tw-font-serif">

        <div class="row p-4 rounded tw-bg-gray-200/75 border border-2 tw-border-gray-300/50 tw-space-y-2.5">

            <span v-if="error != null" class="col-12 tw-text-red-500 tw-text-sm">
                {{ error }}
            </span>

            <div class="col-12 tw-text-md">
                <strong>{{ 'Your Information' }}</strong>                
            </div>

            <div class="col-12 col-lg-3">
                <div class="form-floating">                    
                    <input v-model="form.firstName" type="text" required class="form-control" />
                    <label>{{ 'Name' }}</label>
                </div>
            </div>

            <div class="col-12 col-lg-3">
                <div class="form-floating">                    
                    <input v-model="form.lastName" type="text" required class="form-control" />
                    <label>{{ 'Last Name' }}</label>
                </div>
            </div>

            <div class="col-12 col-lg-6">
                <div class="form-floating">
                    <input v-model="form.email" type="email" required class="form-control" />
                    <label>{{ 'Email' }}</label>
                </div>
            </div>

            <div class="col-12 col-lg-4">
                <div class="form-floating">                
                    <input v-model="form.phoneNumber" type="tel" required class="form-control" />
                    <label>{{ 'Phone number' }}</label>
                </div>
            </div>

            <div class="col-12 col-lg-4">
                <div class="form-floating">                    
                    <input v-model="password" type="password" required class="form-control" />
                    <label>{{ 'Password' }}</label>
                </div>
            </div>

            <div class="col-12 col-lg-4">
                <div class="form-floating">                    
                    <input v-model="confirmPassword" type="password" required class="form-control" />
                    <label>{{ 'Confirm password' }}</label>
                </div>
            </div>

            <span v-if="password && !isPasswordStrong" class="col-12 tw-text-red-500 tw-text-sm">
                {{ 'The password must be at least 8 characters long and include an uppercase letter, a lowercase letter, a number, and a symbol.' }}
            </span>

            <span v-if="passwordsMismatch" class="col-12 tw-text-red-500 tw-text-sm">
                {{ 'The passwords do not match.' }}
            </span>

            <span v-if="hasValidationMessages(validations.firstName)" class="col-12 tw-text-red-500 tw-text-sm">
                <p v-for="(msg, index) in validations.firstName.message" :key="index">{{ msg }}</p>
            </span>

            <span v-if="hasValidationMessages(validations.lastName)" class="col-12 tw-text-red-500 tw-text-sm">
                <p v-for="(msg, index) in validations.lastName.message" :key="index">{{ msg }}</p>
            </span>

            <span v-if="hasValidationMessages(validations.email)" class="col-12 tw-text-red-500 tw-text-sm">
                <p v-for="(msg, index) in validations.email.message" :key="index">{{ msg }}</p>
            </span>

            <span v-if="hasValidationMessages(validations.phoneNumber)" class="col-12 tw-text-red-500 tw-text-sm">
                <p v-for="(msg, index) in validations.phoneNumber.message" :key="index">{{ msg }}</p>
            </span>

            <div class="col-12 tw-text-sm">
                <strong>{{ 'ADDRESS' }}</strong>              
            </div>

            <div class="col-12 col-lg-9">
                <div class="form-floating">
                    <input v-model="form.address.streetAddress" type="text" required class="form-control" />
                    <label>{{ 'Address' }}</label>
                </div>
            </div>
            
            <div class="col-12 col-lg-3">
                <div class="form-floating">
                    <input v-model="form.address.postalCode" type="text" required class="form-control" />
                    <label>{{ 'Postal code' }}</label>
                </div>
            </div>  

            <div class="col-12 col-lg-4">
                <div class="form-floating">
                    <input v-model="form.address.city" type="text" required class="form-control" />
                    <label>{{ 'City' }}</label>
                </div>
            </div>
            
            <div class="col-12 col-lg-4">
                <div class="form-floating">
                    <input v-model="form.address.state" type="text" required class="form-control" />
                    <label>{{ 'State' }}</label>
                </div>
            </div>

            <div class="col-12 col-lg-4">
                <div class="form-floating">
                    <input v-model="form.address.country" type="text" required class="form-control" />
                    <label>{{ 'Country' }}</label>
                </div>
            </div>

            <span v-if="hasValidationMessages(validations.address.streetAddress)" class="col-12 tw-text-red-500 tw-text-sm">
                <p v-for="(msg, index) in validations.address.streetAddress.message" :key="index">{{ msg }}</p>
            </span>

            <span v-if="hasValidationMessages(validations.address.postalCode)" class="col-12 tw-text-red-500 tw-text-sm">
                <p v-for="(msg, index) in validations.address.postalCode.message" :key="index">{{ msg }}</p>
            </span>

            <span v-if="hasValidationMessages(validations.address.city)" class="col-12 tw-text-red-500 tw-text-sm">
                <p v-for="(msg, index) in validations.address.city.message" :key="index">{{ msg }}</p>
            </span>

            <span v-if="hasValidationMessages(validations.address.state)" class="col-12 tw-text-red-500 tw-text-sm">
                <p v-for="(msg, index) in validations.address.state.message" :key="index">{{ msg }}</p>
            </span>

            <span v-if="hasValidationMessages(validations.address.country)" class="col-12 tw-text-red-500 tw-text-sm">
                <p v-for="(msg, index) in validations.address.country.message" :key="index">{{ msg }}</p>
            </span>

            <div class="d-flex flex-column align-items-center justify-content-center">
                <button
                    type="submit"
                    :disabled="!canSubmit || loading"
                    class="tw-bg-black tw-text-white tw-rounded-md tw-h-10 tw-flex tw-items-center tw-justify-center tw-px-1.5 tw-shadow-md hover:tw-bg-gray-800 tw-cursor-pointer tw-text-xl tw-transition tw-delay-150 tw-duration-300 tw-ease-in-out hover:tw--translate-y-1 hover:tw-scale-90 disabled:tw-bg-gray-700 mt-3"
                >
                    <span v-if="loading" class="spinner-border spinner-border-sm me-2" aria-hidden="true"></span>
                    {{ loading ? 'Signing up...' : 'Sign up' }}
                </button>
            </div>

        </div>

    </form>

</template>

<script setup>

    import { reactive, ref, computed } from 'vue';
    import { useRouter } from 'vue-router';
    import apiClient from '../../services/api.js';
    import { validateStringLength, validateName, validateEmail, validatePhoneNumber, validateStreetAddress, validatePostalCode } from "../../utils/validators.js";

    const router = useRouter();    
    
    const form = reactive({
        firstName: '',
        lastName: '',
        email: '',
        phoneNumber: '',
        address: {
            streetAddress: '',
            city: '',
            state: '',
            country: '',
            postalCode: '',
        },
    });    
    const loading = ref(false);
    const error = ref(null);

    const password = ref('');
    const confirmPassword = ref('');    
    const isPasswordStrong = computed(() =>
        /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^A-Za-z\d]).{8,}$/.test(password.value)
    );    
    const passwordsMismatch = computed(() => password.value !== confirmPassword.value);    
    const canSubmit = computed(() =>
        isPasswordStrong.value && !passwordsMismatch.value
    );
    const validations = reactive({
        firstName: null,
        lastName: null,
        email: null,
        phoneNumber: null,
        address: {
            streetAddress: null,
            city: null,
            state: null,
            country: null,
            postalCode: null,
        },
    });
    const hasValidationMessages = (field) => field && Array.isArray(field.message) && field.message.length > 0;

    const validateFirstNameInput = (input) => {

        let data = {
            valid: false,
            message: []
        };

        const firstValidation = validateStringLength('First name', input, 4, 50);

        if(firstValidation.valid) {            
            
            const secondValidation = validateName('First name', input);

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

    const validateLastNameInput = (input) => {

        let data = {
            valid: false,
            message: []
        };
                
        const firstValidation = validateStringLength('Last name', input, 2, 50);

        if(firstValidation.valid) {
            
            const secondValidation = validateName('Last name', input);

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

    const validatePhoneNumberInput = (input) => {

        let data = {
            valid: false,
            message: []
        };

        const firstValidation = validatePhoneNumber('Phone number', input);

        if(firstValidation.valid) {

            data.valid = true;                

        } else {

            data.message.push(firstValidation.message);

        }

        return data;

    };

    const validateStreetAddressInput = (input) => {

        let data = {
            valid: false,
            message: []
        };

        const firstValidation = validateStreetAddress('Street address', input);

        if(firstValidation.valid) {

            data.valid = true;                

        } else {

            data.message.push(firstValidation.message);

        }

        return data;

    };

    const validateCityInput = (input) => {

        let data = {
            valid: false,
            message: []
        };

        const firstValidation = validateStringLength('City', input, 3, 50);

        if(firstValidation.valid) {
            
            const secondValidation = validateName('City', input);

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

    const validateStateInput = (input) => {

        let data = {
            valid: false,
            message: []
        };

        const firstValidation = validateStringLength('State', input, 4, 50);

        if(firstValidation.valid) {
            
            const secondValidation = validateName('State', input);

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

    const validateCountryInput = (input) => {

        let data = {
            valid: false,
            message: []
        };

        const firstValidation = validateStringLength('Country', input, 4, 50);

        if(firstValidation.valid) {
            
            const secondValidation = validateName('Country', input);

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

    const validatePostalCodeInput = (input) => {

        let data = {
            valid: false,
            message: []
        };

        const firstValidation = validatePostalCode('Postal code', input);

        if(firstValidation.valid) {

            data.valid = true;                

        } else {

            data.message.push(firstValidation.message);

        }

        return data;
                
    };

    const validationMap = {
        streetAddress: validateStreetAddressInput,
        city: validateCityInput,
        state: validateStateInput,
        country: validateCountryInput,
        postalCode: validatePostalCodeInput,
    };

    const validateFields = () => {
        validations.firstName = validateFirstNameInput(form.firstName);
        validations.lastName = validateLastNameInput(form.lastName);
        validations.email = validateEmailInput(form.email);
        validations.phoneNumber = validatePhoneNumberInput(form.phoneNumber);

        const addressFields = ['streetAddress', 'city', 'state', 'country', 'postalCode'];

        addressFields.forEach((field) => {
            validations.address[field] = validationMap[field](form.address[field]);
        });
    };

    const sendUserRegistrationCredentials = async () => {

        if (!canSubmit.value) return;

        validateFields();
        
        const isAllValid =
            validations.firstName.valid &&
            validations.lastName.valid &&
            validations.email.valid &&
            validations.phoneNumber.valid &&
            validations.address.streetAddress.valid &&
            validations.address.city.valid &&
            validations.address.state.valid &&
            validations.address.country.valid &&
            validations.address.postalCode.valid &&
            canSubmit.value;

        if (isAllValid) {

            const userRegistrationCredentials = {
                name: `${form.firstName.trim()} ${form.lastName.trim()}`,
                firstName: form.firstName.trim(),
                lastName: form.lastName.trim(),
                email: form.email.trim(),
                password: password.value,
                phoneNumber: form.phoneNumber.trim(),
                address: { 
                    streetAddress: form.address.streetAddress.trim(),
                    city: form.address.city.trim(),
                    state: form.address.state.trim(),
                    country: form.address.country.trim(),
                    postalCode: form.address.postalCode.trim()
                },
                admin: false
            };

            loading.value = true;

            const response = await apiClient.postRegisterUser(userRegistrationCredentials);

            if (response.success) {

                error.value = null;
                router.push('/login');

            } else {

                error.value = response.error.error;

            }

            loading.value = false;

        } else {

            error.value = 'Please fill in all fields correctly.';

        }

    };

</script>