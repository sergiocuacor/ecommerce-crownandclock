<template>
  <div class="product-list">
    <div v-for="product in products" :key="product.id" class="product">
      <img :src="product.image" :alt="product.name" />
      <h3>{{ product.name }}</h3>
      <p>{{ product.price }}</p>
    </div>
  </div>
</template>

<script>
import api from '../services/api';

export default {
  name: 'ProductList',
  data() {
    return {
      products: [],
    };
  },
  async created() {
    try {
      const response = await api.getProducts();
      this.products = response.data;
    } catch (error) {
      console.error('Error fetching products:', error);
    }
  },
};
</script>

<style>
/* Agrega estilos personalizados aquí */
</style>