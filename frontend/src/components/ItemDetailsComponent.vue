<template>
    <div class="product-detail">
      <img :src="product.image" :alt="product.name" />
      <h1>{{ product.name }}</h1>
      <p>{{ product.description }}</p>
      <p>{{ product.price | currency }}</p>
      <button @click="addToCart">Add to Cart</button>
    </div>
  </template>
  
  <script>
  import { useCartStore } from '../store/cart';
  
  export default {
    data() {
      return {
        product: {},
      };
    },
    async created() {
      const { id } = this.$route.params;
      const response = await api.getProduct(id);
      this.product = response.data;
    },
    methods: {
      addToCart() {
        const cartStore = useCartStore();
        cartStore.addToCart(this.product);
      },
    },
  };
  </script>  