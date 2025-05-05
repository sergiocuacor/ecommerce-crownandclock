<template>

    <div class="tw-flex tw-flex-col md:tw-flex-row tw-space-x-2 tw-bg-gray-200 tw-border-[3px] tw-border-gray-300 tw-rounded-xl tw-select-none tw-mb-4">
        <label class="tw-radio tw-flex tw-flex-grow tw-items-center tw-justify-center tw-rounded-lg tw-p-1 tw-cursor-pointer">
            <input v-model="currentOrderStatus" @change="updateOrderStatus" type="radio" value="PENDING" class="tw-peer tw-hidden" />
            <span
                class="tw-tracking-widest peer-checked:tw-bg-gradient-to-r peer-checked:tw-from-[gray] peer-checked:tw-to-[black] peer-checked:tw-text-white tw-text-gray-700 tw-p-2 tw-rounded-lg tw-transition tw-duration-150 tw-ease-in-out"
            >
                {{ 'PENDING' }}
            </span>
        </label>
        
        <label class="tw-radio tw-flex tw-flex-grow tw-items-center tw-justify-center tw-rounded-lg tw-p-1 tw-cursor-pointer">
            <input v-model="currentOrderStatus" @change="updateOrderStatus" type="radio" value="SHIPPED" class="tw-peer tw-hidden" />
            <span
                class="tw-tracking-widest peer-checked:tw-bg-gradient-to-r peer-checked:tw-from-[gray] peer-checked:tw-to-[black] peer-checked:tw-text-white tw-text-gray-700 tw-p-2 tw-rounded-lg tw-transition tw-duration-150 tw-ease-in-out"
            >
                {{ 'SHIPPED' }}
            </span>
        </label>
        
        <label class="tw-radio tw-flex tw-flex-grow tw-items-center tw-justify-center tw-rounded-lg tw-p-1 tw-cursor-pointer">
            <input v-model="currentOrderStatus" @change="updateOrderStatus" type="radio" value="COMPLETED" class="tw-peer tw-hidden" />
            <span
                class="tw-tracking-widest peer-checked:tw-bg-gradient-to-r peer-checked:tw-from-[gray] peer-checked:tw-to-[black] peer-checked:tw-text-white tw-text-gray-700 tw-p-2 tw-rounded-lg tw-transition tw-duration-150 tw-ease-in-out"
            >
                {{ 'COMPLETED' }}
            </span>
        </label>

        <label class="tw-radio tw-flex tw-flex-grow tw-items-center tw-justify-center tw-rounded-lg tw-p-1 tw-cursor-pointer">
            <input v-model="currentOrderStatus" @change="updateOrderStatus" type="radio" value="CANCELLED" class="tw-peer tw-hidden"/>
            <span
                class="tw-tracking-widest peer-checked:tw-bg-gradient-to-r peer-checked:tw-from-[gray] peer-checked:tw-to-[black] peer-checked:tw-text-white tw-text-gray-700 tw-p-2 tw-rounded-lg tw-transition tw-duration-150 tw-ease-in-out"
            >
                {{ 'CANCELLED' }}
            </span>
        </label>

    </div>

    <div v-if="error">
        {{ 'fffffffffffffffffffffffffffffff' }}
    </div>

</template>

<script setup>

    import { ref, watch } from 'vue';
    import apiClient from '../../services/api.js'

    const emit = defineEmits(['orderStatusChanged']);
    const props = defineProps({
        order: {
            type: {},
            required: true
        }
    });

    const loading = ref(false);
    const error = ref(null)
    
    const currentOrderStatus = ref(props.order.status);

    const updateOrderStatus = async (event) => {

        loading.value = true;

        const response = await apiClient.putOrderData(props.order.id, { ...props.order, status: currentOrderStatus.value });

        if (response.success) {

            emit('orderStatusChanged', props.order.id, currentOrderStatus.value)

        } else {

            error.value = response.error.message;

        }

        loading.value = false;

    };

    watch(() => props.order.status, (newVal) => {
        currentOrderStatus.value = newVal;
    });

</script>