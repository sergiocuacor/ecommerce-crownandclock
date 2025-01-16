<template>

    <div id="bottom-banner" tabindex="-1"
        class="tw-fixed tw-bottom-0 tw-start-0 tw-z-50 tw-flex tw-justify-between tw-w-full tw-p-4 tw-border-t tw-border-gray-200 tw-bg-gray-50 dark:tw-bg-gray-700 dark:tw-border-gray-600">
        <div class="tw-flex tw-items-center tw-mx-auto">
            <p class="tw-flex tw-items-center tw-text-sm tw-font-normal tw-text-gray-500 dark:tw-text-gray-400">
                <span class="tw-inline-flex tw-p-1 tw-me-3 tw-bg-gray-200 tw-rounded-full dark:tw-bg-gray-600 tw-w-6 tw-h-6 tw-items-center tw-justify-center">
                    <i class="bi bi-percent tw-text-gray-500 dark:tw-text-gray-400"></i>
                    <span class="tw-sr-only">{{ 'Descuento' }}</span>
                </span>
                <span>
                    {{ 'Consigue un cupón del 5%' }}
                    <router-link :to="{ name: 'home' }" class="tw-flex tw-items-center tw-ms-0 tw-text-sm tw-font-medium tw-text-blue-600 md:tw-ms-1 md:tw-inline-flex dark:tw-text-blue-500 hover:tw-underline">
                        {{ 'Únete' }}
                        <i class="bi bi-arrow-right tw-ms-2"></i>
                    </router-link>
                </span>
            </p>
        </div>
        <div class="tw-flex tw-items-center">
            <button data-dismiss-target="#bottom-banner" type="button" class="tw-flex-shrink-0 tw-inline-flex tw-justify-center tw-w-7 tw-h-7 tw-items-center tw-text-gray-400 hover:tw-bg-gray-200 hover:tw-text-gray-900 tw-rounded-lg tw-text-sm tw-p-1.5 dark:hover:tw-bg-gray-600 dark:hover:tw-text-white">
                <i class="bi bi-x-lg"></i>
                <span class="tw-sr-only">{{ 'Cerrar' }}</span>
            </button>
        </div>
    </div>

</template>