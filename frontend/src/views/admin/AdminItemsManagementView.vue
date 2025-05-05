<template>

    <section v-if="userRole === 2" class="container-sm tw-pt-20 lg:tw-pt-16 pb-4">

        <AdminNavigationComponent />

        <div v-if="loading" class="tw-pt-4">
            {{ 'Loading items...' }}
        </div>
        <div v-if="error" class="tw-text-red-500 tw-pt-4">
            {{ error }}
        </div>
        <div v-if="!loading && !error" class="tw-pt-4">

            <p class="tw-text-2xl tw-font-semibold tw-text-center tw-mb-3 tw-text-gray-800">
                {{ 'All Items' }}
            </p>

            <ItemsComponent :adminMode="true" />

        </div>

    </section>

    <AdminRestrictedAccessComponent v-if="userRole === 1" />

</template>

<script setup>

    import { onMounted, ref } from 'vue';
    import { useRouter } from 'vue-router';
    import { useAuthStore } from '../../services/auth.js';
    import apiClient from '../../services/api.js';
    
    const router = useRouter();
    const authStore = useAuthStore();
    
    const userRole = ref(null);
    const loading = ref(false);
    const error = ref(null);
    
    const items = ref([]);

    const fetchUserRole = async () => {

        const response = await apiClient.getUserData();
    
        if (response.success) {

            const hasAdminRole = response.data.roles.some(role => role.name === "ROLE_ADMIN");
            userRole.value = hasAdminRole ? 2 : 1;

        } else {

            error.value = response.error.message;

        }
        
    };

    onMounted(fetchUserRole);

</script>