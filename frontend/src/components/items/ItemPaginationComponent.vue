<template>

    <article v-if="currentPage <= 0 || currentPage > totalPages" class="tw-flex tw-items-center tw-justify-center tw-border-t tw-border-gray-200 tw-bg-white tw-py-3 sm:tw-px-4">
        <p class="tw-text-sm tw-text-gray-700 tw-bg-gray-100 tw-p-2 tw-rounded-md">
            {{ 'No hay resultados para mostrar o página no válida.' }}
        </p>
    </article>

    <article v-if="currentPage > 0 && currentPage <= totalPages" class="tw-flex tw-items-center tw-justify-between tw-border-t tw-border-gray-200 tw-bg-white tw-py-3 sm:tw-px-4">

        <!-- MOBILE -->
        <div class="tw-flex tw-flex-1 tw-justify-between sm:tw-hidden">
            <button @click="firstPage" class="tw-relative tw-inline-flex tw-items-center tw-rounded-l-md tw-border-2 tw-border-gray-300 tw-bg-white tw-px-4 tw-py-2 tw-text-sm tw-font-medium tw-text-gray-700 hover:tw-bg-gray-50">
                <i class="bi bi-chevron-double-left"></i>
            </button>
            <button @click="prevPage" class="tw-relative tw-inline-flex tw-items-center tw-border-y-2 tw-border-gray-300 tw-bg-white tw-px-4 tw-py-2 tw-text-sm tw-font-medium tw-text-gray-700 hover:tw-bg-gray-50">
                <i class="bi bi-chevron-left"></i>
            </button>
            <div class="tw-relative tw-inline-flex tw-flex-1 tw-items-center tw-justify-center tw-border-2 tw-border-gray-300 tw-bg-white tw-px-4 tw-py-2 tw-text-sm tw-font-medium tw-text-gray-700 hover:tw-bg-gray-50">
                {{ currentPage + ' - ' + totalPages }}
            </div>
            <button @click="nextPage" class="tw-relative tw-inline-flex tw-items-center tw-border-y-2 tw-border-gray-300 tw-bg-white tw-px-4 tw-py-2 tw-text-sm tw-font-medium tw-text-gray-700 hover:tw-bg-gray-50">
                <i class="bi bi-chevron-right"></i>
            </button>
            <button @click="lastPage" class="tw-relative tw-inline-flex tw-items-center tw-rounded-r-md tw-border-2 tw-border-gray-300 tw-bg-white tw-px-4 tw-py-2 tw-text-sm tw-font-medium tw-text-gray-700 hover:tw-bg-gray-50">
                <i class="bi bi-chevron-double-right"></i>
            </button>         
        </div>

        <!-- DESKTOP -->
        <div class="tw-hidden sm:tw-flex sm:tw-flex-1 sm:tw-flex-col md:tw-flex-row sm:tw-items-center sm:tw-justify-between sm:tw-gap-2">
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

                    <!-- FIRST & PREVIOUS -->
                    <button @click="firstPage" :disabled="currentPage <= 1" class="tw-relative tw-inline-flex tw-items-center tw-rounded-l-md tw-px-2 tw-py-2 tw-text-gray-400 tw-ring-1 tw-ring-gray-300 tw-ring-inset hover:tw-bg-gray-50 focus:tw-z-20 focus:tw-outline-offset-0">
                        <span class="tw-sr-only">{{ 'Primera Página' }}</span>
                        <i class="bi bi-chevron-double-left"></i>
                    </button>
                    <button @click="prevPage" :disabled="currentPage <= 1" class="tw-relative tw-inline-flex tw-items-center tw-px-2 tw-py-2 tw-text-gray-400 tw-ring-1 tw-ring-gray-300 tw-ring-inset hover:tw-bg-gray-50 focus:tw-z-20 focus:tw-outline-offset-0">
                        <span class="tw-sr-only">{{ 'Anterior' }}</span>
                        <i class="bi bi-chevron-left"></i>
                    </button>

                    <button v-if="pageNumberButtons.buttons[0]?.condition" @click="goToAnyPage(pageNumberButtons.buttons[0]?.number)" :class="[pageNumberButtons.commonClasses, (currentPage == pageNumberButtons.buttons[0]?.number) ? pageNumberButtons.currentPageClass : pageNumberButtons.notCurrentPageClass]">
                        {{ pageNumberButtons.buttons[0]?.number }}
                    </button>
                    <button v-if="pageNumberButtons.buttons[1]?.condition" @click="goToAnyPage(pageNumberButtons.buttons[1]?.number)" :class="[pageNumberButtons.commonClasses, (currentPage == pageNumberButtons.buttons[1]?.number) ? pageNumberButtons.currentPageClass : pageNumberButtons.notCurrentPageClass]">
                        {{ pageNumberButtons.buttons[1]?.number }}
                    </button>
                    <button v-if="pageNumberButtons.buttons[2]?.condition" @click="goToAnyPage(pageNumberButtons.buttons[2]?.number)" :class="[pageNumberButtons.commonClasses, (currentPage == pageNumberButtons.buttons[2]?.number) ? pageNumberButtons.currentPageClass : pageNumberButtons.notCurrentPageClass]">
                        {{ pageNumberButtons.buttons[2]?.number }}
                    </button>

                    <span v-if="totalPages >= 7 && (currentPage < (totalPages - 3))" class="tw-relative tw-inline-flex tw-items-center tw-px-4 tw-py-2 tw-text-sm tw-font-semibold tw-text-gray-700 tw-ring-1 tw-ring-gray-300 tw-ring-inset focus:tw-outline-offset-0">
                        {{ '...' }}
                    </span>

                    <button v-if="pageNumberButtons.buttons[3]?.condition" @click="goToAnyPage(pageNumberButtons.buttons[3]?.number)" :class="[pageNumberButtons.commonClasses, (currentPage == pageNumberButtons.buttons[3]?.number) ? pageNumberButtons.currentPageClass : pageNumberButtons.notCurrentPageClass]">
                        {{ pageNumberButtons.buttons[3]?.number }}
                    </button>
                    <button v-if="pageNumberButtons.buttons[4]?.condition" @click="goToAnyPage(pageNumberButtons.buttons[4]?.number)" :class="[pageNumberButtons.commonClasses, (currentPage == pageNumberButtons.buttons[4]?.number) ? pageNumberButtons.currentPageClass : pageNumberButtons.notCurrentPageClass]">
                        {{ pageNumberButtons.buttons[4]?.number }}
                    </button>
                    <button v-if="pageNumberButtons.buttons[5]?.condition" @click="goToAnyPage(pageNumberButtons.buttons[5]?.number)" :class="[pageNumberButtons.commonClasses, (currentPage == pageNumberButtons.buttons[5]?.number) ? pageNumberButtons.currentPageClass : pageNumberButtons.notCurrentPageClass]">
                        {{ pageNumberButtons.buttons[5]?.number }}
                    </button>
                    
                    <!-- NEXT & LAST -->
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
        currentPage: {
            type: Number,
            default: 1,
            required: true,
        },
        pageSize: {
            type: Number,
            default: 12,
            required: true,
        },
        totalElements: {
            type: Number,
            default: 0,
            required: true,
        },    
    });
    
    const currentPage = computed(() => props.currentPage);
    const pageSize = computed(() => props.pageSize);
    const totalPages = computed(() => Math.ceil(props.totalElements / pageSize.value));
    const totalElements = computed(() => props.totalElements);

    const firstElement = computed(() => ((currentPage.value - 1) * pageSize.value) + 1);
    const lastElement = computed(() => ((currentPage.value - 1) * pageSize.value) + pageSize.value);

    const pageNumberButtons = computed(() => {
        return {
            commonClasses: "tw-relative tw-inline-flex tw-items-center tw-px-4 tw-py-2 tw-text-sm tw-font-semibold focus:tw-z-20",
            currentPageClass: "tw-text-white tw-z-10 tw-bg-indigo-600 focus-visible:tw-outline-2 focus-visible:tw-outline-offset-2 focus-visible:tw-outline-indigo-600",
            notCurrentPageClass: "tw-text-gray-900 tw-ring-1 tw-ring-gray-300 tw-ring-inset hover:tw-bg-gray-50 focus:tw-outline-offset-0",
            buttons: [
                { condition: totalPages.value >= 1, number: (currentPage.value <= 3 || (currentPage.value <= 6 && totalPages.value <= 6)) ? 1 : (currentPage.value >= totalPages.value - 3) ? totalPages.value - 5 : currentPage.value - 2 },
                { condition: totalPages.value >= 2, number: (currentPage.value <= 3 || (currentPage.value <= 6 && totalPages.value <= 6)) ? 2 : (currentPage.value >= totalPages.value - 3) ? totalPages.value - 4 : currentPage.value - 1 },
                { condition: totalPages.value >= 3, number: (currentPage.value <= 3 || (currentPage.value <= 6 && totalPages.value <= 6)) ? 3 : (currentPage.value >= totalPages.value - 3) ? totalPages.value - 3 : currentPage.value },
                { condition: totalPages.value >= 4, number: (totalPages.value <= 6) ? 4 : (totalPages.value - 2) },
                { condition: totalPages.value >= 5, number: (totalPages.value <= 6) ? 5 : (totalPages.value - 1) },
                { condition: totalPages.value >= 6, number: (totalPages.value <= 6) ? 6 : totalPages.value },
            ],
        };
    });

    const goToAnyPage = (pageNumber) => {
        router.push({
            path: route.path,
            query: { ...route.query, page: pageNumber },
        });
    };

    const firstPage = () => {
        goToAnyPage(1);
    };
    
    const prevPage = () => {
        if (currentPage.value > 1) {
            goToAnyPage(currentPage.value - 1);
        }
    };   

    const nextPage = () => {
        if (currentPage.value < totalPages.value) {
            goToAnyPage(currentPage.value + 1);
        }
    };

    const lastPage = () => {
        goToAnyPage(totalPages.value);
    };
    
</script>