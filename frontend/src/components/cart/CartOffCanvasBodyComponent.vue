<template>
    <ul class="list-group list-group-flush">
        <li v-for="item in cartStore.items" :key="item.id" class="list-group-item">            
            <div class="tw-flex tw-items-center tw-gap-4">
                <router-link :to="{ name: 'product', params: { mask: item.id } }" class="ratio ratio-1x1 tw-w-10 tw-h-10">
                    <img class="tw-object-contain" :src="item.image" :alt="item.id">
                </router-link>
                <div class="tw-font-medium">
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
</template>

<script setup>

    import { useCartStore } from '../../store/cart.js';

    const cartStore = useCartStore();

</script>