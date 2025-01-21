<template>

  <!-- FULL -->
  <main v-if="cartStore.items.length > 0" class="container tw-pt-20 tw-pb-2.5">
    <div class="tw-text-2xl tw-text-center tw-p-5 tw-bg-gray-300 tw-border-2 tw-rounded-t-lg">
      {{ 'Carrito' }}
    </div>
    <CartItemsComponent />
    <CartCheckoutComponent />
  </main>
    
  <!-- EMPTY -->    
  <DynamicHeightDivComponent v-else>
    <template #header>
        <div class="tw-font-mono tw-text-md lg:tw-text-xl tw-bg-gray-200 tw-px-2 tw-rounded">
            {{ 'Carrito Vacío' }}
        </div>
    </template>
    <template #footer >
        <div class="tw-font-mono tw-text-xs lg:tw-text-base">
            {{ 'No hay productos' }}
        </div>
    </template>
  </DynamicHeightDivComponent>

</template>

<script setup>

  import { useCartStore } from '../store/cart.js';    

  const cartStore = useCartStore();

</script>