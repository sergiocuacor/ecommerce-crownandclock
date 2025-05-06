<template>
    <footer class="container-fluid tw-border-t-4">
        <div class="row px-5">
            
            <div class="col-12 mt-3">
                <CustomBreadCrumbComponent />
            </div>

            <div class="col-6 col-md-2 mb-3">
                <p class="fs-6 fw-semibold">
                    {{ `Information` }}
                </p>
                <ul class="nav flex-column pt-2">
                    <li class="nav-item mb-2">
                        <router-link :to="{ name: 'home' }" class="nav-link p-0 text-body-secondary">
                            {{ `Home` }}
                        </router-link>
                    </li>
                    <li class="nav-item mb-2">
                        <router-link :to="{ name: 'pricing' }" class="nav-link p-0 text-body-secondary">
                            {{ `Pricing` }}
                        </router-link>
                    </li>
                    <li class="nav-item mb-2">
                        <router-link :to="{ name: 'faq' }" class="nav-link p-0 text-body-secondary">
                            {{ `FAQ` }}
                        </router-link>
                    </li>
                    <li class="nav-item mb-2">
                        <router-link :to="{ name: 'about' }" class="nav-link p-0 text-body-secondary">
                            {{ `About` }}
                        </router-link>
                    </li>
                </ul>
            </div>

            <div class="col-6 col-md-2 mb-3">
                <p class="fs-6 fw-semibold">
                    {{ `Our selection` }}
                </p>
                <ul class="nav flex-column pt-2">
                    <li class="nav-item mb-2">
                        <router-link :to="{ name: 'products' }" class="nav-link p-0 text-body-secondary">
                            {{ `Watches` }}
                        </router-link>
                    </li>
                    <li class="nav-item mb-2">
                        <router-link :to="{ name: 'brands' }" class="nav-link p-0 text-body-secondary">
                            {{ `Brands` }}
                        </router-link>
                    </li>
                    <li class="nav-item mb-2">
                        <router-link :to="{ name: 'cart' }" class="nav-link p-0 text-body-secondary">
                            {{ `Cart` }}
                        </router-link>
                    </li>
                </ul>
            </div>

            <div class="col-6 col-md-2 mb-3">
                <p class="fs-6 fw-semibold">
                    {{ `Most purchased brands` }}
                </p>
                <ul class="nav flex-column pt-2">
                    <li class="nav-item mb-2">
                        <router-link :to="{ name: 'brand', params: { mask: 8 } }" class="nav-link p-0 text-body-secondary">
                            {{ `Rolex` }}
                        </router-link>
                    </li>
                    <li class="nav-item mb-2">
                        <router-link :to="{ name: 'brand', params: { mask: 3 } }" class="nav-link p-0 text-body-secondary">
                            {{ `Casio` }}
                        </router-link>
                    </li>
                    <li class="nav-item mb-2">
                        <router-link :to="{ name: 'brand', params: { mask: 2 } }" class="nav-link p-0 text-body-secondary">
                            {{ `Cartier` }}
                        </router-link>
                    </li>
                </ul>
            </div>

            <div class="col-md-5 offset-md-1 mb-3">
                <form>
                    <h5>
                        {{ `Subscribe to our newsletter` }}
                    </h5>
                    <p>
                        {{ `Monthly digest of what's new and exciting from us.` }}
                    </p>
                    <div class="d-flex flex-column flex-sm-row w-100 gap-2">
                        <label for="newsletter1" class="visually-hidden">
                            {{ `Email address` }}
                        </label>
                        <input id="newsletter1" type="text" class="form-control" placeholder="Email address">
                        <button class="btn btn-dark" type="button">
                            {{ `Subscribe` }}
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <div class="d-flex flex-column flex-sm-row justify-content-between py-3 px-4 border-top">            
            <p class="fs-6">
                {{ `© 2025 Crown & Clock, Inc. All rights reserved.` }}
            </p>
            <ul class="list-unstyled d-flex">
                <li class="ms-3">
                    <router-link :to="{ name: 'twitter' }" class="link-body-emphasis">
                        <i class="bi bi-twitter-x fs-6 fw-semibold"></i>        
                    </router-link>
                </li>
                <li class="ms-3">
                    <router-link :to="{ name: 'instagram' }" class="link-body-emphasis">
                        <i class="bi bi-instagram fs-6 fw-semibold"></i>        
                    </router-link>
                </li>
                <li class="ms-3">
                    <router-link :to="{ name: 'facebook' }" class="link-body-emphasis">
                        <i class="bi bi-facebook fs-6 fw-semibold"></i>      
                    </router-link>
                </li>
            </ul>
        </div>
    </footer>
</template>