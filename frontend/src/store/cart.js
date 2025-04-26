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

const fetchUserId = async () => {

  const response = await apiClient.getUserData();

  let userId = null;

  if (response.success) {

    userId = response.data.id;

  }

  return userId;

};

const fetchDiscounts = async (userId) => {

  const response = await apiClient.getValidDiscountsForUser(userId);

  let valid_discounts = [];

  if (response.success) {

    valid_discounts = response.data;

  }

  return valid_discounts;

};

export const useCartStore = defineStore('cart', {

  state: () => ({

    items: JSON.parse(localStorage.getItem('cartItems')) || [],
    coupon: JSON.parse(localStorage.getItem('discountCoupon') || null)

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
    
    async couponValidation(couponName) {

      const userId = await fetchUserId();

      if(userId != null) {

        const valid_coupons = await fetchDiscounts(userId);

        if(valid_coupons.length > 0) {

          const foundCoupon = valid_coupons.find(
            code => code.name.toLowerCase() === couponName.toLowerCase()
          );
          
          if (foundCoupon) {
            this.coupon = foundCoupon;
            this.saveCoupon();
            return;
          }

        }

      }

      this.coupon = null;
      this.saveCoupon();

    },

    saveCoupon() {

      localStorage.setItem('discountCoupon', JSON.stringify(this.coupon));

    },

    emptyCart() {

      this.items = [];
      this.saveCart();
      this.coupon = null;
      this.saveCoupon();

    }

  },
  
});