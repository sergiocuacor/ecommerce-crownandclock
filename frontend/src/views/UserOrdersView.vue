<template>

    <main class="container-sm tw-pt-16 lg:tw-pt-14 pb-4" v-if="!loading && !error">

        <section class="row gy-2 gx-4 py-4">

            <article class="col-12">

                <div class="tw-flex tw-items-center">

                    <span class="tw-text-2xl tw-font-semibold tw-font-serif">{{ 'Orders' }}</span>
                    
                    <span v-if="statusBadgeClass[filter]" :class="['ms-2 badge', statusBadgeClass[filter]]">
                        {{ filter.toUpperCase() }}
                    </span>

                </div>

            </article>

            <article v-if="filteredOrderList.length > 0" class="accordion" id="ordersAccordion">

                <div v-for="order, index in filteredOrderList" :key="order.id" class="accordion-item">
                    <h2 class="accordion-header">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse" :data-bs-target="`#productPanel-` + order.id" aria-expanded="true" :aria-controls="`productPanel-` + order.id">
                            {{ 'Order ' + new Date(order.orderDate).toLocaleString() }}
                            <span v-if="statusBadgeClass[order.status.toUpperCase()]" :class="['ms-2 badge', statusBadgeClass[order.status.toUpperCase()]]">
                                {{ order.status.toUpperCase() }}
                            </span>
                        </button>
                    </h2>
                    <div :id="`productPanel-` + order.id" class="accordion-collapse collapse" :class="index == 0 ? 'show' : ''">
                        <div class="accordion-body">

                            <OrderItemsComponent :orderItems="order.orderDetails"/>

                            <OrderInformationComponent :order="order"/>
                            
                        </div>
                    </div>
                </div>

            </article>

        <article v-else>
            {{ 'No orders found.' }}
        </article>

        </section>
        
    </main>

</template>

<script setup>

    import { computed, onMounted, ref } from 'vue';
    import { useRoute } from 'vue-router';
    import apiClient from '../services/api.js';

    const route = useRoute();
    const filter = computed(() => route.params.filter || 'all');
    console.log('Filter:', filter.value);
    const statusBadgeClass = {
        PENDING: 'tw-bg-yellow-700',
        CANCELLED: 'tw-bg-red-800',
        COMPLETED: 'tw-bg-green-800',
        SHIPPED: 'tw-bg-blue-800'
    };

    const filteredOrderList = computed(() => {

        if (!user.value.orderList) return [];

        if (filter.value === 'all') {
            return user.value.orderList;
        }

        return user.value.orderList.filter(order => 
            order.status.toUpperCase() === filter.value.toUpperCase()
        );

    });
    
    const user = ref({});
    const loading = ref(true);
    const error = ref(null);

    const fetchUserData = async () => {

        loading.value = true;

        const response = await apiClient.getUserData();

        if (response.success) {

            error.value = null;
            user.value = response.data;

        } else {

            error.value = response.error;

        }

        loading.value = false;

    };

    onMounted(fetchUserData);

</script>