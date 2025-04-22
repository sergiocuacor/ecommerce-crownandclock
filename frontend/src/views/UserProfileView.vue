<template>

    <main class="container-sm tw-pt-16 lg:tw-pt-14 tw-font-serif" v-if="!loading">

        <div class="card">

            <div class="card-header tw-bg-black/90 tw-text-white">
                <h4 class="mb-0 tw-uppercase">{{ 'User Profile' }}</h4>
            </div>

            <div class="card-body">

                <h5 class="card-title fw-semibold tw-uppercase">{{ 'Personal Information' }}</h5>

                <ul class="list-group list-group-flush mb-3">
                    <li class="list-group-item">
                        <strong>{{ 'Full name: ' }}</strong>{{ user.name }}                     
                    </li>
                    <li class="list-group-item tw-flex tw-items-center tw-justify-between tw-gap-2">
                        <UserInformationInputGeneratorComponent :text="`First name: `" :inputType="`text`" :value="user.firstName" :validationId="`firstName`" :userId="user.id"/>
                    </li>    
                    <li class="list-group-item tw-flex tw-items-center tw-justify-between tw-gap-2">
                        <UserInformationInputGeneratorComponent :text="`Last name: `" :inputType="`text`" :value="user.lastName" :validationId="`lastName`" :userId="user.id"/>
                    </li>
                    <li class="list-group-item">
                        <UserInformationPasswordInputComponent :userId="user.id"/>
                    </li>
                    <li class="list-group-item tw-flex tw-items-center tw-justify-between tw-gap-2">
                        <UserInformationInputGeneratorComponent :text="`Email: `" :inputType="`email`" :value="user.email" :validationId="`email`" :userId="user.id"/>
                    </li>
                    <li class="list-group-item tw-flex tw-items-center tw-justify-between tw-gap-2">
                        <UserInformationInputGeneratorComponent :text="`Phone number: `" :inputType="`text`" :value="user.phoneNumber" :validationId="`phoneNumber`" :userId="user.id"/>
                    </li>
                </ul>

                <h5 class="card-title fw-semibold tw-uppercase">{{ 'Full Address' }}</h5>
                
                <ul class="list-group list-group-flush">                    
                    <li class="list-group-item tw-flex tw-items-center tw-justify-between tw-gap-2">
                        <UserInformationInputGeneratorComponent :text="`Address: `" :inputType="`text`" :value="user.address.streetAddress" :validationId="`streetAddress`" :userId="user.id"/>
                    </li>
                    <li class="list-group-item tw-flex tw-items-center tw-justify-between tw-gap-2">
                        <UserInformationInputGeneratorComponent :text="`City: `" :inputType="`text`" :value="user.address.city" :validationId="`city`" :userId="user.id"/>
                    </li>
                    <li class="list-group-item tw-flex tw-items-center tw-justify-between tw-gap-2">
                        <UserInformationInputGeneratorComponent :text="`State: `" :inputType="`text`" :value="user.address.state" :validationId="`state`" :userId="user.id"/>
                    </li>
                    <li class="list-group-item tw-flex tw-items-center tw-justify-between tw-gap-2">
                        <UserInformationInputGeneratorComponent :text="`Country: `" :inputType="`text`" :value="user.address.country" :validationId="`country`" :userId="user.id"/>
                    </li>
                    <li class="list-group-item tw-flex tw-items-center tw-justify-between tw-gap-2">
                        <UserInformationInputGeneratorComponent :text="`Postal code: `" :inputType="`text`" :value="user.address.postalCode" :validationId="`postalCode`" :userId="user.id"/>
                    </li>
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

                            <OrderInformationComponent :order="order"/>
                            
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