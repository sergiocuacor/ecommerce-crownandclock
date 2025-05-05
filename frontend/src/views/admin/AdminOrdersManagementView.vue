<template>

    <section v-if="userRole === 2" class="container-sm tw-pt-20 lg:tw-pt-16 pb-4">

        <AdminNavigationComponent />

        <div v-if="loading" class="tw-pt-4">
            {{ 'Loading orders...' }}
        </div>
        <div v-if="error" class="tw-text-red-500 tw-pt-4">
            {{ error }}
        </div>
        <div v-if="!loading && !error" class="tw-pt-4">
            
            <section class="row gy-2 gx-4 py-4">

                <article class="col-12">

                    <p class="tw-text-2xl tw-font-semibold tw-font-serif">{{ 'Lastest Orders' }}</p>

                </article>

                <article v-if="orders.length > 0" class="accordion" id="ordersAccordion">

                    <div v-for="order, index in orders" :key="order.id" class="accordion-item">
                        <h2 class="accordion-header">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse" :data-bs-target="`#productPanel-` + order.id" aria-expanded="true" :aria-controls="`productPanel-` + order.id">
                                {{ 'Order ' + new Date(order.orderDate).toLocaleString() }}
                                <span v-if="order.status == 'PENDING'" class="ms-2 badge tw-bg-yellow-700">{{ order.status }}</span>
                                <span v-if="order.status == 'CANCELLED'" class="ms-2 badge tw-bg-red-800">{{ order.status }}</span>
                                <span v-if="order.status == 'COMPLETED'" class="ms-2 badge tw-bg-green-800">{{ order.status }}</span>
                                <span v-if="order.status == 'SHIPPED'" class="ms-2 badge tw-bg-blue-800">{{ order.status }}</span>
                            </button>
                        </h2>
                        <div :id="`productPanel-` + order.id" class="accordion-collapse collapse" :class="index == 0 ? 'show' : ''">
                            <div class="accordion-body">

                                <AdminOrderStatusSelectorComponent :order="order" @orderStatusChanged="updateOrderStatus"/>

                                <OrderItemsComponent :adminMode="true" :orderItems="order.orderDetails"/>

                                <OrderInformationComponent :order="order"/>
                                
                            </div>
                        </div>
                    </div>

                </article>

                <article v-else>
                    {{ 'No orders found' }}
                </article>

                </section>
                
            </div>

    </section>

    <AdminRestrictedAccessComponent v-if="userRole === 1" />

</template>

<script setup>

    import { onMounted, ref } from 'vue';
    import { useRouter } from 'vue-router';
    import { useAuthStore } from '../../services/auth.js';
    import apiClient from '../../services/api.js';
    
    const router = useRouter();
    const authStore = useAuthStore();
    
    const userRole = ref(null);
    const loading = ref(false);
    const error = ref(null);
    
    const orders = ref([]);

    const fetchUserRole = async () => {

        const response = await apiClient.getUserData();
    
        if (response.success) {

            const hasAdminRole = response.data.roles.some(role => role.name === "ROLE_ADMIN");
            userRole.value = hasAdminRole ? 2 : 1;

        } else {

            error.value = response.error.message;

        }
        
    };

    const fetchAllOrders = async () => {

        loading.value = true;

        const response = await apiClient.getAllOrders();
    
        if (response.success) {

            orders.value = response.data;

        } else {

            error.value = response.error.message;

        }
    
        loading.value = false;

    };

    const updateOrderStatus = async (id, status) => {

        const orderToUpdate = orders.value.find(order => order.id === id);

        orderToUpdate.status = status;

    };

    onMounted(async () => {

        await fetchUserRole();

        if (userRole.value === 2) {

            await fetchAllOrders();

        }
    });

</script>