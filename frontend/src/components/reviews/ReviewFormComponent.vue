
<template>

    <p v-if="error" class="tw-text-red-500 tw-text-sm tw-mt-1">
        {{ error }}
    </p>

    <form @submit.prevent="submitForm" class="tw-flex tw-flex-col tw-gap-4 tw-p-3 tw-bg-white tw-rounded-md tw-shadow-md tw-space-y-2">
        <div>
            <label for="comment" class="tw-block tw-mb-2 tw-font-semibold">
                {{ 'Comment' }}
            </label>
            <textarea
                id="comment"
                v-model="comment"
                class="tw-w-full tw-p-2 tw-border tw-rounded"
                rows="4"
                placeholder="Write your comment..."
                maxlength="500"
            ></textarea>
            <div class="tw-text-sm tw-text-gray-500">
                {{ comment.length + '/500' }}
            </div>
            <div v-if="commentError" class="tw-text-red-500 tw-text-sm tw-mt-1">
                {{ commentError }}
            </div>
        </div>

        <div>
            <label class="tw-block tw-mb-2 tw-font-semibold">
                {{ 'Rating' }}
            </label>
            <div class="tw-flex tw-space-x-1">
                <button
                    v-for="n in 5"
                    :key="n"
                    @click="setRating(n)"
                    @mouseover="hoverRating = n"
                    @mouseleave="hoverRating = 0"
                    type="button"
                    class="tw-text-2xl focus:tw-outline-none"
                >
                    <span :class="(hoverRating || rating) >= n ? 'tw-text-yellow-400' : 'tw-text-gray-300'">
                        <i class="bi bi-star-fill"></i>
                    </span>
                </button>
            </div>
            <div v-if="ratingError" class="tw-text-red-500 tw-text-sm tw-mt-1">
                {{ ratingError }}
            </div>
        </div>

        <button
            type="submit"
            class="tw-bg-black hover:tw-bg-gray-500 tw-text-white tw-text-sm tw-font-semibold tw-px-2 tw-py-1 tw-rounded-md"
        >
            <span v-if="sending" class="spinner-border spinner-border-sm me-2" aria-hidden="true"></span>
            {{ sending ? 'Saving...' : 'Save' }}
        </button>
    </form>

</template>

<script setup>

    import { getCurrentInstance, ref } from 'vue';
    import apiClient from '../../services/api.js';
    import { useRouter } from 'vue-router';

    const router = useRouter();
    const { proxy } = getCurrentInstance();

    const props = defineProps({
        productId: {
            required: true
        }
    });
    
    const sending = ref(false);
    const error = ref(null);
    const comment = ref('');
    const commentError = ref('');
    const rating = ref(1);
    const ratingError = ref('');
    const hoverRating = ref(0);

    const fetchUserId = async () => {

        const response = await apiClient.getUserData();

        let userId = null;

        if (response.success) {

            error.value = null;
            userId = response.data.id;

        } else {

            error.value = response.error.error;

        }

        return userId;

    };

    const sendReview = async (data) => {

        sending.value = true;

        const userId = await fetchUserId();

        if (userId) {

            const response = await apiClient.postNewReview(userId, props.productId, data);

            console.log(response)

            if (response.success) {

                error.value = null;
                router.push('/profile/reviews');
                proxy.$toast(`Added review`);

            } else {

                error.value = response.error;
                router.push('/profile/reviews');
                proxy.$toast(`Can't save review`);
            }

        }

        sending.value = false;

    };

    const setRating = (stars) => {
        rating.value = stars
    }

    const validateComment = () => {

        if (comment.value.length < 3) {
            commentError.value = 'The comment must have at least 3 characters.'
            return false
        }

        if (comment.value.length > 500) {
            commentError.value = 'The comment cannot exceed 500 characters.'
            return false
        }

        commentError.value = ''
        return true
    }

    const validateRating = () => {

        if (rating.value < 1) {
            ratingError.value = 'You must select at least 1 star.'
            return false
        }

        ratingError.value = ''
        return true
    }

    const submitForm = () => {

        if (rating.value < 1) {
            alert('You must select at least 1 star.')
            return
        }

        if (!validateComment() || !validateRating()) {
            return
        }

        const formData = {
            rating: rating.value,
            comment: comment.value
        }

        sendReview(formData)
        
    }
    
</script>
