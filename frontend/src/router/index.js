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
import CategoriesView from '../views/CategoriesView.vue';
import CategoryView from '../views/CategoryView.vue';
import SocialsView from '../views/SocialsView.vue';
import UserProfileView from '../views/UserProfileView.vue';

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
  // Categories
  { 
    path: '/categories',
    name: 'categories',
    component: CategoriesView,
    meta: { 
      label: 'Categories', 
      breadcrumb: [{ label: 'Home', path: { name: 'home' } }] 
    } 
  },
  {
    path: '/category',
    redirect: { name: 'categories' }
  },
  {
    path: '/products/categories',
    redirect: { name: 'categories' }
  },
  {
    path: '/products/category',
    redirect: { name: 'categories' }
  },
  // Category
  { 
    path: '/categories/:mask',
    name: 'category',
    component: CategoryView, 
    meta: { 
      label: 'Category', 
      breadcrumb: [
        { label: 'Home', path: { name: 'home' } },
        { label: 'Categories', path: { name: 'categories' } }
      ] 
    } 
  },
  {
    path: '/category/:mask',
    redirect: { name: 'category' }
  },
  {
    path: '/products/categories/:mask',
    redirect: { name: 'category' }
  },
  {
    path: '/products/category/:mask',
    redirect: { name: 'category' }
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
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach(async (to, from, next) => {
  
  const auth = useAuthStore();

  if (to.meta.requiresAuth && !auth.token) {

    next('/login'); // 🔁 redirige a login si no hay token

  } else {

    let defaultPageTitle = 'Crown & Clock';
    let pageTitle = defaultPageTitle;

    if (to.meta.label) {

      pageTitle = defaultPageTitle + ' - ' + to.meta.label;
      
    }

    if (to.meta.breadcrumb) {

      let breadcrumbs = [...to.meta.breadcrumb];

      if (to.params.mask) {

        try {

          const response = await apiClient.getItem(to.params.mask);
          let item = response.data;

          if (item) {

            breadcrumbs.push({ label: item.title, path: to.path });
            pageTitle = defaultPageTitle + ' - ' + item.title;

          } else {

            breadcrumbs.push({ label: 'Unknown Product', path: to.path });
            pageTitle = defaultPageTitle + ' - ' + 'Unknown Product';

          }

        } catch (error) {

          console.error('Error Fetching Item:', error);
          breadcrumbs.push({ label: 'Error Loading Product', path: to.path });

        }

      } else {

        breadcrumbs.push({ label: to.meta.label, path: to.path });

      }

      to.meta.breadcrumbs = breadcrumbs;
      
    }

    document.title = pageTitle;

    next(); // ✅ continúa
    
  }  

});

export default router;