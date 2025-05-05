<template>

    <section v-if="userRole === 2" class="container-sm tw-pt-20 lg:tw-pt-16 pb-4">

        <AdminNavigationComponent />

        <div v-if="loading" class="tw-pt-4">
            {{ 'Loading users...' }}
        </div>
        <div v-if="error" class="tw-text-red-500 tw-pt-4">
            {{ error }}
        </div>
        <div v-if="!loading && !error" class="tw-pt-4">

            <p class="tw-text-2xl tw-font-semibold tw-text-center tw-mb-3 tw-text-gray-800">
                {{ 'All Users' }}
            </p>

            <div
                v-for="user in users"
                :key="user.id"
                class="tw-bg-white tw-border tw-rounded-2xl tw-shadow-md tw-py-4 tw-px-5 tw-mb-3 hover:tw-shadow-lg transition-shadow"
            >
            
                <div class="tw-flex tw-flex-col lg:tw-flex-row lg:tw-justify-between lg:tw-items-center tw-mb-2">
                    <h3 class="tw-text-xl tw-font-bold tw-text-gray-900">
                        {{ user.firstName + ' ' + user.lastName }}
                    </h3>
                    <span class="tw-text-sm tw-text-gray-500">{{ user.email }}</span>
                </div>

                <div class="tw-flex tw-flex-col lg:tw-flex-row lg:tw-justify-between lg:tw-items-end tw-mb-2">

                    <div class="tw-text-sm tw-text-gray-700 tw-space-y-1">
                        <p>
                            <span class="tw-font-medium tw-me-1">{{ 'Phone:' }}</span>{{ user.phoneNumber }}</p>
                        <div>
                            <p class="tw-font-medium">{{ 'Address:' }}</p>
                            <p class="tw-ml-2">{{ user.address.streetAddress }}</p>
                            <p class="tw-ml-2">{{ user.address.city }}, {{ user.address.state }}</p>
                            <p class="tw-ml-2">{{ user.address.country + ' - ' + user.address.postalCode }}</p>
                        </div>
                    </div>

                    <div class="tw-pt-3 tw-flex tw-item-center">
                        <span class="tw-me-1">
                            {{ 'Active:' }}
                        </span>
                        <LockCheckboxInput :target="user.id" :isChecked="user.enabled" @inputChanged="updateUserState"/>
                    </div>                    

                </div>
                
            </div>

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
    
    const users = ref([]);

    const fetchUserRole = async () => {

        const response = await apiClient.getUserData();
    
        if (response.success) {

            const hasAdminRole = response.data.roles.some(role => role.name === "ROLE_ADMIN");
            userRole.value = hasAdminRole ? 2 : 1;

        } else {

            error.value = response.error.message;

        }
        
    };

    const fetchAllUsers = async () => {

        loading.value = true;

        const response = await apiClient.getAllUsers();
    
        if (response.success) {

            users.value = response.data;

        } else {

            error.value = response.error.message;

        }
    
        loading.value = false;

    };

    const updateUserState = async (id, data) => {

        const response = await apiClient.putUserData(id, data);

        if (response.success) {

            console.log(response.data);

        } else {

            console.log(response.error.message);

        }

    };

    onMounted(async () => {

        await fetchUserRole();

        if (userRole.value === 2) {

            await fetchAllUsers();

        }
    });

</script>