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
  getBrand(mask) {
    return apiClient.get(`/brands/${mask}`);
  },
  getBrands() {
    return apiClient.get('/brands');
  },
  getCategory(mask) {
    return apiClient.get(`/categories/${mask}`);
  },
  getCategories() {
    return apiClient.get('/products/categories');
  },
  getItemsFromBrand(mask) {
    return apiClient.get(`/products/brand/${mask}`);
  }, 
  getItemsFromCategory(mask) {
    return apiClient.get(`/products/category/${mask}`);
  },  
};