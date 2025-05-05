import axios from 'axios';

const baseURL_ENV = import.meta.env.VITE_API_URL || "http://localhost:8080";
const apiClient = axios.create({
  baseURL: baseURL_ENV,
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
    return { success: false, error: err.response?.data || 'Unknown error' };
  }
};

export default {
  getApiBaseURL() {
    return apiClient.defaults.baseURL;
  },
  // Items
  getItemById(id) {
    return apiCall('get', `/products/${id}`);
  },
  getItemByMask(mask) {
    return apiCall('get', `/products/mask/${mask}`);
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
  putItemData(id, data) {
    return apiCall('put', `/products/${id}`, {}, data);
  },
  deleteItemById(id) {
    return apiCall('delete', `/products/${id}`);
  },
  // Brands
  getBrandById(id) {
    return apiCall('get', `/brands/${id}`);
  },
  getBrands() {
    return apiCall('get', '/brands/all');
  },
  // Users 
  postLoginUser(data) {
    return apiCall('post', '/login', {}, data);
  },
  postRegisterUser(data) {
    return apiCall('post', '/users/register', {}, data);
  },
  getUserData() {
    return apiCall('get', '/users/me');
  },
  putUserData(id, data) {
    return apiCall('put', `/users/${id}`, {}, data);
  },
  getAllUsers() {
    return apiCall('get', '/users/all');
  },
  getTokenValidation() {
    return apiCall('get', '/auth/validate-token');
  },
  // Orders & Cart
  getValidDiscountsForUser(id) {
    return apiCall('get', `/orders/discounts/${id}`);
  },  
  postOrder(data) {
    return apiCall('post', '/orders', {}, data);
  },
  putOrderData(id, data) {
    return apiCall('put', `/orders/${id}`, {}, data);
  },
  getAllOrders() {
    return apiCall('get', '/orders/all');
  },
  //Reviews
  getReviewsByItemId(id) {
    return apiCall('get', `/reviews/product/${id}`);
  },
  getReviewsByUserId(id) {
    return apiCall('get', `/reviews/user/${id}`);
  },
  postNewReview(userId, productId, data) {
    return apiCall('post', `/reviews/${userId}/${productId}`, {}, data);
  },
  //Reports
  getDailySalesReportsXLS() {
    return getSalesReports(`/reports/daily-sales/excel`);
  },
  getDailySalesReportsPDF() {
    return getSalesReports(`/reports/daily-sales/pdf`);
  },
  getMonthlySalesReportsXLS() {
    return getSalesReports(`/reports/monthly-sales/excel`);
  },
  getMonthlySalesReportsPDF() {
    return getSalesReports(`/reports/monthly-sales/pdf`);
  }
};