<template>
    <div class="tw-grid tw-grid-cols-1 tw-gap-0">
      <!-- Cart Card -->
      <div class="tw-w-full tw-bg-[#FFFAEB] tw-shadow-[0px_187px_75px_rgba(0,0,0,0.01),0px_105px_63px_rgba(0,0,0,0.05),0px_47px_47px_rgba(0,0,0,0.09),0px_12px_26px_rgba(0,0,0,0.1),0px_0px_0px_rgba(0,0,0,0.1)] tw-rounded-t-[19px]">
        <label class="tw-w-full tw-h-10 tw-relative tw-flex tw-items-center tw-pl-5 tw-border-b tw-border-[rgba(16,86,82,0.75)] tw-font-bold tw-text-[11px] tw-text-black">
          {{ 'CHECKOUT' }}
        </label>
        <div class="tw-flex tw-flex-col tw-p-5 tw-gap-4">
          <div class="tw-grid tw-gap-2">
            <div>
              <span class="tw-block tw-text-[13px] tw-font-semibold tw-text-black tw-mb-2">{{ 'SHIPPING' }}</span>
              <p class="tw-text-[11px] tw-font-semibold tw-text-black">{{ '221B Baker Street, W1U 8ED' }}</p>
              <p class="tw-text-[11px] tw-font-semibold tw-text-black">{{ 'London, United Kingdom' }}</p>
            </div>
            <hr class="tw-h-px tw-bg-[rgba(16,86,82,0.75)] tw-border-0" />
            <div>
              <span class="tw-block tw-text-[13px] tw-font-semibold tw-text-black tw-mb-2">{{ 'PAYMENT METHOD' }}</span>
              <p class="tw-text-[11px] tw-font-semibold tw-text-black">{{ 'Visa' }}</p>
              <p class="tw-text-[11px] tw-font-semibold tw-text-black">{{ '**** **** **** 4243' }}</p>
            </div>
            <hr class="tw-h-px tw-bg-[rgba(16,86,82,0.75)] tw-border-0" />
            <div>
              <span class="tw-block tw-text-[13px] tw-font-semibold tw-text-black tw-mb-2">{{ 'HAVE A PROMO CODE?' }}</span>
              <form class="tw-grid tw-grid-cols-[1fr_80px] tw-gap-2">
                <input type="text" placeholder="¿Tienes un cupón?" class="tw-h-9 tw-px-3 tw-rounded tw-border tw-border-[rgb(16,86,82)] tw-bg-[#FBF3E4] tw-outline-none tw-transition-all tw-duration-300 tw-ease-[cubic-bezier(0.15,0.83,0.66,1)] focus:tw-border-transparent focus:tw-ring-2 focus:tw-ring-[#C9C1B2]"/>
                <button type="button" class="tw-flex tw-justify-center tw-items-center tw-h-9 tw-w-full tw-bg-[rgba(16,86,82,0.75)] tw-shadow-[0px_0.5px_0.5px_#F3D2C9,0px_1px_0.5px_rgba(239,239,239,0.5)] tw-rounded tw-text-[12px] tw-font-semibold tw-text-black">
                  {{ 'Aplicar' }}
                </button>
              </form>
            </div>
            <hr class="tw-h-px tw-bg-[rgba(16,86,82,0.75)] tw-border-0" />
            <div>
              <span class="tw-block tw-text-[13px] tw-font-semibold tw-text-black tw-mb-2">{{ 'PAYMENT' }}</span>
              <div class="tw-grid tw-grid-cols-[10fr_1fr] tw-gap-y-1">
                <span class="tw-text-[12px] tw-font-semibold tw-text-black tw-text-left">{{ 'Subtotal:' }}</span>
                <span class="tw-text-[13px] tw-font-semibold tw-text-black tw-text-right">{{ 'uwu' + '€' }}</span>
                <span class="tw-text-[12px] tw-font-semibold tw-text-black tw-text-left">{{ 'Shipping:' }}</span>
                <span class="tw-text-[13px] tw-font-semibold tw-text-black tw-text-right">{{ 'uwu' + '€' }}</span>
                <span class="tw-text-[12px] tw-font-semibold tw-text-black tw-text-left">{{ 'Impuestos: ' }}</span>
                <span class="tw-text-[13px] tw-font-semibold tw-text-black tw-text-right">{{ 'uwu' + '€' }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
  
      <!-- Checkout Card -->
      <div class="tw-w-full tw-bg-[#FFFAEB] tw-shadow-[0px_187px_75px_rgba(0,0,0,0.01),0px_105px_63px_rgba(0,0,0,0.05),0px_47px_47px_rgba(0,0,0,0.09),0px_12px_26px_rgba(0,0,0,0.1),0px_0px_0px_rgba(0,0,0,0.1)]">
        <div class="tw-flex tw-items-center tw-justify-between tw-px-5 tw-py-2.5 tw-bg-[rgba(16,86,82,0.5)]">
          <label class="tw-relative tw-text-[22px] tw-font-black tw-text-[#2B2B2F]">{{ cartTotal + '€'  }}</label>
          <button class="tw-flex tw-justify-center tw-items-center tw-w-[150px] tw-h-9 tw-bg-[rgba(16,86,82,0.55)] tw-shadow-[0px_0.5px_0.5px_rgba(16,86,82,0.75),0px_1px_0.5px_rgba(16,86,82,0.75)] tw-border tw-border-[rgb(16,86,82)] tw-rounded-[7px] tw-text-[13px] tw-font-semibold tw-text-black tw-transition-all tw-duration-300 tw-ease-[cubic-bezier(0.15,0.83,0.66,1)]" @click="checkout">
            {{ 'Pagar' }}
          </button>
        </div>
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

    function checkout() {
        alert('Proceeding to checkout...');
    }

</script>