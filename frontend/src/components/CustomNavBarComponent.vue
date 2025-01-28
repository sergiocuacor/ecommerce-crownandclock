<template>
    <nav class="navbar navbar-expand-lg fixed-top tw-bg-gray-200 lg:tw-bg-transparent lg:tw-bg-gradient-to-b lg:tw-from-gray-200">
        <div class="container-fluid">
            <router-link to="/" class="navbar-brand">
                <i class="bi bi-bing"></i>
            </router-link>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <router-link :to="{ name: 'home' }" class="nav-link active" aria-current="page">
                            {{ 'Home' }}
                        </router-link>
                    </li>
                    <li class="nav-item dropdown">
                        <router-link :to="{ name: 'home' }" class="nav-link dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                            {{ 'Other' }}
                        </router-link>
                        <ul class="dropdown-menu">
                            <li>
                                <router-link :to="{ name: 'products' }" class="dropdown-item">
                                    {{ 'Products' }}
                                </router-link>
                            </li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li> 
                                <router-link :to="{ name: 'brands' }" class="dropdown-item">
                                    {{ 'Brands' }}
                                </router-link>
                            </li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li>
                                <router-link :to="{ name: 'categories' }" class="dropdown-item">
                                    {{ 'Categories' }}
                                </router-link>
                            </li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li>
                                <router-link :to="{ name: 'cart' }" class="dropdown-item">
                                    {{ 'Cart' }}
                                </router-link>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <router-link to="/" class="nav-link disabled" aria-disabled="true">
                            {{ 'Disabled' }}
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <button class="nav-link" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" @click="utilsStore.setOffCanvasContent('cart')" aria-controls="offcanvasNavbar" aria-label="Open Cart OffCanvas">
                            <IconWithNotificationComponent icon="bi-basket-fill" notificationColor="tw-bg-green-500"/>                        
                        </button>
                    </li>
                    <li class="nav-item">
                        <button class="nav-link" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" @click="utilsStore.setOffCanvasContent('controlPanel')" aria-controls="offcanvasNavbar" aria-label="Open Control Panel OffCanvas">
                            <IconWithNotificationComponent icon="bi-person-circle" notificationColor="tw-bg-blue-500"/>                        
                        </button>
                    </li>
                    <li class="nav-item">
                        <router-link :to="{ name: 'login' }" class="nav-link">
                            {{ 'Login' }}
                        </router-link>
                    </li>
                </ul>
                <form class="d-flex" role="search">
                    <div class="input-group">
                        <input type="search" class="form-control" placeholder="Search" aria-label="Search">
                        <span class="input-group-text tw-bg-green-700 tw-text-white">
                            <button type="submit">
                                <i class="bi bi-search"></i>
                            </button>
                        </span>
                    </div>
                </form>
            </div>
        </div>
    </nav>
    <OffCanvasComponent />
</template>

<script setup>

    import { useUtilsStore } from '../services/utils.js';

    const utilsStore = useUtilsStore();
    
</script>
