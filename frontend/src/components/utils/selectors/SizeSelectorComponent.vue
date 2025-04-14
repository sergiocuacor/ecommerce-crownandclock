<template>

    <label for="sizeSelector" class="form-label">{{ 'Cantidad por página: ' }}</label>
    <select name="sizeSelector" id="sizeSelector" class="form-select" v-model="selectedSize" @change="handleChange">

        <option v-for="size in sizes" :key="size" :value="size" :disabled="size == selectedSize">{{ size }}</option>

    </select>

</template>

<script setup>

    import { computed, ref } from 'vue';
    import { useRoute, useRouter } from 'vue-router';

    const route = useRoute();
    const router = useRouter();

    const emit = defineEmits(['sizeSelected']);
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