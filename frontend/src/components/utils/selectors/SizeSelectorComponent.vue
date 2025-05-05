<template>

    <label for="sizeSelector" class="tw-block tw-text-md tw-font-medium tw-text-gray-700 tw-py-2">
        {{ 'Watches per page: ' }}
    </label>
    
    <select name="sizeSelector" id="sizeSelector" class="tw-block tw-w-full tw-border tw-border-gray-300 tw-rounded-md tw-py-2 tw-px-3 tw-shadow-md focus:tw-border-gray-400 focus:tw-ring-gray-400 tw-text-md" v-model="selectedSize" @change="handleChange">

        <option v-for="size in sizes" :key="size" :value="size" :disabled="size == selectedSize">
            {{ size }}
        </option>

    </select>

</template>

<script setup>

    import { computed, ref } from 'vue';
    import { useRoute, useRouter } from 'vue-router';

    const route = useRoute();
    const router = useRouter();

    const props = defineProps({
        pageSize: {
            type: Number,
            default: 12,
            required: true,
        },
    });

    const sizes = computed(() => {
        const baseSizes = [6, 12, 24, 48];
        if (!baseSizes.includes(props.pageSize)) {
            baseSizes.push(props.pageSize);
        }
        return baseSizes.sort((a, b) => a - b);
    });
    const selectedSize = ref(props.pageSize);

    const handleChange = () => {

        router.push({
            path: route.path,
            query: { ...route.query, page: 1, size: selectedSize.value},
        });

    }

</script>