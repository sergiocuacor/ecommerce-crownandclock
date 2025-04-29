<template>

    <div class="tw-text-sm tw-text-gray-500 tw-text-right">
        {{ date ? 'Posted: ' + date : 'unknown' }}
    </div>


    <article v-if="props.fromUser">

        <div v-if="!loading && !error" class="tw-grid tw-grid-cols-12 tw-gap-2">

            <div class="tw-col-span-12 lg:tw-col-span-2">
                <router-link :to="{ name: 'product', params: { mask: item.mask } }" class="ratio ratio-1x1">
                    <ImageExtensionCheckerComponent :src="apiBaseURL + `/images/` + item.mask + `/image.png`" :alt="`${item.mask}`"/>
                </router-link>
            </div>

            <div class="tw-col-span-12 lg:tw-col-span-10">
                <div class="tw-bg-gray-100 tw-p-2.5 tw-mt-2 tw-border tw-border-2 tw-rounded-lg">
                    <div class="tw-font-bold tw-text-lg tw-text-gray-800">
                        {{ item.name }} 
                    </div>                
                    <div class="tw-flex tw-items-center tw-space-x-1">
                        <div class="tw-text-xl tw-font-semibold tw-text-gray-700">
                            {{ props.review.rating + ' / 5' }}
                        </div>
                        <div v-for="n in 5" :key="n">
                            <span :class="n <= props.review.rating ? 'tw-text-yellow-400' : 'tw-text-gray-300'">
                                <i class="bi bi-star-fill"></i>
                            </span>
                        </div>
                    </div>
                </div>               
                <div class="tw-px-1.5 tw-mt-2 tw-mb-1">
                    <span class="tw-font-semibold tw-text-gray-800">
                        {{ 'Commented: ' }}
                    </span>
                    <p class="tw-break-words tw-font-sm tw-italic tw-tracking-wide tw-text-gray-500">
                        {{ props.review.comment }}
                    </p>
                </div>
            </div>

        </div>

    </article>

    <article v-else>

        <div class="tw-bg-gray-100 tw-p-2.5 tw-mt-2 tw-border tw-border-2 tw-rounded-lg">
            <div class="tw-font-bold tw-text-lg tw-text-gray-800">
                {{ props.review.username }}
            </div>

            <div class="tw-flex tw-items-center tw-space-x-1">
                <div class="tw-text-xl tw-font-semibold tw-text-gray-700">
                    {{ props.review.rating + ' / 5' }}
                </div>
                <div v-for="n in 5" :key="n">
                    <span :class="n <= props.review.rating ? 'tw-text-yellow-400' : 'tw-text-gray-300'">
                        <i class="bi bi-star-fill"></i>
                    </span>
                </div>
            </div>
        </div>
        
    
        <div class="tw-px-1.5 tw-mt-2 tw-mb-1">
            <span class="tw-font-semibold tw-text-gray-800">
                {{ 'Commented: ' }}
            </span>
            <p class="tw-break-words tw-font-sm tw-italic tw-tracking-wide tw-text-gray-500">
                {{ props.review.comment }}
            </p>
        </div>

    </article>

</template>

<script setup>

    import { defineProps, onMounted, ref } from 'vue';
    import apiClient from '../../services/api.js';

    const apiBaseURL = ref(apiClient.getApiBaseURL());

    const props = defineProps({
        review: {
            type: Object,
            required: true
        },
        fromUser: {
            type: Boolean,
            default: false
        }
    });

    const item = ref({});
    const loading = ref(true);
    const error = ref(null);
    const date = props.review.createdAt ? new Date(props.review.createdAt).toLocaleString() : null;

    const fetchItem = async () => {

        if (!props.fromUser) { return; }

        loading.value = true;

        const response = await apiClient.getItemById(props.review.productId);

        if (response.success) {

            item.value = response.data;

        } else {

            error.value = response.error.message;
        }

        loading.value = false;

    };

    onMounted(fetchItem);

</script>