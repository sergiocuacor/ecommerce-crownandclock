import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    'Content-Type': 'application/json',
  },
});

async function apiCall(method, url, params = {}, data = {}) {
  try {
    const response = await apiClient({ method, url, params, data });
    return { success: true, data: response.data };
  } catch (err) {
    return { success: false, error: err.response?.data || 'Error desconocido' };
  }
}

export default {
  getApiBaseURL() {
    return apiClient.defaults.baseURL;
  },
  getItem(mask) {
    return apiCall('get', `/product/${mask}`);
  },
  getItems() {
    return apiCall('get', '/product/all');
  },
  getItemsPageable(page = 0, size = 12, sort = 'name,asc') {
    return apiCall('get', '/product', { page, size, sort });
  },
  getBrandById(id) {
    return apiCall('get', `/brand/${id}`);
  },
  getBrands() {
    return apiCall('get', '/brand');
  }
};