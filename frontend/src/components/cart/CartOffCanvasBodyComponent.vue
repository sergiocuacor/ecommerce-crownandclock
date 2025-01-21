<template>

    <!-- FULL -->
    <ul v-if="cartStore.items.length > 0" class="list-group list-group-flush">
        <li v-for="item in cartStore.items" :key="item.id" class="list-group-item">            
            <div class="tw-flex tw-items-center tw-gap-4">
                <router-link :to="{ name: 'product', params: { mask: item.id } }" class="ratio ratio-1x1 tw-w-2/12">
                    <img class="tw-object-contain" :src="item.image" :alt="item.id">
                </router-link>
                <div class="tw-font-medium tw-w-10/12">
                    <div class="tw-w-64 tw-overflow-hidden tw-whitespace-nowrap tw-text-ellipsis tw-truncate dark:tw-text-white">
                        {{ item.title }}
                    </div>
                    <div class="tw-text-sm tw-text-gray-500 dark:tw-text-gray-400">
                        {{ item.price + '&#8364' }}
                    </div>
                    <div class="tw-text-sm tw-text-gray-500 dark:tw-text-gray-400">
                        {{ item.quantity + (item.quantity > 1 ? ' uds' : ' ud') }}
                    </div>
                    <div class="tw-text-sm tw-text-gray-500 dark:tw-text-gray-400">
                        {{ 'Total: ' + item.price * item.quantity + '&#8364' }}
                    </div>
                </div>
            </div>
        </li>        
    </ul>

    <div v-if="cartStore.items.length > 0" class="">            
        <div class="tw-text-2xl tw-text-center tw-p-5 space-y-2.5 tw-bg-gray-300 tw-border-2 tw-rounded-lg">
            {{ 'Total: ' + cartTotal + '&#8364' }}     
        </div>
    </div>

    <!-- EMPTY -->
    <div v-else class="tw-w-full tw-h-full tw-relative">
        <div class="tw-absolute tw-top-1/2 tw-left-1/2 tw-transform tw--translate-x-1/2 tw--translate-y-1/2 tw-bg-gray-200 tw-px-2 tw-rounded">
            {{ 'Vacío' }}
        </div>
    </div>

</template>

<script setup>

    import { computed } from 'vue';
    import { useCartStore } from '../../store/cart.js';

    const cartStore = useCartStore();

    const cartTotal = computed(() => {
        return cartStore.items.reduce((total, item) => total + item.price * item.quantity, 0).toFixed(2);
    });

</script>