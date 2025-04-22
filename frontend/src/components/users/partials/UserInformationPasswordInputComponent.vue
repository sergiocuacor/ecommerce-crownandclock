<template>

    <span v-if="!isEditing" class="tw-flex tw-items-center tw-justify-between tw-gap-2">
        <strong>{{ 'Password: ' }}</strong>
        <button
            v-if="!isEditing"
            @click="isEditing = true"
            type="submit"
            class="tw-flex tw-items-center tw-gap-2 tw-bg-black tw-text-white tw-px-3 tw-py-1.5 tw-rounded-md tw-text-sm tw-font-medium hover:tw-bg-gray-700 focus:tw-outline-none focus:tw-ring-2 focus:tw-ring-gray-500 active:tw-scale-95 tw-transition"
        >
            <i class="bi bi-pen-fill"></i>
            {{ 'Edit' }}
        </button>
    </span>

    <form v-else @submit.prevent="sendNewPassword" class="row tw-space-y-3">
        <div class="col-12">
            <label class="tw-pb-1">
                <strong>{{ 'New password: ' }}</strong>
            </label>
            <input 
                v-model="password" 
                type="password" 
                required 
                class="tw-w-full tw-px-2 tw-py-1 tw-border tw-border-gray-300 tw-rounded-md tw-shadow-sm focus:tw-outline-none focus:tw-ring-2 focus:tw-ring-gray-500 focus:tw-border-transparent tw-transition"
            />
        </div>
        <div class="col-12">
            <label class="tw-pb-1">
                <strong>{{ 'Confirm password: ' }}</strong>
            </label>
            <input 
                v-model="confirmPassword" 
                type="password" 
                required 
                class="tw-w-full tw-px-2 tw-py-1 tw-border tw-border-gray-300 tw-rounded-md tw-shadow-sm focus:tw-outline-none focus:tw-ring-2 focus:tw-ring-gray-500 focus:tw-border-transparent tw-transition"
            />
        </div>

        <div v-if="password && !isPasswordStrong" class="col-12 tw-text-red-500 tw-text-sm">
            {{ 'The password must be at least 8 characters long and include an uppercase letter, a lowercase letter, a number, and a symbol.' }}
        </div>

        <div v-if="passwordsMismatch" class="col-12 tw-text-red-500 tw-text-sm">
            {{ 'The passwords do not match.' }}
        </div>

        <div v-if="error != null" class="col-12 tw-text-red-500 tw-text-sm">
            {{ 'Error updating password, try later.' }}
        </div>

        <div class="col-12">
            <span class="tw-flex tw-items-center tw-justify-center tw-gap-2">
                <button
                    :disabled="!canSubmit || !loading"
                    type="submit" 
                    class="tw-flex tw-items-center tw-gap-2 tw-bg-black disabled:tw-bg-gray-500 tw-text-white tw-px-3 tw-py-1.5 tw-rounded-md tw-text-sm tw-font-medium hover:tw-bg-gray-700 focus:tw-outline-none focus:tw-ring-2 focus:tw-ring-gray-500 active:tw-scale-95 tw-transition"
                >
                    <i class="bi bi-check-square-fill"></i>
                    {{ 'Confirm' }}
                </button>
                <button
                    :disabled="!loading"
                    @click="isEditing = false"
                    type="button" 
                    class="tw-flex tw-items-center tw-gap-2 tw-bg-black disabled:tw-bg-gray-400 tw-text-white tw-px-3 tw-py-1.5 tw-rounded-md tw-text-sm tw-font-medium hover:tw-bg-gray-700 focus:tw-outline-none focus:tw-ring-2 focus:tw-ring-gray-500 active:tw-scale-95 tw-transition"
                >
                    <i class="bi bi-x-square-fill"></i>
                    {{ 'Cancel' }}
                </button>
            </span>
        </div>      

    </form>

</template>

<script setup>

    import { ref, computed } from 'vue';
    import apiClient from '../../../services/api.js';

    const props = defineProps({
        userId: {
            required: true
        }
    });

    const isEditing = ref(false);
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

    const sendNewPassword = async () => {

        if (!canSubmit.value) return        

        const newPassword = {
            password: password.value
        };

        loading.value = true;

        const response = await apiClient.putUserData(props.userId, newPassword);

        if (response.success) {

            error.value = null;
            isEditing.value = false;
            password.value = '';
            confirmPassword.value = '';

        } else {

            error.value = response.error.error;

        }

        loading.value = false;

    };

</script>