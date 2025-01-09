<template>
    <div class="cart">
      <h1>Your Shopping Cart</h1>
      <div v-if="cartItems.length === 0" class="empty-cart">
        <p>Your cart is empty.</p>
      </div>
      <div v-else class="cart-items">
        <div
          v-for="item in cartItems"
          :key="item.id"
          class="cart-item"
        >
          <img :src="item.image" :alt="item.name" />
          <div class="details">
            <h3>{{ item.name }}</h3>
            <p>Price: {{ item.price | currency }}</p>
            <div class="quantity-controls">
              <button @click="decreaseQuantity(item)">-</button>
              <span>{{ item.quantity }}</span>
              <button @click="increaseQuantity(item)">+</button>
            </div>
            <button class="remove" @click="removeFromCart(item.id)">Remove</button>
          </div>
        </div>
      </div>
      <div v-if="cartItems.length > 0" class="cart-summary">
        <h2>Total: {{ cartTotal | currency }}</h2>
        <button class="checkout" @click="checkout">Proceed to Checkout</button>
      </div>
    </div>
  </template>
  
  <script>
  import { useCartStore } from '../store/cart';
  
  export default {
    name: 'CartView',
    computed: {
      cartItems() {
        const cartStore = useCartStore();
        return cartStore.items;
      },
      cartTotal() {
        const cartStore = useCartStore();
        return cartStore.items.reduce((total, item) => total + item.price * item.quantity, 0);
      },
    },
    methods: {
      increaseQuantity(item) {
        const cartStore = useCartStore();
        cartStore.increaseQuantity(item.id);
      },
      decreaseQuantity(item) {
        const cartStore = useCartStore();
        if (item.quantity > 1) {
          cartStore.decreaseQuantity(item.id);
        } else {
          cartStore.removeFromCart(item.id);
        }
      },
      removeFromCart(productId) {
        const cartStore = useCartStore();
        cartStore.removeFromCart(productId);
      },
      checkout() {
        alert('Proceeding to checkout...');
        // Aquí puedes agregar lógica para procesar el pago.
      },
    },
    filters: {
      currency(value) {
        return `$${value.toFixed(2)}`;
      },
    },
  };
  </script>
  
  <style>
  .cart {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
  }
  .cart-items {
    margin: 20px 0;
  }
  .cart-item {
    display: flex;
    gap: 20px;
    margin-bottom: 20px;
  }
  .cart-item img {
    width: 100px;
    height: auto;
  }
  .details {
    flex: 1;
  }
  .quantity-controls {
    display: flex;
    align-items: center;
    gap: 10px;
  }
  .remove {
    background-color: red;
    color: white;
    border: none;
    padding: 5px 10px;
    cursor: pointer;
  }
  .cart-summary {
    text-align: right;
    margin-top: 20px;
  }
  .checkout {
    background-color: green;
    color: white;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
  }
  </style>  