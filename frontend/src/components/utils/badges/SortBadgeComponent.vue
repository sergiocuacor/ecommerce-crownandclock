<template>

    <span class="tw-bg-black tw-text-white tw-py-0.5 tw-px-3 tw-rounded-full tw-font-semibold tw-text-sm">
        {{ 'Sorted by: ' + sortLabel }}
    </span>

</template>

<script setup>

    import { computed, defineProps } from 'vue';

    const props = defineProps({
        sortBy: {
            type: String,
            required: true,
        },
    });

    const sortLabel = computed(() => {
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

        const updateLabels = (key, label) => {
            labels[key] = label;
        };

        if (!labels[props.sortBy]) {
            switch (props.sortBy) {
                case 'name':
                    updateLabels('name,asc', 'Name (A-Z)');
                    break;
                case 'price':
                    updateLabels('price,asc', 'Price (Low to High)');
                    break;
                case 'brand':
                    updateLabels('brand,asc', 'Brand (A-Z)');
                    break;
                case 'stock':
                    updateLabels('stock,asc', 'Stock (Low to High)');
                    break;
                case 'mask':
                    updateLabels('mask', 'Mask (A-Z)');
                    break;
                case 'mask,asc':
                    updateLabels('mask,asc', 'Mask (A-Z)');
                    break;
                case 'mask,desc':
                    updateLabels('mask,desc', 'Mask (Z-A)');
                    break;
                case 'description':
                    updateLabels('description', 'Description (A-Z)');
                    break;
                case 'description,asc':
                    updateLabels('description,asc', 'Description (A-Z)');
                    break;
                case 'description,desc':
                    updateLabels('description,desc', 'Description (Z-A)');
                    break;
                case 'brand_id':
                    updateLabels('brand_id', 'Brand ID (A-Z)');
                    break;
                case 'brand_id,asc':
                    updateLabels('brand_id,asc', 'Brand ID (A-Z)');
                    break;
                case 'brand_id,desc':
                    updateLabels('brand_id,desc', 'Brand ID (Z-A)');
                    break;
                default:
                    updateLabels(props.sortBy, 'Unknown');
            }
        }
        return labels;
    });
    
</script>