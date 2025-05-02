<template>
    <div class="row tw-mt-4 sm:tw-border-2 sm:tw-rounded-lg">
        
        <!-- SUCCESS -->
        <div v-if="!loading && !error" class="col-12 tw-bg-gray-200 tw-py-2 tw-space-y-2">
            <div class="tw-text-center tw-text-lg tw-font-bold tw-text-gray-700">
                {{ 'Reviews' }}
            </div>

            <div v-if="reviews.length > 0" class="list-group">
                <div
                    v-for="review in reviews"
                    :key="`review-${review.id}`"
                    class="list-group-item list-group-item-action"
                >
                    <ReviewComponent :review="review" :fromUser="fromUser" />
                </div>
            </div>

            <div v-else class="tw-bg-gray-300 tw-font-semibold tw-text-md tw-p-4 tw-rounded-lg" role="alert">
                {{ 'No reviews available for this item.' }}
            </div>
        </div>

        <!-- LOADING -->
        <div v-if="loading" class="tw-text-center tw-py-4 tw-text-gray-500">
            Loading reviews...
        </div>

        <!-- ERROR -->
        <div v-if="error" class="tw-text-red-600 tw-bg-red-100 tw-py-4 tw-rounded-md tw-text-center" role="alert">
            {{ error }}
        </div>
        
    </div>
</template>

<script setup>
    import { ref, onMounted, defineProps } from 'vue';
    import apiClient from '../../services/api.js';

    const props = defineProps({
        userId: {
            default: null,
            required: false,
        },
        itemId: {
            default: null,
            required: false,
        },
    });

    const reviews = ref([]);
    const loading = ref(true);
    const error = ref(null);
    const fromUser = ref(false);

    const fetchReviews = async () => {

        loading.value = true;
        let response = null;

        if (props.userId != null) {

            response = await apiClient.getReviewsByUserId(props.userId);
            fromUser.value = true;

        } else if (props.itemId != null) {

            response = await apiClient.getReviewsByItemId(props.itemId);
            fromUser.value = false;

        } else {

            error.value = 'No userId or itemId provided.';
            loading.value = false;
            return;

        }

        if (response.success) {

            error.value = null;
            reviews.value = response.data;

        } else {

            error.value = response.error?.message || 'An unknown error occurred.';

        }

        loading.value = false;

    };

    onMounted(fetchReviews);

</script>
