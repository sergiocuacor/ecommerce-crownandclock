<template>

    <div v-if="authStore.token" class="tw-flex tw-flex-col tw-gap-2 tw-p-2 tw-mt-2 tw-bg-gray-400/25 tw-rounded-[10px]">
        <p class="tw-text-black">
            <span class="tw-text-[15px] tw-font-bold">
                {{ 'Street address: ' }}
            </span>
            <span class="tw-text-[14px] tw-font-semibold">
                {{ address.streetAddress }}
            </span>
        </p>
        <p class="tw-text-black">
            <span class="tw-text-[15px] tw-font-bold">
                {{ 'City: ' }}
            </span>
            <span class="tw-text-[14px] tw-font-semibold">
                {{ address.city }}
            </span>
        </p>
        <p class="tw-text-black">
            <span class="tw-text-[15px] tw-font-bold">
                {{ 'State: ' }}
            </span>
            <span class="tw-text-[14px] tw-font-semibold">
                {{ address.state }}
            </span>
        </p>
        <p class="tw-text-black">
            <span class="tw-text-[15px] tw-font-bold">
                {{ 'Country: ' }}
            </span>
            <span class="tw-text-[14px] tw-font-semibold">
                {{ address.country }}
            </span>
        </p>
        <p class="tw-text-black">
            <span class="tw-text-[15px] tw-font-bold">
                {{ 'Postal code: ' }}
            </span>
            <span class="tw-text-[14px] tw-font-semibold">
                {{ address.postalCode }}
            </span>
        </p>
    </div>
    <div v-else class="tw-flex tw-flex-col tw-gap-2 tw-p-2 tw-mt-2 tw-bg-gray-400/25 tw-rounded-[10px]">
        <p class="tw-text-[14px] tw-font-semibold tw-text-black">
            {{ 'You should log in to see your shipping address.' }}
        </p>
    </div>

</template>

<script setup>

    import { onMounted, ref } from 'vue';
    import apiClient from '../../../services/api.js';
    import { useAuthStore } from '../../../services/auth.js';

    const authStore = useAuthStore();
    const address = ref({});
    const loading = ref(false);
    const error = ref(null);

    const fetchUserAddressData = async () => {

        loading.value = true;
        error.value = null;

        const response = await apiClient.getUserData();

        if (response.success) {

            address.value = response.data.address;

        } else {

            error.value = response.error.message;

        }

        loading.value = false;

    };

    onMounted(fetchUserAddressData);

</script>