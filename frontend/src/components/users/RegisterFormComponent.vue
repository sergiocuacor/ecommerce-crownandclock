<template>

    <form @submit.prevent="sendUserRegistrationCredentials" class="container d-flex flex-column align-items-center justify-content-center h-100">

        <div class="row p-4 rounded tw-bg-gray-200/75 border border-2 tw-border-gray-300/50 tw-space-y-2.5 w-100">

            <div class="text-center">
                {{ 'Sign Up Form' }}
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

            <div v-if="password && !isPasswordStrong" class="col-12 text-red-500 text-sm">
                {{ 'The password must be at least 8 characters long and include an uppercase letter, a lowercase letter, a number, and a symbol.' }}
            </div>

            <div v-if="passwordsMismatch" class="col-12 text-red-500 text-sm">
                {{ 'The passwords do not match.' }}
            </div>

            <div class="col-12 col-lg-6">
                <div class="form-floating">
                    <input v-model="form.address.streetAddress" type="text" required class="form-control" />
                    <label>{{ 'Address' }}</label>
                </div>
            </div>
            
            <div class="col-12 col-lg-6">
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

            <div class="d-flex flex-column align-items-center justify-content-center" :disabled="!canSubmit">
                <button type="submit" class="btn btn-primary mt-3">
                    {{ 'Sign up' }}
                </button>
            </div>
        </div>

    </form>

</template>

<script setup>

    import { reactive, ref, computed } from 'vue';
    import { useRouter } from 'vue-router';
    import apiClient from '../../services/api.js';

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
    const loading = ref(true);
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

    const sendUserRegistrationCredentials = async () => {

        if (!canSubmit.value) return        

        const userRegistrationCredentials = {
            name: `${form.firstName} ${form.lastName}`,
            firstName: form.firstName,
            lastName: form.lastName,
            email: form.email,
            password: password.value,
            phoneNumber: form.phoneNumber,
            address: { ...form.address },
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

    };

</script>