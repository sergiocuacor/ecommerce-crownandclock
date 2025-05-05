<template>

    <figure
        v-if="itemRating > 0"
        class="tw-flex tw-align-items-center tw-text-white tw-justify-center tw-py-1.5 tw-px-2 tw-bg-black/80 tw-rounded-lg tw-cursor-help"
        :title="`${itemRating} / 5 in ${ratingCount} reviews`"
    >
        <div class="tw-font-bold tw-mr-1.5 tw-text-gray-400">
            {{ ratingCount }}
            <span class="tw-hidden lg:tw-inline tw-truncate">
                {{ ' reviews' }}
            </span>
        </div>

        <template v-for="index in 5" :key="index">
            <i
                v-if="index <= Math.floor(itemRating)"
                class="bi bi-star-fill tw-text-yellow-500"
            ></i>
            <i
                v-else-if="index === Math.floor(itemRating) + 1 && (itemRating % 1) >= 0.5"
                class="bi bi-star-half tw-text-yellow-500"
            ></i>
            <i
                v-else
                class="bi bi-star-fill tw-text-gray-500"
            ></i>
        </template>
    </figure>

    <figure
        v-else
        class="tw-flex tw-align-items-center tw-text-white tw-justify-center tw-py-1.5 tw-px-2 tw-bg-black/80 tw-rounded-lg tw-cursor-help"
        :title="`No reviews yet`"
    >
        <div class="tw-font-bold tw-text-gray-400 tw-mx-1.5 lg:tw-mx-0">
            {{ '0' }}
            <span class="tw-hidden lg:tw-inline tw-truncate">
                {{ ' reviews' }}
            </span>
        </div>
        
    </figure>

</template>  

<script setup>

    import { computed, defineProps } from 'vue';

    const props = defineProps({
        averageRating: {
            type: Number,
            default: null
        },
        ratingCount: {
            type: Number,
            default: null
        }
    });

    const itemRating = computed(() => {
        if (
            props.averageRating !== null &&
            props.ratingCount !== null &&
            props.ratingCount > 0
        ) {
            return props.averageRating;
        } else {
            return 0;
        }
    });

</script>
