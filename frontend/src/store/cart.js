import { defineStore } from 'pinia';
import apiClient from '../services/api.js';

const fetchItemStock = async (item) => {

  const response = await apiClient.getItemStock(item.id);

  let stock = item.stock;

  if (response.success) {
      stock = response.data;
  }

  return stock;

};

export const useCartStore = defineStore('cart', {

  state: () => ({

    items: JSON.parse(localStorage.getItem('cartItems')) || [],

  }),

  actions: {

    async addToCart(product) {

      const existingItem = this.items.find(item => item.id === product.id);

      if (existingItem) {

        existingItem.stock = await fetchItemStock(existingItem);

        if(existingItem.stock && (existingItem.quantity < existingItem.stock)) {
          existingItem.quantity++;
        } else if (existingItem.stock && (existingItem.quantity >= existingItem.stock)) {
          existingItem.quantity = existingItem.stock;
        }

      } else {

        product.stock = await fetchItemStock(product);

        if(product.stock && product.stock >= 1) {
          this.items.push({ ...product, quantity: 1 });
        }        

      }

      this.saveCart();

    },

    removeFromCart(productId) {

      this.items = this.items.filter(item => item.id !== productId);
      this.saveCart();

    },

    async increaseQuantity(productId) {

      const item = this.items.find(item => item.id === productId);

      if (item) {

        item.stock = await fetchItemStock(item);

        if (item.stock && (item.quantity < item.stock)) {
          item.quantity++;          
        } else if (item.stock && (item.quantity >= item.stock)) {
          item.quantity = item.stock;
        }

      }
      
      this.saveCart();

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