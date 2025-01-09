<template>
    <figure class="d-flex justify-content-end bg-black bg-gradient bg-opacity-75 rounded-1 py-1 px-2 m-1">
        <div class="d-block text-white me-1">
            {{ itemRating.rate }}

            <div class="d-inline-block">
                <i v-for="index in Math.floor(itemRating.rate)" :key="'full-' + index" class="bi bi-star-fill text-warning"></i>

                <i v-if="(itemRating.rate - Math.floor(itemRating.rate)) >= 0.5" class="bi bi-star-half text-warning"></i>
                <i v-else class="bi bi-star-fill text-secondary"></i>

                <i v-for="index in 5 - Math.ceil(itemRating.rate)" :key="'empty-' + index" class="bi bi-star-fill text-secondary"></i>
            </div>
        </div>
    </figure>
</template>

<script>
    import { ref } from 'vue';

    export default {
        name: 'Rating',
        components: {

        },
        props: {
            itemRating: {
                type: Object,
                required: true,
            },
        },
        methods: {

        },
        setup(props) {
            const itemRating = ref([]);

            itemRating.value = props.itemRating;
            return {
                itemRating,
            };
        },
    };
</script>