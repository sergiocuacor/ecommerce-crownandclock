<template>

    <article class="tw-flex tw-items-center tw-justify-between tw-border-t tw-border-gray-200 tw-bg-white tw-py-3 sm:tw-px-4">

        <!-- MOBILE -->
        <div class="tw-flex tw-flex-1 tw-justify-between sm:tw-hidden">
            <button @click="firstPage" class="tw-relative tw-inline-flex tw-items-center tw-rounded-l-md tw-border-2 tw-border-gray-300 tw-bg-white tw-px-4 tw-py-2 tw-text-sm tw-font-medium tw-text-gray-700 hover:tw-bg-gray-50">
                <i class="bi bi-chevron-double-left"></i>
            </button>
            <button @click="prevPage" class="tw-relative tw-inline-flex tw-items-center tw-border-y-2 tw-border-gray-300 tw-bg-white tw-px-4 tw-py-2 tw-text-sm tw-font-medium tw-text-gray-700 hover:tw-bg-gray-50">
                <i class="bi bi-chevron-left"></i>
            </button>
            <div class="tw-relative tw-inline-flex tw-flex-1 tw-items-center tw-justify-center tw-border-2 tw-border-gray-300 tw-bg-white tw-px-4 tw-py-2 tw-text-sm tw-font-medium tw-text-gray-700 hover:tw-bg-gray-50">
                {{ 'Pag. ' + currentPage }}
            </div>
            <button @click="nextPage" class="tw-relative tw-inline-flex tw-items-center tw-border-y-2 tw-border-gray-300 tw-bg-white tw-px-4 tw-py-2 tw-text-sm tw-font-medium tw-text-gray-700 hover:tw-bg-gray-50">
                <i class="bi bi-chevron-right"></i>
            </button>
            <button @click="lastPage" class="tw-relative tw-inline-flex tw-items-center tw-rounded-r-md tw-border-2 tw-border-gray-300 tw-bg-white tw-px-4 tw-py-2 tw-text-sm tw-font-medium tw-text-gray-700 hover:tw-bg-gray-50">
                <i class="bi bi-chevron-double-right"></i>
            </button>         
        </div>

        <!-- DESKTOP -->
        <div class="tw-hidden sm:tw-flex sm:tw-flex-1 sm:tw-items-center sm:tw-justify-between">
            <div>
                <p class="tw-text-sm tw-text-gray-700">
                    {{ 'Mostrando de' }}
                    <span class="tw-font-medium">{{ firstElement }}</span>
                    {{ ' a ' }}
                    <span class="tw-font-medium">{{ lastElement }}</span>
                    {{ ' en ' }}
                    <span class="tw-font-medium">{{ totalElements }}</span>
                    {{ ' resultados.' }}                    
                </p>
            </div>
            <div>
                <nav class="tw-isolate tw-inline-flex -tw-space-x-px tw-rounded-md tw-shadow-xs" aria-label="Pagination">

                    <button @click="firstPage" :disabled="currentPage <= 0" class="tw-relative tw-inline-flex tw-items-center tw-rounded-l-md tw-px-2 tw-py-2 tw-text-gray-400 tw-ring-1 tw-ring-gray-300 tw-ring-inset hover:tw-bg-gray-50 focus:tw-z-20 focus:tw-outline-offset-0">
                        <span class="tw-sr-only">{{ 'Primera Página' }}</span>
                        <i class="bi bi-chevron-double-left"></i>
                    </button>
                    <button @click="prevPage" :disabled="currentPage <= 0" class="tw-relative tw-inline-flex tw-items-center tw-px-2 tw-py-2 tw-text-gray-400 tw-ring-1 tw-ring-gray-300 tw-ring-inset hover:tw-bg-gray-50 focus:tw-z-20 focus:tw-outline-offset-0">
                        <span class="tw-sr-only">{{ 'Anterior' }}</span>
                        <i class="bi bi-chevron-left"></i>
                    </button>

                    <!-- <a href="#" aria-current="page" class="tw-relative tw-z-10 tw-inline-flex tw-items-center tw-bg-indigo-600 tw-px-4 tw-py-2 tw-text-sm tw-font-semibold tw-text-white focus:tw-z-20 focus-visible:tw-outline-2 focus-visible:tw-outline-offset-2 focus-visible:tw-outline-indigo-600">1</a>
                    <a href="#" class="tw-relative tw-inline-flex tw-items-center tw-px-4 tw-py-2 tw-text-sm tw-font-semibold tw-text-gray-900 tw-ring-1 tw-ring-gray-300 tw-ring-inset hover:tw-bg-gray-50 focus:tw-z-20 focus:tw-outline-offset-0">2</a>
                    <a href="#" class="tw-relative tw-hidden tw-items-center tw-px-4 tw-py-2 tw-text-sm tw-font-semibold tw-text-gray-900 tw-ring-1 tw-ring-gray-300 tw-ring-inset hover:tw-bg-gray-50 focus:tw-z-20 focus:tw-outline-offset-0 md:tw-inline-flex">3</a>
                    <span class="tw-relative tw-inline-flex tw-items-center tw-px-4 tw-py-2 tw-text-sm tw-font-semibold tw-text-gray-700 tw-ring-1 tw-ring-gray-300 tw-ring-inset focus:tw-outline-offset-0">...</span>
                    <a href="#" class="tw-relative tw-hidden tw-items-center tw-px-4 tw-py-2 tw-text-sm tw-font-semibold tw-text-gray-900 tw-ring-1 tw-ring-gray-300 tw-ring-inset hover:tw-bg-gray-50 focus:tw-z-20 focus:tw-outline-offset-0 md:tw-inline-flex">8</a>
                    <a href="#" class="tw-relative tw-inline-flex tw-items-center tw-px-4 tw-py-2 tw-text-sm tw-font-semibold tw-text-gray-900 tw-ring-1 tw-ring-gray-300 tw-ring-inset hover:tw-bg-gray-50 focus:tw-z-20 focus:tw-outline-offset-0">9</a>
                    <a href="#" class="tw-relative tw-inline-flex tw-items-center tw-px-4 tw-py-2 tw-text-sm tw-font-semibold tw-text-gray-900 tw-ring-1 tw-ring-gray-300 tw-ring-inset hover:tw-bg-gray-50 focus:tw-z-20 focus:tw-outline-offset-0">10</a> -->
                    
                    <button @click="nextPage" :disabled="currentPage >= totalPages" class="tw-relative tw-inline-flex tw-items-center tw-px-2 tw-py-2 tw-text-gray-400 tw-ring-1 tw-ring-gray-300 tw-ring-inset hover:tw-bg-gray-50 focus:tw-z-20 focus:tw-outline-offset-0">
                        <span class="tw-sr-only">{{ 'Siguiente' }}</span>
                        <i class="bi bi-chevron-right"></i>
                    </button>
                    <button @click="lastPage" :disabled="currentPage >= totalPages" class="tw-relative tw-inline-flex tw-items-center tw-rounded-r-md tw-px-2 tw-py-2 tw-text-gray-400 tw-ring-1 tw-ring-gray-300 tw-ring-inset hover:tw-bg-gray-50 focus:tw-z-20 focus:tw-outline-offset-0">
                        <span class="tw-sr-only">{{ 'Última' }}</span>
                        <i class="bi bi-chevron-double-right"></i>
                    </button>
                    
                </nav>
            </div>
        </div>

    </article>

</template>
  
<script setup>

    import { computed } from 'vue';
    import { useRoute, useRouter } from 'vue-router';

    const route = useRoute();
    const router = useRouter();

    const props = defineProps({
        // Pagination-related properties
        currentPage: {
            type: Number,
            default: 0,
            required: false,
        },
        pageSize: {
            type: Number,
            default: 12,
            required: false,
        },
        totalElements: {
            type: Number,
            default: 0,
            required: false,
        },    
    });
    
    const currentPage = computed(() => props.currentPage);
    const pageSize = computed(() => props.pageSize);
    const totalPages = computed(() => Math.ceil(props.totalElements / pageSize.value) - 1);
    const totalElements = computed(() => props.totalElements);

    const firstElement = computed(() => currentPage.value * pageSize.value + 1);
    const lastElement = computed(() => (currentPage.value * pageSize.value) + pageSize.value);
    
    const firstPage = () => {
        router.push({
            path: route.path,
            query: { ...route.query, page: 0 },
        });
    };
    
    const prevPage = () => {
        if (currentPage.value > 0) {
            router.push({
                path: route.path,
                query: { ...route.query, page: currentPage.value - 1 },
            });
        }
    };

    const nextPage = () => {
        if (currentPage.value < totalPages.value) {
            router.push({
                path: route.path,
                query: { ...route.query, page: currentPage.value + 1 },
            });
        }
    };

    const lastPage = () => {
        router.push({
            path: route.path,
            query: { ...route.query, page: totalPages.value },
        });
    };
    
</script>