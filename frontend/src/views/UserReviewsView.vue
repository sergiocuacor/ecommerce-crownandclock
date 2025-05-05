<template>

    <main class="container-sm tw-pt-16 lg:tw-pt-14 pb-4" v-if="!loading && !error">

        <ReviewsComponent :userId="user.id" />

    </main>

</template>

<script setup>

    import { onMounted, ref } from 'vue';
    import apiClient from '../services/api.js';
    
    const user = ref({});
    const loading = ref(true);
    const error = ref(null);

    const fetchUserData = async () => {

        loading.value = true;

        const response = await apiClient.getUserData();

        if (response.success) {

            error.value = null;
            user.value = response.data;

        } else {

            error.value = response.error;

        }

        loading.value = false;

    };

    onMounted(fetchUserData);

</script>