<template>

    <ElementComponent v-if="!props.adminMode" :title="props.item.name" :toRoute="{ name: 'product', params: { mask: props.item.mask } }" :imgSrc="apiBaseURL + `/images/` + props.item.mask + `/image.png`" :imgAlt="`${props.item.mask}`">
        <template #top-right>
            <ItemRatingComponent :averageRating="props.item.averageRating" :ratingCount="props.item.ratingCount" :class="`tw-m-2`"/>
        </template>
        <template #bottom-right>
            <CartActionsButtonComponent :item="props.item" :class="'m-1'"/>
        </template>
        <template #footer>
            <div class="col text-truncate">
                <span class="tw-font-semibold tw-text-[16px]">
                    {{ props.item.name }}
                </span>
            </div>
            <div class="col-12 col-md-auto">
                <span class="tw-font-semibold tw-text-[16px]">
                    {{ props.item.price }}
                </span>
                <span class="tw-font-bold tw-text-[14px]">
                    {{ '&#8364' }}
                </span>
            </div>
            <div class="col-12">
                <span class="tw-font-semibold tw-text-[14px]">
                    {{ 'Stock: ' }}
                </span>
                <span class="tw-font-bold tw-text-[14px]">
                    {{ props.item.stock }}
                </span>
            </div>
        </template>        
    </ElementComponent>

    <li v-else class="tw-flex tw-flex-col md:tw-flex-row md:tw-items-center tw-relative tw-py-2.5">
        <router-link :to="{ name: 'product', params: { mask: props.item.mask } }" class="ratio ratio-1x1 tw-w-full md:tw-w-2/12 tw-bg-gray-100 tw-rounded">
            <ImageExtensionCheckerComponent :class="`tw-object-contain tw-p-3`" :src="apiBaseURL + `/images/` + props.item.mask + `/image.png`" :alt="props.item.name"/>
        </router-link>
        <div class="tw-absolute tw-top-0 tw-left-0 tw-ms-2.5 md:tw-ms-1 tw-mt-5 md:tw-mt-3.5">
            <div class="tw-max-w-min tw-bg-gray-500/25 tw-flex tw-p-1 tw-space-x-1.5 tw-rounded-md">
                <span class="tw-bg-white tw-text-black tw-rounded-md tw-w-8 tw-h-8 tw-flex tw-items-center tw-justify-center tw-shadow-md disabled:tw-opacity-50 tw-text-xl">
                    {{ props.item.id }}
                </span>                
            </div>            
        </div>
        <div class="tw-absolute tw-top-0 tw-right-0 tw-me-2.5 md:tw-me-0 tw-mt-5 md:tw-mt-2.5">
            <div class="tw-max-w-min tw-bg-gray-500/25 tw-flex tw-p-1 tw-rounded-md">

                <button
                    @click="changeItemStockQuantity(props.item.id, true)"
                    class="tw-bg-white tw-text-black tw-rounded-s-md tw-w-8 tw-h-8 tw-flex tw-items-center tw-justify-center tw-shadow-md hover:tw-bg-gray-100 disabled:tw-opacity-50 tw-cursor-pointer tw-text-xl tw-transition tw-delay-150 tw-duration-300 tw-ease-in-out"
                >
                    <i class="bi bi-plus"></i>
                </button>

                <div class="tw-bg-yellow-50 tw-text-black  tw-w-8 tw-h-8 tw-flex tw-items-center tw-justify-center tw-shadow-md tw-text-xl tw-transition tw-delay-150 tw-duration-300 tw-ease-in-out">
                    {{ itemStock }}
                </div>

                <button
                    :disabled="(itemStock > 0) ? false : true"
                    @click="changeItemStockQuantity(props.item.id, false)"
                    class="tw-bg-black tw-text-white tw-rounded-e-md tw-w-8 tw-h-8 tw-flex tw-items-center tw-justify-center tw-shadow-md hover:tw-bg-gray-800 disabled:tw-opacity-50 tw-cursor-pointer tw-text-xl tw-transition tw-delay-150 tw-duration-300 tw-ease-in-out"
                >
                    <i class="bi bi-dash"></i>
                </button>

            </div>              
        </div>
        <div class="tw-font-medium tw-w-full md:tw-w-10/12 md:tw-ps-2 tw-pt-2 md:tw-pt-0">
            <div class="tw-overflow-hidden tw-truncate">
                {{ props.item.name }}
            </div>
            <div class="tw-overflow-hidden tw-text-[13px] tw-font-semibold tw-text-gray-600 tw-truncate">
                {{ props.item.mask }}
            </div>
            <div class="tw-text-[14px] tw-text-gray-500">
                <span class="tw-font-bold">{{ 'Price: ' }}</span>
                {{ props.item.price + '&#8364' }}
            </div>
            <div class="tw-text-[12px] tw-text-gray-500"> 
                <span class="tw-font-bold">{{ 'Brand: ' }}</span>
                {{ props.item.nameBrand }}
            </div>
        </div>
        <div class="tw-absolute tw-bottom-0 tw-right-0 tw-my-2">
            <button class="tw-bg-black tw-text-white tw-rounded-md tw-h-8 tw-flex tw-items-center tw-justify-center tw-px-1.5 tw-shadow-md hover:tw-bg-gray-800 disabled:tw-opacity-50 tw-cursor-pointer tw-text-xl tw-transition tw-delay-150 tw-duration-300 tw-ease-in-out hover:tw--translate-y-1 hover:tw-scale-110" @click="removeItem(props.item.id)">
                {{ 'Remove' }}
            </button>
        </div>         
    </li>

</template>

<script setup>

    import { defineEmits, getCurrentInstance, ref, watch } from 'vue';
    import apiClient from '../../services/api.js';

    const { proxy } = getCurrentInstance();

    const apiBaseURL = ref(apiClient.getApiBaseURL());

    const emit = defineEmits(['deletedItem']);
    const props = defineProps({
        item: {
            type: Object,
            default: {},
            required: true
        },
        adminMode: {
            type: Boolean,
            default: false,
        },
    });

    const loading = ref(false);
    const error = ref(null);   

    const itemStock = ref(props.item.stock);

    const removeItem = async (id) => {

        loading.value = true;

        const response = await apiClient.deleteItemById(id);        
    
        if (response.success) {

            error.value = null;

            setTimeout(() => {
                
                proxy.$toast(`Removed watch ${props.item.name} with ID ${props.item.id} from brand ${props.item.nameBrand}`);
                emit('deletedItem', props.item);

            }, 500);

        } else {

            proxy.$toast(`Error removing watch ${props.item.name} with ID ${props.item.id}`, { bgColor: 'tw-bg-red-200/75' });
            error.value = response.error.message;

        }

        loading.value = false;

    };

    const changeItemStockQuantity = async (id, action) => {

        loading.value = true;
        
        let newStock = itemStock.value + (action ? 1 : -1);

        if (newStock < 0) {
            loading.value = false;
            return;
        }

        const response = await apiClient.putItemData(id, { ...props.item, stock: newStock });

        if (response.success) {

            proxy.$toast(`Changed ${props.item.name} stock to ${newStock}`);
            itemStock.value = newStock;
            error.value = null;

        } else {

            proxy.$toast(`Error changing watch ${props.item.name} with ID ${props.item.id}`, { bgColor: 'tw-bg-red-200/75' });
            error.value = response.error.message;
        }

        loading.value = false;

    };

    watch(() => props.item.stock, (newVal) => {
        itemStock.value = newVal;
    });

</script>