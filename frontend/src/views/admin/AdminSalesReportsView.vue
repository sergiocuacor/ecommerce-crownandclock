<template>
    <section v-if="userRole === 2" class="container-sm tw-pt-20 lg:tw-pt-16 pb-4">
        <AdminNavigationComponent /> 

        <div v-if="loading" class="tw-pt-4">
            {{ 'Loading...' }}
        </div>
        <div v-if="error" class="tw-text-red-500 tw-pt-4">
            {{ error }}
        </div>
        <div v-if="!loading && !error" class="tw-pt-16">

            <div class="tw-grid tw-grid-cols-1 lg:tw-grid-cols-2 tw-gap-x-20 lg:tw-gap-x-10 tw-gap-y-16">

                <FolderOpeningComponent :text="`Download Daily Sales Report XLS`" @click="fetchSalesReports(1)" :class="`tw-cursor-pointer`"/>

                <FolderOpeningComponent :text="`Download Daily Sales Report PDF`" @click="fetchSalesReports(2)" :class="`tw-cursor-pointer`"/>

                <FolderOpeningComponent :text="`Download Monthly Sales Report XLS`" @click="fetchSalesReports(3)" :class="`tw-cursor-pointer`"/>

                <FolderOpeningComponent :text="`Download Monthly Sales Report PDF`" @click="fetchSalesReports(4)" :class="`tw-cursor-pointer`"/>

            </div>

        </div>
    </section>

    <AdminRestrictedAccessComponent v-if="userRole === 1" />

</template>

<script setup>
    import { onMounted, ref } from 'vue';
    import axios from 'axios';
    import { useRouter } from 'vue-router';
    import { useAuthStore } from '../../services/auth.js';  
    import apiClient from '../../services/api.js' 

    const router = useRouter();
    const authStore = useAuthStore();

    const userRole = ref(null);
    const loading = ref(false);
    const error = ref(null);

    const fetchUserRole = async () => {

        loading.value = true;

        const response = await apiClient.getUserData();

        if (response.success) {

            const hasAdminRole = response.data.roles.some(role => role.name === "ROLE_ADMIN");
            userRole.value = hasAdminRole ? 2 : 1;

        } else {

            error.value = response.error.message;

        }

        loading.value = false;

    };

    const fetchSalesReports = async (action) => {

        loading.value = true;
        
        try {

            let endpoint;
            let contentType;
            let fileExtension;
            
            switch(action) {
                case 1: // Daily XLS
                    endpoint = '/reports/daily-sales/excel';
                    contentType = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet';
                    fileExtension = 'xlsx';
                    break;
                case 2: // Daily PDF
                    endpoint = '/reports/daily-sales/pdf';
                    contentType = 'application/pdf';
                    fileExtension = 'pdf';
                    break;
                case 3: // Monthly XLS
                    endpoint = '/reports/monthly-sales/excel';
                    contentType = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet';
                    fileExtension = 'xlsx';
                    break;
                case 4: // Monthly PDF
                    endpoint = '/reports/monthly-sales/pdf';
                    contentType = 'application/pdf';
                    fileExtension = 'pdf';
                    break;
            }
            
            const response = await axios.get(
                apiClient.getApiBaseURL() + endpoint,
                {
                    responseType: 'blob',
                    headers: {
                        'Accept': contentType,
                        'Authorization': `Bearer ${localStorage.getItem('token')}`
                    }
                }
            );
            
            const timestamp = new Date().getTime();
            const fileName = `sales-report-${timestamp}.${fileExtension}`;
            
            const url = window.URL.createObjectURL(new Blob([response.data]));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', fileName);
            document.body.appendChild(link);
            link.click();
            
            window.URL.revokeObjectURL(url);
            document.body.removeChild(link);
            
        } catch (err) {

            error.value = `Error generating sales report. Try again later ${err}`;

        } finally {

            loading.value = false;

        }

    }

    onMounted(fetchUserRole);

</script>
