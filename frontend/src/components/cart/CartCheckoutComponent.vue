<template>
    <div class="tw-grid tw-grid-cols-1 tw-gap-0">
      <!-- Cart Card -->
      <div class="tw-w-full tw-bg-gray-200 tw-rounded-t-[10px]">
        <label class="tw-w-full tw-h-10 tw-relative tw-flex tw-items-center tw-pl-5 tw-pt-0.5 tw-border-b tw-border-[rgba(16,86,82,0.75)] tw-font-bold tw-text-[11px] tw-text-black">
          {{ 'CHECKOUT' }}
        </label>
        <div class="tw-flex tw-flex-col tw-p-5 tw-gap-4">
          <div class="tw-grid tw-gap-2">
            
            <div>
              <span class="tw-block tw-text-[13px] tw-font-semibold tw-text-black">
                {{ 'SHIPPING' }}
              </span>
              <CartFullAddressComponent />
            </div>

            <hr class="tw-h-px tw-bg-gray-600/75 tw-border-0" />

            <div>
              <span class="tw-block tw-text-[13px] tw-font-semibold tw-text-black">
                {{ 'PAYMENT METHOD' }}
              </span>
              <CartPaymentMethodComponent @validatedPaymentMethod="validatePaymentMethod"/>
            </div>
            
            <hr class="tw-h-px tw-bg-gray-600/75 tw-border-0" />

            <div>
              <span class="tw-block tw-text-[13px] tw-font-semibold tw-text-black tw-mb-2">{{ 'HAVE A PROMO CODE?' }}</span>
              <form class="tw-grid tw-grid-cols-12 tw-gap-2">
                <div class="tw-col-span-12 md:tw-col-span-9">
                  <input v-if="cartStore.coupon == null" v-model="couponInput" type="text" placeholder="COUPON" class="tw-w-full tw-h-9 tw-px-3 tw-rounded tw-border tw-border-gray-400 tw-bg-gray-300 tw-outline-none tw-transition-all tw-duration-300 tw-ease-[cubic-bezier(0.15,0.83,0.66,1)] focus:tw-border-transparent focus:tw-ring-2 focus:tw-ring-gray-400"/>
                  <input v-else type="text" :placeholder="cartStore.coupon.name" disabled class="tw-w-full tw-h-9 tw-px-3 tw-rounded tw-border tw-border-gray-400 tw-bg-gray-300 tw-outline-none tw-transition-all tw-duration-300 tw-ease-[cubic-bezier(0.15,0.83,0.66,1)] focus:tw-border-transparent focus:tw-ring-2 focus:tw-ring-gray-400"/>
                </div>
                <div class="tw-col-span-12 md:tw-col-span-3">
                  <button :disabled="cartStore.coupon != null" @click="validateCoupon" type="button" class="tw-flex tw-justify-center tw-items-center tw-h-9 tw-w-full tw-bg-gray-500 disabled:tw-bg-gray-400 tw-font-semibold tw-text-[12px] tw-text-gray-300 tw-rounded tw-transition tw-delay-150 tw-duration-300 tw-ease-in-out hover:tw-scale-95">
                    {{ cartStore.coupon != null ? 'Applied' : 'Apply' }}
                  </button>
                </div> 
              </form>
            </div>

            <hr class="tw-h-px tw-bg-gray-600/75 tw-border-0" />

            <div>
              <span class="tw-block tw-text-[13px] tw-font-semibold tw-text-black tw-mb-2">
                {{ 'PAYMENT' }}
              </span>
              <div class="tw-grid tw-grid-cols-[10fr_1fr] tw-gap-y-1">
                <span class="tw-text-[12px] tw-font-semibold tw-text-black tw-text-left">
                  {{ 'Subtotal:' }}
                </span>
                <span class="tw-text-[13px] tw-font-semibold tw-text-black tw-text-right">
                  {{ cartSubtotal + '€' }}
                </span>
                <span class="tw-text-[12px] tw-font-semibold tw-text-black tw-text-left">
                  {{ 'Taxes (21%): ' }}
                </span>
                <span class="tw-text-[13px] tw-font-semibold tw-text-black tw-text-right">
                  {{ cartTaxes + '€' }}
                </span>
                <span v-if="cartStore.coupon != null" class="tw-text-[12px] tw-font-semibold tw-text-black tw-text-left">
                  {{ 'Coupon ' + cartStore.coupon.name + ' (' + cartStore.coupon.discountPercentage + '%)' }}
                </span>
                <span v-if="cartStore.coupon != null" class="tw-text-[13px] tw-font-semibold tw-text-black tw-text-right">
                  {{ '-' + cartDiscount + '€' }}
                </span>
              </div>
            </div>

          </div>
        </div>
      </div>
  
      <!-- Checkout Card -->
      <div class="tw-w-full">
        <div class="tw-flex tw-flex-col lg:tw-flex-row tw-items-center tw-justify-between tw-px-5 tw-py-2.5 tw-bg-[rgba(16,86,82,0.5)] tw-rounded-b-[10px]">
          <label class="tw-relative tw-text-[22px] tw-font-black">
            {{ 'Total: ' }}{{ cartStore.coupon != null ? cartTotalDiscounted : cartTotal }}{{ '€'  }}
          </label>
          <CardPaymentComponent @click="checkout" :class="`tw-mt-2 lg:tw-mt-0`"/>         
        </div>
      </div>
    </div>

</template>

<script setup>

    import { computed, onMounted, ref } from 'vue';
    import { useRouter } from 'vue-router';
    import { useCartStore } from '../../store/cart.js';
    import apiClient from '../../services/api.js';
    import { useAuthStore } from '../../services/auth.js';

    const router = useRouter();
    const cartStore = useCartStore();
    const authStore = useAuthStore();
    const couponInput = ref('')
    const isSubmitting = ref(false);
    const isValidPaymentMethod = ref(false);

    const validatePaymentMethod = (isValid) => {
      isValidPaymentMethod.value = isValid;
    };

    const rawTotal = computed(() =>
      cartStore.items.reduce((total, item) => total + item.price * item.quantity, 0)
    );

    const cartTotal = computed(() => rawTotal.value.toFixed(2));
    const cartSubtotal = computed(() => (rawTotal.value * 0.79).toFixed(2));
    const cartTaxes = computed(() => (rawTotal.value * 0.21).toFixed(2));
    const cartTotalDiscounted = computed(() => (rawTotal.value * (1 - (cartStore.coupon.discountPercentage / 100))).toFixed(2));
    const cartDiscount = computed(() => (rawTotal.value * (cartStore.coupon.discountPercentage / 100)).toFixed(2));

    const fetchUserId = async () => {

      const response = await apiClient.getUserData();

      let userId = null;

      if (response.success) {

        userId = response.data.id;

      }

      return userId;

    };

    const validateCoupon = async () => {

      await validateToken();

      await cartStore.couponValidation(couponInput.value);

    }

    const validateToken = async () => {

      if (!authStore.token) {
        router.push('/login');
      }

      await authStore.tokenValidation();

      if (!authStore.token) {

        router.push('/login');

      }

    };

    const sendOrder = async (order) => {

      const response = await apiClient.postOrder(order);

      if (response.success) {
        console.log('Order sent successfully!');
        router.push('/profile');
        cartStore.emptyCart();
      } else {
        console.error('Error sending order:', response.error);
      }

    };

    const checkout = async () => {

      await validateToken();

      if (!isValidPaymentMethod.value) {
        alert('Please select a payment method.');
        return;
      }

      if (isSubmitting.value) return;

      isSubmitting.value = true;

      if(cartStore.coupon != null) {
        await cartStore.couponValidation(cartStore.coupon.name);
      }
      
      const userId = await fetchUserId();

      if(userId != null) {

        const simplifiedArray = cartStore.items.map(item => ({
          productId: item.id,
          quantity: item.quantity,
        }));

        const order = {
          userId: userId,
          items: simplifiedArray,
        };

        if (cartStore.coupon != null) {
          await cartStore.couponValidation(cartStore.coupon.name);
          order.discount = cartStore.coupon.name;
        }

        await sendOrder(order);

      }

      isSubmitting.value = false;

    };

    onMounted(async () => {

      if(cartStore.coupon != null) {

        await cartStore.couponValidation(cartStore.coupon.name);

      }

    });

</script>