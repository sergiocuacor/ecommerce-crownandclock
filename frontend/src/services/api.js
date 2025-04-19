import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    'Content-Type': 'application/json',
  },
});

apiClient.interceptors.request.use(config => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

async function apiCall(method, url, params = {}, data = {}) {
  try {
    const response = await apiClient({ method, url, params, data });
    return { success: true, data: response.data };
  } catch (err) {
    return { success: false, error: err.response?.data || 'Error desconocido' };
  }
};

export default {
  getApiBaseURL() {
    return apiClient.defaults.baseURL;
  },
  getItem(mask) {
    return apiCall('get', `/products/${mask}`);
  },
  getItems() {
    return apiCall('get', '/products/all');
  },
  getItemsPageable(page = 0, size = 12, sort = 'name,asc', brand = '') {
    return apiCall('get', '/products', { page, size, sort, brand });
  },
  getItemStock(id) {
    return apiCall('get', `/products/stock/${id}`);
  },
  getBrandById(id) {
    return apiCall('get', `/brands/${id}`);
  },
  getBrands() {
    return apiCall('get', '/brands/all');
  },
  postOrder(data) {
    return apiCall('post', '/orders', {}, data);
  },
  getValidDiscountsForUser(id) {
    return apiCall('get', `/orders/discounts/${id}`);
  },
  postLoginUser(data) {
    return apiCall('post', '/login', {}, data);
  },
  postRegisterUser(data) {
    return apiCall('post', '/users/register', {}, data);
  },
  getUserData() {
    return apiCall('get', '/users/me');
  }
};