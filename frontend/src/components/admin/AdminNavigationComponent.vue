<template>

    <div class="tw-grid tw-grid-cols-1 lg:tw-grid-cols-2 tw-gap-2 tw-bg-gray-300/75 tw-p-2 tw-rounded"> 

        <router-link
            :to="{ name: 'admin-panel' }"
            class="tw-col-span-1 lg:tw-col-span-2 tw-flex tw-items-center tw-justify-center tw-text-[18px] tw-font-semibold tw-text-gray-900 tw-bg-gray-100 hover:tw-bg-gray-50 tw-border tw-border-gray-400 hover:tw-border-gray-600 tw-py-2 tw-px-4 tw-rounded-md tw-transition-colors tw-duration-200"
        >
            {{ 'Panel' }}<i class="bi bi-shield-lock-fill tw-ms-2"></i>
        </router-link>

        <router-link
            :to="{ name: 'admin-users' }"
            class="tw-flex tw-items-center tw-justify-center tw-text-[18px] tw-font-semibold tw-text-gray-900 tw-bg-gray-100 hover:tw-bg-gray-50 tw-border tw-border-gray-400 hover:tw-border-gray-600 tw-py-2 tw-px-4 tw-rounded-md tw-transition-colors tw-duration-200"
        >
            {{ 'User management' }}<i class="bi bi-people-fill tw-ms-2"></i>
        </router-link>

        <router-link
            :to="{ name: 'admin-products' }"
            class="tw-flex tw-items-center tw-justify-center tw-text-[18px] tw-font-semibold tw-text-gray-900 tw-bg-gray-100 hover:tw-bg-gray-50 tw-border tw-border-gray-400 hover:tw-border-gray-600 tw-py-2 tw-px-4 tw-rounded-md tw-transition-colors tw-duration-200"
        >
            {{ 'Products management' }}<i class="bi bi-watch tw-ms-2"></i>
        </router-link>

        <router-link
            :to="{ name: 'admin-orders' }"
            class="tw-flex tw-items-center tw-justify-center tw-text-[18px] tw-font-semibold tw-text-gray-900 tw-bg-gray-100 hover:tw-bg-gray-50 tw-border tw-border-gray-400 hover:tw-border-gray-600 tw-py-2 tw-px-4 tw-rounded-md tw-transition-colors tw-duration-200"
        >
            {{ 'Orders management' }}<i class="bi bi-box-seam-fill tw-ms-2"></i>
        </router-link>

        <router-link
            :to="{ name: 'admin-sales-reports' }"
            class="tw-flex tw-items-center tw-justify-center tw-text-[18px] tw-font-semibold tw-text-gray-900 tw-bg-gray-100 hover:tw-bg-gray-50 tw-border tw-border-gray-400 hover:tw-border-gray-600 tw-py-2 tw-px-4 tw-rounded-md tw-transition-colors tw-duration-200"
        >
            {{ 'Sales reports' }}<i class="bi bi-graph-up tw-ms-2"></i>
        </router-link>
    
    </div>

</template>