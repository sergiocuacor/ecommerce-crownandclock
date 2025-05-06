<template>
    <nav class="navbar navbar-expand-lg fixed-top tw-bg-gray-200 lg:tw-bg-transparent lg:tw-bg-gradient-to-b lg:tw-from-gray-200">
        <div class="container-fluid">
            <router-link :to="{ name: 'home' }" class="navbar-brand">
                <i class="bi bi-bing"></i>
            </router-link>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse mb-2 mb-lg-0 fw-semibold" id="navbarContent">
                <ul class="navbar-nav flex-grow-1">
                    <li class="nav-item ms-lg-3">
                        <router-link :to="{ name: 'home' }" class="nav-link">
                            {{ 'Home' }}
                        </router-link>
                    </li>
                    <li class="nav-item ms-lg-3">
                        <router-link :to="{ name: 'products' }" class="nav-link">
                            {{ 'Watches' }}
                        </router-link>
                    </li>
                    <li class="nav-item ms-lg-3">
                        <router-link :to="{ name: 'brands' }" class="nav-link">
                            {{ 'Brands' }}
                        </router-link>
                    </li>
                    <li class="nav-item ms-lg-3">
                        <router-link :to="{ name: 'cart' }" class="nav-link">
                            {{ 'Cart' }}
                        </router-link>
                    </li>
                    
                </ul>                
                
                <ul class="navbar-nav flex-shrink-1">

                    <li class="nav-item ms-lg-2">
                        <button class="nav-link" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" @click="utilsStore.setOffCanvasContent('cart')" aria-controls="offcanvasNavbar" aria-label="Open Cart OffCanvas">
                            <IconWithNotificationComponent icon="bi-basket-fill" :notificationColor="(cartStore.items.length > 0) ? 'tw-bg-green-500' : ''"/>                        
                        </button>
                    </li>
                    
                    <li v-if="authStore.token" class="nav-item ms-lg-2">
                        <router-link :to="{ name: 'profile' }" class="nav-link">
                            <IconWithNotificationComponent icon="bi-person-circle" notificationColor="tw-bg-blue-500"/>
                        </router-link>
                    </li>
                    
                    <li v-if="!authStore.token" class="nav-item ms-lg-2">
                        <router-link :to="{ name: 'login' }" class="nav-link">
                            {{ 'Login' }}
                        </router-link>
                    </li>

                    <li v-if="!authStore.token" class="nav-item ms-lg-2">
                        <router-link :to="{ name: 'register' }" class="nav-link">
                            {{ 'Sign Up' }}
                        </router-link>
                    </li>

                    <li v-if="authStore.token" class="nav-item ms-lg-2">
                        <button class="nav-link" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" @click="utilsStore.setOffCanvasContent('controlPanel')" aria-controls="offcanvasNavbar" aria-label="Open Control Panel OffCanvas">
                            <IconWithNotificationComponent icon="bi-gear-fill"/>                        
                        </button>
                    </li>

                    <li v-if="authStore.token" class="nav-item ms-lg-2">
                        <button @click="logOut" type="button" class="nav-link tw-text-red-800 hover:tw-text-red-700">
                            <IconWithNotificationComponent icon="bi-arrow-up-right-square-fill" /> 
                        </button>
                    </li>

                </ul>

            </div>
        </div>
    </nav>
    <OffCanvasComponent />
</template>

<script setup>

    import { useUtilsStore } from '../services/utils.js';
    import { useAuthStore } from '../services/auth.js';    
    import { useCartStore } from '../store/cart.js';
    import { useRouter } from 'vue-router';

    const utilsStore = useUtilsStore();
    const authStore = useAuthStore();
    const cartStore = useCartStore();
    const router = useRouter();    

    function logOut() {
        authStore.logOutUser();
        cartStore.removeCoupon();
        router.push('/login');
    }
    
</script>
