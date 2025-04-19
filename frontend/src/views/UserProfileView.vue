<template>

    <main class="container-sm tw-pt-16 lg:tw-pt-14 tw-font-serif" v-if="!loading">

        <div class="card shadow-lg">

            <div class="card-header tw-bg-black/90 tw-text-white">
                <h4 class="mb-0">{{ 'User Profile' }}</h4>
            </div>

            <div class="card-body">

                <h5 class="card-title fw-semibold">{{ 'Personal Information' }}</h5>

                <ul class="list-group list-group-flush mb-3">
                    <li class="list-group-item"><strong>{{ 'Full name: ' }}</strong>{{ user.name }}</li>
                    <li class="list-group-item"><strong>{{ 'First name: ' }}</strong>{{ user.firstName }}</li>
                    <li class="list-group-item"><strong>{{ 'Last name: ' }}</strong>{{ user.lastName }}</li>
                    <li class="list-group-item"><strong>{{ 'Email: ' }}</strong>{{ user.email }}</li>
                    <li class="list-group-item"><strong>{{ 'Phone number: ' }}</strong>{{ user.phoneNumber }}</li>
                </ul>

                <h5 class="card-title fw-semibold">{{ 'Full Address' }}</h5>
                
                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><strong>{{ 'Address: ' }}</strong>{{ user.address.streetAddress }}</li>
                    <li class="list-group-item"><strong>{{ 'City: ' }}</strong>{{ user.address.city }}</li>
                    <li class="list-group-item"><strong>{{ 'State: ' }}</strong>{{ user.address.state }}</li>
                    <li class="list-group-item"><strong>{{ 'Country: ' }}</strong>{{ user.address.country }}</li>
                    <li class="list-group-item"><strong>{{ 'Postal code: ' }}</strong>{{ user.address.postalCode }}</li>
                </ul>
            </div>

        </div>

        <section class="row gy-2 gx-4 py-4">

            <article class="col-12">

                <p class="tw-text-2xl tw-font-semibold tw-font-serif">{{ 'Lastest Orders' }}</p>

            </article>

            <article v-if="user.orderList.length > 0" class="accordion" id="ordersAccordion">

                <div v-for="order, index in user.orderList" :key="index" class="accordion-item">
                    <h2 class="accordion-header">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse" :data-bs-target="`#productPanel-` + index" aria-expanded="true" :aria-controls="`#productPanel-` + index">
                            {{ 'Order ' + new Date(order.orderDate).toLocaleString() }}
                            <span v-if="order.status == 'PENDING'" class="ms-2 badge tw-bg-yellow-700">{{ order.status }}</span>
                            <span v-if="order.status == 'CANCELLED'" class="ms-2 badge tw-bg-red-800">{{ order.status }}</span>
                            <span v-if="order.status == 'COMPLETED'" class="ms-2 badge tw-bg-green-800">{{ order.status }}</span>
                            <span v-if="order.status == 'SHIPPED'" class="ms-2 badge tw-bg-blue-800">{{ order.status }}</span>
                        </button>
                    </h2>
                    <div :id="`productPanel-` + index" class="accordion-collapse collapse" :class="index == 0 ? 'show' : ''">
                        <div class="accordion-body">

                            <OrderItemsComponent :orderItems="order.orderDetails"/>                            

                            <div class="tw-mt-2 tw-p-2 tw-bg-gray-200 tw-border-2 tw-rounded-md tw-divide-y-2 tw-divide-gray-300">
                                <div class="tw-text-center tw-text-lg tw-font-semibold tw-pb-2">
                                    {{ 'Invoice' }}
                                </div>
                                <div class="tw-py-2 tw-font-bold">
                                    {{ 'Subtotal: ' }}<strong>{{ (order.total * 0.79) + '€' }}</strong>
                                </div>
                                <div class="tw-py-2 tw-font-bold">
                                    {{ 'Taxes (21%): ' }}<strong>{{ (order.total * 0.21) + '€' }}</strong>
                                </div>
                                <div v-if="order.discountApplied != null && order.discountPercentage != null" class="tw-bg-gray-200 tw-font-bold tw-divide-y-2 tw-divide-gray-300">
                                    <div class="tw-py-2">
                                        {{ 'Coupon ' + order.discountApplied + '(' + order.discountPercentage + '%): '}}<strong>{{ '-' + (order.total * (order.discountPercentage / 100)) + '€' }}</strong>
                                    </div>
                                    <div class="tw-text-center tw-pt-2">
                                       {{ 'Total: ' }}<strong>{{ (order.total * (1 - (order.discountPercentage / 100))) + '€' }} </strong>
                                    </div>                                    
                                </div>
                                <div v-else class="tw-bg-gray-200 tw-font-bold tw-divide-y-2 tw-divide-gray-300">
                                    <div class="tw-text-center tw-pt-2">
                                        {{ 'Total: ' }}<strong>{{ order.total + '€' }}</strong>
                                    </div>                                    
                                </div>
                            </div>
                            
                        </div>
                    </div>
                </div>

            </article>

            <article v-else>
                {{ 'No orders found' }}
            </article>

        </section>        

    </main>
    
</template>

<script setup>

    import { onMounted, ref } from 'vue';
    import apiClient from '../services/api.js';
    
    const user = ref([]);
    const loading = ref(true);
    const error = ref(null);

    const fetchUserData = async () => {

        loading.value = true;

        const response = await apiClient.getUserData();

        if (response.success) {

            user.value = response.data;

        } else {

            error.value = response.error;

        }

        loading.value = false;

    };

    onMounted(fetchUserData)

</script>