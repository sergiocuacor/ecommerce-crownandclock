<template>
        
        <form @submit.prevent="validateUserCredentials" class="container d-flex flex-column align-items-center justify-content-center h-100">

            <div class="p-4 rounded tw-bg-gray-200/75 border border-2 tw-border-gray-300/50 tw-space-y-2.5 w-100">

                <div class="tw-text-md">
                    <strong>{{ 'Your credentials' }}</strong>
                </div>

                <div class="form-floating">
                    <input v-model="emailInput" type="email" placeholder="example@example.com" required class="form-control" />
                    <label>{{ 'Email' }}</label>
                </div>

                <div class="form-floating">         
                    <input v-model="passwordInput" type="password" required class="form-control" />
                    <label>{{ 'Password' }}</label>
                </div>

                <p v-if="error != null">{{ error }}</p>

                <div class="d-flex flex-column align-items-center justify-content-center">
                    <button
                        type="submit"
                        :disabled="loading"
                        class="tw-bg-black tw-text-white tw-rounded-md tw-h-10 tw-flex tw-items-center tw-justify-center tw-px-1.5 tw-shadow-md hover:tw-bg-gray-800 tw-cursor-pointer tw-text-xl tw-transition tw-delay-150 tw-duration-300 tw-ease-in-out hover:tw--translate-y-1 hover:tw-scale-90 disabled:tw-bg-gray-700 mt-3">
                            <span v-if="loading" class="spinner-border spinner-border-sm me-2" aria-hidden="true"></span>
                            {{ loading ? 'Signing in...' : 'Sign in' }}
                    </button>
                </div>
                
            </div>

        </form>

</template>

<script setup>

    import { ref } from 'vue';
    import { useRouter } from 'vue-router';
    import apiClient from '../../services/api.js';
    import { useAuthStore } from '../../services/auth.js';

    const router = useRouter();
    const authStore = useAuthStore();
    
    const emailInput = ref('');
    const passwordInput = ref('');
    const loading = ref(false);
    const error = ref(null);

    const validateUserCredentials = async () => {

        const userCredentials = {
            email: emailInput.value,
            password: passwordInput.value
        };

        loading.value = true;
        error.value = null;

        const response = await apiClient.postLoginUser(userCredentials);

        if (response.success) {

            authStore.logInUser(response.data.token);            
            router.push('/home');

        } else {

            error.value = response.error.message;

        }

        loading.value = false;

    };

</script>  