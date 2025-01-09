import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'https://fakestoreapi.com',
  headers: {
    'Content-Type': 'application/json',
  },
});

export default {
  getItem(mask) {
    return apiClient.get(`/products/${mask}`);
  },
  getItems() {
    return apiClient.get('/products');
  },
  getCategories() {
    return apiClient.get('/products/categories');
  },
  getItemsFromCategory(mask) {
    return apiClient.get(`/products/category/${mask}`);
  },  
};