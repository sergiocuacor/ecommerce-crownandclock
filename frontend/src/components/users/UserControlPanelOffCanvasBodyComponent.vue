<template>
    <a href="/" class="d-flex align-items-center pb-3 mb-3 link-body-emphasis text-decoration-none border-bottom">
        <i class="bi bi-gear-fill fs-5 fw-semibold me-1"></i>
        <span class="fs-5 fw-semibold">Control Panel</span>
    </a>
    <ul class="list-unstyled ps-0">
        <li class="mb-1">
            <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="true">
                <i class="bi bi-house-door-fill fs-6 fw-semibold me-1"></i>
                <span class="fs-6 fw-semibold">Home</span>
            </button>
            <div class="collapse show" id="home-collapse">
                <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <li>
                        <router-link :to="{ name: 'home' }" class="link-body-emphasis d-inline-flex text-decoration-none rounded">
                            {{ 'Home' }}
                        </router-link>
                    </li>
                    <li>
                        <router-link :to="{ name: 'products' }" class="link-body-emphasis d-inline-flex text-decoration-none rounded">
                            {{ 'Watches' }}
                        </router-link>
                    </li>
                    <li>
                        <router-link :to="{ name: 'brands' }" class="link-body-emphasis d-inline-flex text-decoration-none rounded">
                            {{ 'Brands' }}
                        </router-link>
                    </li>
                </ul>
            </div>
        </li>
        <li class="mb-1">
            <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#dashboard-collapse" aria-expanded="false">
                <i class="bi bi-square-half fs-6 fw-semibold me-1"></i>
                <span class="fs-6 fw-semibold">
                    {{ 'Dashboard' }}
                </span>
            </button>
            <div class="collapse" id="dashboard-collapse">
                <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <li>
                        <router-link :to="{ name: 'reviews' }" class="link-body-emphasis d-inline-flex text-decoration-none rounded">
                            {{ 'Reviews' }}
                        </router-link>
                    </li>
                </ul>
            </div>
        </li>
        <li class="mb-1">
            <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="false">
                <i class="bi bi-box-seam-fill fs-6 fw-semibold me-1"></i>
                <span class="fs-6 fw-semibold">
                    {{ 'Orders' }}
                </span>
            </button>
            <div class="collapse" id="orders-collapse">
                <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <li>
                        <router-link :to="{ name: 'orders' }" class="link-body-emphasis d-inline-flex text-decoration-none rounded">
                            {{ 'All' }}
                        </router-link>
                    </li>
                    <li>
                        <router-link :to="{ name: 'orders', params: { filter: 'completed' } }" class="link-body-emphasis d-inline-flex text-decoration-none rounded">
                            {{ 'Completed' }}
                        </router-link>
                    </li>
                    <li>
                        <router-link :to="{ name: 'orders', params: { filter: 'shipped' } }" class="link-body-emphasis d-inline-flex text-decoration-none rounded">
                            {{ 'Shipped' }}
                        </router-link>
                    </li>
                    <li>
                        <router-link :to="{ name: 'orders', params: { filter: 'pending' } }" class="link-body-emphasis d-inline-flex text-decoration-none rounded">
                            {{ 'Pending' }}
                        </router-link>
                    </li>
                    <li>
                        <router-link :to="{ name: 'orders', params: { filter: 'cancelled' } }" class="link-body-emphasis d-inline-flex text-decoration-none rounded">
                            {{ 'Cancelled' }}
                        </router-link>
                    </li>
                </ul>
            </div>
        </li>
        <li class="border-top my-3"></li>
        <li class="mb-1">
            <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#account-collapse" aria-expanded="false">
                <i class="bi bi-person-circle fs-6 fw-semibold me-1"></i>
                <span class="fs-6 fw-semibold">
                    {{ 'Account' }}
                </span>
            </button>
            <div class="collapse" id="account-collapse">
                <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <li>
                        <router-link :to="{ name: 'profile' }" class="link-body-emphasis d-inline-flex text-decoration-none rounded">
                            {{ 'Profile' }}
                        </router-link>
                    </li>
                    <li>
                        <a href="" @click="logOut" class="link-body-emphasis d-inline-flex text-decoration-none rounded">
                            {{ 'Sign out' }}
                        </a>
                    </li>
                </ul>
            </div>
        </li>
    </ul>
</template>

<script setup>

    import { useAuthStore } from '../../services/auth.js';   
    import { useRouter } from 'vue-router';

    const authStore = useAuthStore();
    const router = useRouter();    

    function logOut() {
        authStore.logOutUser();
        router.push('/login');
    }

</script>