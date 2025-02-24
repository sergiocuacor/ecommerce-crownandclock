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
                        <a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Overview</a>
                    </li>
                    <li>
                        <a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Updates</a>
                    </li>
                    <li>
                        <a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Reports</a>
                    </li>
                </ul>
            </div>
        </li>
        <li class="mb-1">
            <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#dashboard-collapse" aria-expanded="false">
                <i class="bi bi-square-half fs-6 fw-semibold me-1"></i>
                <span class="fs-6 fw-semibold">Dashboard</span>
            </button>
            <div class="collapse" id="dashboard-collapse">
                <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <li>
                        <a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Overview</a>
                    </li>
                    <li>
                        <a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Weekly</a>
                    </li>
                    <li>
                        <a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Monthly</a>
                    </li>
                    <li>
                        <a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Annually</a>
                    </li>
                </ul>
            </div>
        </li>
        <li class="mb-1">
            <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="false">
                <i class="bi bi-box-seam-fill fs-6 fw-semibold me-1"></i>
                <span class="fs-6 fw-semibold">Orders</span>
            </button>
            <div class="collapse" id="orders-collapse">
                <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <li>
                        <a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">New</a></li>
                    <li>
                        <a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Processed</a>
                    </li>
                    <li>
                        <a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Shipped</a>
                    </li>
                    <li>
                        <a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Returned</a>
                    </li>
                </ul>
            </div>
        </li>
        <li class="mb-1">
            <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#products-collapse" aria-expanded="false">
                <i class="bi bi-boxes fs-6 fw-semibold me-1"></i>
                <span class="fs-6 fw-semibold">Products</span>
            </button>
            <div class="collapse" id="products-collapse">
                <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <li>
                        <a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Wishlist</a>
                    </li>
                </ul>
            </div>
        </li>
        <li class="border-top my-3"></li>
        <li class="mb-1">
            <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#account-collapse" aria-expanded="false">
                <i class="bi bi-person-circle fs-6 fw-semibold me-1"></i>
                <span class="fs-6 fw-semibold">Account</span>
            </button>
            <div class="collapse" id="account-collapse">
                <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <li>
                        <a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">New...</a>
                    </li>
                    <li>
                        <a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Profile</a>
                    </li>
                    <li>
                        <a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Settings</a>
                    </li>
                    <li>
                        <a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Sign out</a>
                    </li>
                </ul>
            </div>
        </li>
    </ul>
</template>