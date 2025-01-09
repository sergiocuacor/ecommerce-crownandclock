import { defineStore } from 'pinia';

export const useCartStore = defineStore('cart', {
  state: () => ({
    items: [],
  }),
  actions: {
    addToCart(product) {
      const existingItem = this.items.find(item => item.id === product.id);
      if (existingItem) {
        existingItem.quantity++;
      } else {
        this.items.push({ ...product, quantity: 1 });
      }
    },
    removeFromCart(productId) {
      this.items = this.items.filter(item => item.id !== productId);
    },
    increaseQuantity(productId) {
      const item = this.items.find(item => item.id === productId);
      if (item) {
        item.quantity++;
      }
    },
    decreaseQuantity(productId) {
      const item = this.items.find(item => item.id === productId);
      if (item && item.quantity > 1) {
        item.quantity--;
      }
    },
  },
});