<template>
        
        <form @submit.prevent="validateUserCredentials" class="container d-flex flex-column align-items-center justify-content-center h-100">

            <div class="p-4 rounded tw-bg-gray-200/75 border border-2 tw-border-gray-300/50 tw-space-y-2.5 w-100">

                <div class="text-center">
                    {{ 'Login' }}
                </div>

                <div class="">
                    <label for="emailInput" class="form-label">{{ 'Email' }}</label>
                    <input v-model="emailInput" type="email" placeholder="example@example.com" required class="form-control" />
                </div>

                <div class="">
                    <label for="passwordInput" class="form-label">{{ 'Contraseña' }}</label>
                    <input v-model="passwordInput" type="password" placeholder="Contraseña" required class="form-control" />
                </div>

                <p v-if="error != null">{{ error }}</p>

                <div class="d-flex flex-column align-items-center justify-content-center">
                    <button type="submit" class="btn btn-primary mt-3">
                        {{ 'Iniciar sesión' }}
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
    const loading = ref(true);
    const error = ref(null);

    const validateUserCredentials = async () => {

        const userCredentials = {
            email: emailInput.value,
            password: passwordInput.value
        };

        loading.value = true;

        const response = await apiClient.postLoginUser(userCredentials);

        if (response.success) {

            authStore.logInUser(response.data.token);
            error.value = null;
            router.push('/products');

        } else {

            error.value = response.error.error;

        }

        loading.value = false;

    };

</script>  