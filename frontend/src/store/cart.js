import { defineStore } from 'pinia';

export const useCartStore = defineStore('cart', {

  state: () => ({

    items: JSON.parse(localStorage.getItem('cartItems')) || [],

  }),

  actions: {

    addToCart(product) {

      const existingItem = this.items.find(item => item.id === product.id);

      if (existingItem) {

        existingItem.quantity++;

      } else {

        this.items.push({ ...product, quantity: 1 });

      }

      this.saveCart();

    },

    removeFromCart(productId) {

      this.items = this.items.filter(item => item.id !== productId);
      this.saveCart();

    },

    increaseQuantity(productId) {

      const item = this.items.find(item => item.id === productId);

      if (item) {

        item.quantity++;
        this.saveCart();

      }

    },

    decreaseQuantity(productId) {

      const item = this.items.find(item => item.id === productId);

      if (item && item.quantity > 1) {

        item.quantity--;
        this.saveCart();

      }

    },

    saveCart() {

      localStorage.setItem('cartItems', JSON.stringify(this.items));

    },

  },
  
});