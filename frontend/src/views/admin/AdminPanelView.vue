<template>

    <DynamicHeightDivComponent v-if="userRole === 2">
        <template #header>
            <section class="container-sm">
                <AdminNavigationComponent /> 
            </section>            
        </template>
    </DynamicHeightDivComponent>

    <AdminRestrictedAccessComponent v-if="userRole === 1" />

    <DynamicHeightDivComponent v-if="userRole != 2 && userRole != 1">
        <template #header>
            <section class="tw-font-mono tw-text-3xl tw-mx-3" v-if="error">
                {{ error }}              
            </section>
        </template>
    </DynamicHeightDivComponent>

</template>

<script setup>

    import { onMounted, ref } from 'vue';
    import { useRouter } from 'vue-router';
    import { useAuthStore } from '../../services/auth.js';  
    import apiClient from '../../services/api.js' 
    
    const router = useRouter();
    const authStore = useAuthStore();

    const userRole = ref(null);
    const loading = ref(false);
    const error = ref(null);

    const fetchUserRole = async () => {

        loading.value = true

        const response = await apiClient.getUserData();

        if (response.success) {

            error.value= null
            const hasAdminRole = response.data.roles.some(role => role.name === "ROLE_ADMIN");
            userRole.value = hasAdminRole ? 2 : 1;

        } else {

            error.value = response.error.message;

        }

        loading.value = false

    };

    onMounted(fetchUserRole);

</script>