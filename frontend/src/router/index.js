import { createRouter, createWebHistory } from 'vue-router';
import { useAuthStore } from '../services/auth.js';
import apiClient from '../services/api.js';
import NotFoundView from '../views/NotFoundView.vue';
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import RegisterView from '../views/RegisterView.vue';
import CartView from '../views/CartView.vue';
import ItemsView from '../views/ItemsView.vue';
import ItemView from '../views/ItemView.vue';
import BrandsView from '../views/BrandsView.vue';
import BrandView from '../views/BrandView.vue';
import SocialsView from '../views/SocialsView.vue';
import UserProfileView from '../views/UserProfileView.vue';
import UserOrdersView from '../views/UserOrdersView.vue';
import UserReviewsView from '../views/UserReviewsView.vue';
import UserCheckoutView from '../views/UserCheckoutView.vue';
import AdminPanelView from '../views/admin/AdminPanelView.vue';
import AdminUsersManagementView from '../views/admin/AdminUsersManagementView.vue';
import AdminItemsManagementView from '../views/admin/AdminItemsManagementView.vue';
import AdminOrdersManagementView from '../views/admin/AdminOrdersManagementView.vue';
import AdminSalesReportsView from '../views/admin/AdminSalesReportsView.vue';
import PricingView from '../views/PricingView.vue';
import FaqView from '../views/FaqView.vue';
import AboutView from '../views/AboutView.vue';

const routes = [
  // Error/Unknown
  {
    path: '/:pathMatch(.*)*',
    name: 'error',
    component: NotFoundView,
    meta: { 
      label: 'Not Found', 
      breadcrumb: [{ label: 'Home', path: '/' }] 
    }
  },
  // Home
  { 
    path: '/home',
    name: 'home',
    component: HomeView,
    meta: {
      label: 'Home',
      breadcrumb: []
    } 
  },
  { 
    path: '/',
    redirect: { name: 'home' }
  },
  // Login
  { 
    path: '/login',
    name: 'login',
    component: LoginView,
    meta: {
      label: 'Login',
      breadcrumb: [{ label: 'Home', path: { name: 'home' } }]
    } 
  },
  // Register
  { 
    path: '/register',
    name: 'register',
    component: RegisterView,
    meta: {
      label: 'Register',
      breadcrumb: [{ label: 'Home', path: { name: 'home' } }]
    } 
  },
  // User Profile
  { 
    path: '/profile',
    name: 'profile',
    component: UserProfileView,
    meta: {
      label: 'Profile',
      breadcrumb: [{ label: 'Home', path: { name: 'home' } }],
      requiresAuth: true
    } 
  },
  { 
    path: '/user',
    redirect: { name: 'profile' }
  },
  { 
    path: '/users',
    redirect: { name: 'profile' }
  },
  // User Orders With filter
  { 
    path: '/profile/orders/:filter?',
    name: 'orders',
    component: UserOrdersView,
    meta: {
      label: 'Orders',
      breadcrumb: [{ label: 'Home', path: { name: 'home' } }],
      requiresAuth: true
    } 
  },
  { 
    path: '/order',
    redirect: { name: 'orders' }
  },
  { 
    path: '/orders',
    redirect: { name: 'orders' }
  },
  // User Reviews
  { 
    path: '/profile/reviews',
    name: 'reviews',
    component: UserReviewsView,
    meta: {
      label: 'Reviews',
      breadcrumb: [{ label: 'Home', path: { name: 'home' } }],
      requiresAuth: true
    } 
  },
  { 
    path: '/review',
    redirect: { name: 'reviews' }
  },
  { 
    path: '/reviews',
    redirect: { name: 'reviews' }
  },
  //User Checkout
  { 
    path: '/checkout/:filter?',
    name: 'checkout',
    component: UserCheckoutView,
    meta: {
      label: 'Checkout',
      breadcrumb: [{ label: 'Home', path: { name: 'home' } }],
      requiresAuth: true
    } 
  },
  // Cart
  { 
    path: '/products/cart',
    name: 'cart',
    component: CartView, 
    meta: { 
      label: 'Cart', 
      breadcrumb: [{ label: 'Home', path: { name: 'home' } }] 
    } 
  },
  { 
    path: '/cart',
    redirect: { name: 'cart' }
  },
  // Products
  { 
    path: '/products',
    name: 'products',
    component: ItemsView, 
    meta: {
      label: 'Products',
      breadcrumb: [
        { label: 'Home', path: { name: 'home' } }
      ]
    }
  },
  // Product
  { 
    path: '/products/:mask',
    name: 'product',
    component: ItemView, 
    meta: {
      label: 'Product',
      breadcrumb: [
        { label: 'Home', path: { name: 'home' } },
        { label: 'Products', path: { name: 'products' } }
      ]
    } 
  },
  // Brands
  { 
    path: '/brands',
    name: 'brands',
    component: BrandsView, 
    meta: { 
      label: 'Brands', 
      breadcrumb: [{ label: 'Home', path: { name: 'home' } }] 
    } 
  },
  {
    path: '/brand',
    redirect: { name: 'brands' }
  },
  {
    path: '/products/brands',
    redirect: { name: 'brands' }
  },
  {
    path: '/products/brand',
    redirect: { name: 'brands' }
  },
  // Brand
  { 
    path: '/brands/:mask',
    name: 'brand',
    component: BrandView, 
    meta: { 
      label: 'Brand', 
      breadcrumb: [
        { label: 'Home', path: { name: 'home' } },
        { label: 'Brands', path: { name: 'brands' } }
      ] 
    } 
  },
  {
    path: '/brand/:mask',
    redirect: { name: 'brand' }
  },
  {
    path: '/products/brands/:mask',
    redirect: { name: 'brand' }
  },
  {
    path: '/products/brand/:mask',
    redirect: { name: 'brand' }
  },
  // Socials
  { 
    path: '/socials',
    name: 'socials',
    component: SocialsView, 
    meta: { 
      label: 'Socials', 
      breadcrumb: [{ label: 'Home', path: {name: 'home'} }] 
    },
    beforeEnter: (to, from, next) => {
      if (to.query.ref === 'twitter' || to.query.ref === 'x') {
        next({ name: 'twitter' })
      } else if (to.query.ref === 'instagram') {
        next({ name: 'instagram' })
      } else if (to.query.ref === 'facebook') {
        next({ name: 'facebook' })
      } else {
        next()
      }
    }
  },
  // Twitter
  { 
    path: '/socials/twitter',
    name: 'twitter',
    props: { social: 'twitter' },
    component: SocialsView,
    meta: {
      mask: 'twitter',
      url: 'https://x.com/?lang=es',
      label: 'Twitter',
      breadcrumb: [
        { label: 'Home', path: { name: 'home' } },
        { label: 'Socials', path: { name: 'socials' } }
      ]
    } 
  },
  {
    path: '/socials/x',
    redirect: { name: 'twitter'}
  },
  {
    path: '/twitter',
    redirect: { name: 'twitter'}
  },
  {
    path: '/x',
    redirect: { name: 'twitter'}
  },  
  // Instagram
  { 
    path: '/socials/instagram',
    name: 'instagram',
    component: SocialsView,
    meta: {
      mask: 'instagram',
      url: 'https://www.instagram.com',
      label: 'Instagram',
      breadcrumb: [        
        { label: 'Home', path: { name: 'home' } },
        { label: 'Socials', path: { name: 'socials' } }
      ]
    } 
  },
  {
    path: '/instagram',
    redirect: { name: 'instagram'}
  },
  // Facebook
  { 
    path: '/socials/facebook',
    name: 'facebook',
    component: SocialsView,
    meta: {
      mask: 'facebook',
      url: 'https://www.facebook.com/?locale=es_ES',
      label: 'Facebook',
      breadcrumb: [
        { label: 'Home', path: { name: 'home' } },
        { label: 'Socials', path: { name: 'socials' } }
      ]
    } 
  },
  {
    path: '/facebook',
    redirect: { name: 'facebook'}
  },
  // ADMIN - Admin Panel
  { 
    path: '/admin-panel',
    name: 'admin-panel',
    component: AdminPanelView,
    meta: { 
      label: 'Admin Panel', 
      breadcrumb: [
        { label: 'Home', path: { name: 'home' } }
      ],
      requiresAuth: true 
    } 
  },
  {
    path: '/admin',
    redirect: { name: 'admin-panel'}
  },
  {
    path: '/administration',
    redirect: { name: 'admin-panel'}
  },
  // ADMIN - Admin Users Management
  { 
    path: '/admin-panel/users',
    name: 'admin-users',
    component: AdminUsersManagementView,
    meta: { 
      label: 'User Management', 
      breadcrumb: [
        { label: 'Home', path: { name: 'home' } },
        { label: 'Admin Panel', path: { name: 'admin-panel' } }
      ],
      requiresAuth: true 
    } 
  },
  {
    path: '/admin/users',
    redirect: { name: 'admin-users'}
  },
  {
    path: '/administration/users',
    redirect: { name: 'admin-users'}
  },
  // ADMIN - Admin Products Management
  { 
    path: '/admin-panel/products',
    name: 'admin-products',
    component: AdminItemsManagementView,
    meta: { 
      label: 'Products Management', 
      breadcrumb: [
        { label: 'Home', path: { name: 'home' } },
        { label: 'Admin Panel', path: { name: 'admin-panel' } }
      ],
      requiresAuth: true 
    } 
  },
  {
    path: '/admin/products',
    redirect: { name: 'admin-products'}
  },
  {
    path: '/administration/products',
    redirect: { name: 'admin-products'}
  },
  // ADMIN - Admin Orders Management
  { 
    path: '/admin-panel/orders',
    name: 'admin-orders',
    component: AdminOrdersManagementView,
    meta: { 
      label: 'Orders Management', 
      breadcrumb: [
        { label: 'Home', path: { name: 'home' } },
        { label: 'Admin Panel', path: { name: 'admin-panel' } }
      ],
      requiresAuth: true 
    } 
  },
  {
    path: '/admin/orders',
    redirect: { name: 'admin-orders'}
  },
  {
    path: '/administration/orders',
    redirect: { name: 'admin-orders'}
  },
  // ADMIN - Admin Sales Reports
  { 
    path: '/admin-panel/sales_reports',
    name: 'admin-sales-reports',
    component: AdminSalesReportsView,
    meta: { 
      label: 'Sales Reports', 
      breadcrumb: [
        { label: 'Home', path: { name: 'home' } },
        { label: 'Admin Panel', path: { name: 'admin-panel' } }
      ],
      requiresAuth: true 
    } 
  },
  {
    path: '/admin/sales_reports',
    redirect: { name: 'admin-sales-reports'}
  },
  {
    path: '/administration/sales_reports',
    redirect: { name: 'admin-sales-reports'}
  },
  // Pricing
  { 
    path: '/pricing',
    name: 'pricing',
    component: PricingView,
    meta: {
      label: 'Pricing',
      breadcrumb: [
        { label: 'Home', path: { name: 'home' } }
      ]
    } 
  },
  // FAQ
  { 
    path: '/faq',
    name: 'faq',
    component: FaqView,
    meta: {
      label: 'FAQ',
      breadcrumb: [
        { label: 'Home', path: { name: 'home' } }
      ]
    } 
  },
  // About
  { 
    path: '/about',
    name: 'about',
    component: AboutView,
    meta: {
      label: 'About us',
      breadcrumb: [
        { label: 'Home', path: { name: 'home' } }
      ]
    } 
  },
  { 
    path: '/about-us',
    redirect: { name: 'about' }
  },
  { 
    path: '/about_us',
    redirect: { name: 'about' }
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach(async (to, from, next) => {
  
  const auth = useAuthStore();

  if (to.meta.requiresAuth) {

    if (!auth.token) {
      return next('/login');
    }

    await auth.tokenValidation();

    if (!auth.token) {
      return next('/login');
    }

  } else {

    await auth.tokenValidation();
    
  }

  let defaultPageTitle = 'Crown & Clock';
  let pageTitle = defaultPageTitle;

  if (to.meta.label) {
    pageTitle = `${defaultPageTitle} - ${to.meta.label}`;
  }

  if (to.meta.breadcrumb) {
    const breadcrumbs = [...to.meta.breadcrumb];

    if (to.params.mask) {

      if (to.name === 'product') {
        try {
          const response = await apiClient.getItemByMask(to.params.mask);
          const item = response.data;
          if (item) {
            breadcrumbs.push({ label: item.name, path: to.path });
            pageTitle = `${defaultPageTitle} - ${item.name}`;
          } else {
            breadcrumbs.push({ label: 'Unknown Product', path: to.path });
            pageTitle = `${defaultPageTitle} - Unknown Product`;
          }
        } catch (error) {
          console.error('Error fetching product:', error);
          breadcrumbs.push({ label: 'Error Loading Product', path: to.path });
        }
      }
      
      if (to.name === 'brand') {
        try {
          const response = await apiClient.getBrandById(to.params.mask);
          const brand = response.data;
          if (brand) {
            breadcrumbs.push({ label: brand.name, path: to.path });
            pageTitle = `${defaultPageTitle} - ${brand.name}`;
          } else {
            breadcrumbs.push({ label: 'Unknown Brand', path: to.path });
            pageTitle = `${defaultPageTitle} - Unknown Brand`;
          }
        } catch (error) {
          console.error('Error fetching brand:', error);
          breadcrumbs.push({ label: 'Error Loading Brand', path: to.path });
        }
      }

    } else {
      if (!breadcrumbs.find(b => b.path === to.path)) {
        breadcrumbs.push({ label: to.meta.label, path: to.path });
      }
    }

    to.meta.breadcrumbs = breadcrumbs;
  }

  document.title = pageTitle;
  next();

});

export default router;