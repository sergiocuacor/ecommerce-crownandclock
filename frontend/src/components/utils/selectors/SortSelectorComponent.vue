<template>

    <label for="sortSelector" class="tw-block tw-text-md tw-font-medium tw-text-gray-700 tw-py-2">
        {{ 'Watches per page: ' }}
    </label>
    
    <select name="sortSelector" id="sortSelector" class="tw-block tw-w-full tw-border tw-border-gray-300 tw-rounded-md tw-py-2 tw-px-3 tw-shadow-md focus:tw-border-gray-400 focus:tw-ring-gray-400 tw-text-md" v-model="selectedSortOrder" @change="handleChange">

        <option v-for="(label, sortOrder) in sortOrders" :key="sortOrder" :value="sortOrder" :disabled="sortOrder === selectedSortOrder">
            {{ label }}
        </option>

    </select>

</template>

<script setup>

    import { computed, ref } from 'vue';
    import { useRoute, useRouter } from 'vue-router';

    const route = useRoute();
    const router = useRouter();

    const props = defineProps({
        sortBy: {
            type: String,
            default: 'name,asc',
            required: true,
        },
    });

    const sortOrders = computed(() => {
        const labels = {
            'name,asc': 'Name (A-Z)',
            'name,desc': 'Name (Z-A)',
            'price,asc': 'Price (Low to High)',
            'price,desc': 'Price (High to Low)',
            'brand,asc': 'Brand (A-Z)',
            'brand,desc': 'Brand (Z-A)',
            'stock,asc': 'Stock (Low to High)',
            'stock,desc': 'Stock (High to Low)',
        };

        const updateLabelsAndSortOrder = (key, label, defaultSortOrder) => {
            labels[key] = label;
            selectedSortOrder.value = defaultSortOrder;
        };

        if (!labels[props.sortBy]) {
            switch (props.sortBy) {
                case 'name':
                    updateLabelsAndSortOrder('name,asc', 'Name (A-Z)', 'name,asc');
                    break;
                case 'price':
                    updateLabelsAndSortOrder('price,asc', 'Price (Low to High)', 'price,asc');
                    break;
                case 'brand':
                    updateLabelsAndSortOrder('brand,asc', 'Brand (A-Z)', 'brand,asc');
                    break;
                case 'stock':
                    updateLabelsAndSortOrder('stock,asc', 'Stock (Low to High)', 'stock,asc');
                    break;
                case 'mask':
                    updateLabelsAndSortOrder('mask', 'Mask (A-Z)', 'mask');
                    break;
                case 'mask,asc':
                    updateLabelsAndSortOrder('mask,asc', 'Mask (A-Z)', 'mask,asc');
                    break;
                case 'mask,desc':
                    updateLabelsAndSortOrder('mask,desc', 'Mask (Z-A)', 'mask,asc');
                    break;
                case 'description':
                    updateLabelsAndSortOrder('description', 'Description (A-Z)', 'description');
                    break;
                case 'description,asc':
                    updateLabelsAndSortOrder('description,asc', 'Description (A-Z)', 'description,asc');
                    break;
                case 'description,desc':
                    updateLabelsAndSortOrder('description,desc', 'Description (Z-A)', 'description,desc');
                    break;
                case 'brand_id':
                    updateLabelsAndSortOrder('brand_id', 'Brand ID (A-Z)', 'brand_id');
                    break;
                case 'brand_id,asc':
                    updateLabelsAndSortOrder('brand_id,asc', 'Brand ID (A-Z)', 'brand_id,asc');
                    break;
                case 'brand_id,desc':
                    updateLabelsAndSortOrder('brand_id,desc', 'Brand ID (Z-A)', 'brand_id,desc');
                    break;
                default:
                    labels[props.sortBy] = 'Unknown';
            }
        }
        return labels;
    });
    const selectedSortOrder = ref(props.sortBy);

    const handleChange = () => {

        router.push({
            path: route.path,
            query: { ...route.query, page: 1, sort: selectedSortOrder.value},
        });

    }

</script>