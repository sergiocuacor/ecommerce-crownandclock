<template>

  <DynamicHeightDivComponent v-if="filter != 'success' && filter != 'error'">
    <template #header>
        <div class="tw-font-mono tw-text-md lg:tw-text-xl tw-bg-gray-200 tw-px-2 tw-rounded">
            {{ 'What are you doing here?' }}
        </div>
    </template>
    <template #footer >
        <div class="tw-font-mono tw-text-xs lg:tw-text-base">
            {{ 'Leave now' }}
        </div>
    </template>
  </DynamicHeightDivComponent>

  <main v-if="filter == 'success'" class="container-sm tw-pt-16 lg:tw-pt-14 pb-4">
    <div class="tw-text-2xl tw-text-center tw-p-5 tw-bg-gray-300 tw-border-2 tw-rounded-t-lg">
      {{ 'Success' }}
    </div>
    <div class="tw-text-center tw-p-5 tw-bg-gray-300 tw-border-2 tw-rounded-b-lg">
      {{ 'Your order was successful!' }}
    </div>
    
    <section v-if="!loading && !error" class="row gy-2 gx-4 py-4">

      <article class="col-12">

        <article v-if="latestOrder" class="accordion" id="ordersAccordion">

          <div :key="latestOrder.id" class="accordion-item">
              <h2 class="accordion-header">
                  <button class="accordion-button" type="button" data-bs-toggle="collapse" :data-bs-target="`#productPanel-` + latestOrder.id" aria-expanded="true" :aria-controls="`productPanel-` + latestOrder.id">
                      {{ 'Order ' + new Date(latestOrder.orderDate).toLocaleString() }}
                  </button>
              </h2>
              <div :id="`productPanel-` + latestOrder.id" class="accordion-collapse collapse show">
                  <div class="accordion-body">

                      <OrderItemsComponent :orderItems="latestOrder.orderDetails"/>

                      <OrderInformationComponent :order="latestOrder"/>
                      
                  </div>
              </div>
          </div>

        </article>   

      </article>

    </section>

  </main>

  <DynamicHeightDivComponent v-if="filter == 'error'">
    <template #header>
        <div class="tw-font-mono tw-text-md lg:tw-text-xl tw-bg-gray-200 tw-px-2 tw-rounded">
            {{ `There is an error processing your order, please try again later.` }}
        </div>
    </template>
    <template #footer >
        <div class="tw-font-mono tw-font-semibold tw-text-[18px] tw-p-2">
          <router-link
            :to="{ name: 'cart'}"
            class="tw-text-blue-500 hover:tw-text-blue-700 tw-font-semibold tw-text-[18px] tw-underline"
          >
            {{ 'Go back to cart' }}
          </router-link>
        </div>
    </template>
  </DynamicHeightDivComponent>

</template>

<script setup>

  import { computed, onMounted, ref } from 'vue';
  import { useRoute } from 'vue-router';
  import apiClient from '../services/api.js';

  const route = useRoute();
  const filter = computed(() => route.params.filter || '');
  
  const user = ref({});
  const loading = ref(true);
  const error = ref(null);

  const latestOrder = computed(() => {

    if (!Array.isArray(user.value.orderList) || user.value.orderList.length === 0) return null;

    return user.value.orderList.reduce((latest, current) => {
      return new Date(current.orderDate) > new Date(latest.orderDate) ? current : latest;
    });

  });

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