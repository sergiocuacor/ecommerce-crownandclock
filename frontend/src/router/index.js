import { createRouter, createWebHistory } from 'vue-router';
import apiClient from '../services/api.js';
import NotFoundView from '../views/NotFoundView.vue';
import HomeView from '../views/HomeView.vue';
import ItemsView from '../views/ItemsView.vue';
import ItemView from '../views/ItemView.vue';
import CartView from '../views/CartView.vue';
import SocialsView from '../views/SocialsView.vue';

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
    component: SocialsView, 
    meta: {
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
    redirect: '/',
    name: 'instagram',
    component: SocialsView, 
    meta: {
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
    redirect: '/',
    name: 'facebook',
    component: SocialsView, 
    meta: {
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
  if (to.meta.breadcrumb) {
    let breadcrumbs = [...to.meta.breadcrumb];

    if (to.params.mask) {
      try {
        const response = await apiClient.getItem(to.params.mask);
        let item = response.data;
        if (item) {
          breadcrumbs.push({ label: item.title, path: to.path });
        } else {
          breadcrumbs.push({ label: 'Unknown Product', path: to.path });
        }
      } catch (error) {
        console.error('Error fetching item:', error);
        breadcrumbs.push({ label: 'Error Loading Product', path: to.path });
      }
    } else {
      breadcrumbs.push({ label: to.meta.label, path: to.path });
    }

    to.meta.breadcrumbs = breadcrumbs;
    
  }
  next();
});

export default router;