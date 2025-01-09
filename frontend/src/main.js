import { createPinia } from 'pinia';
import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import './assets/custom.scss'; //CSS
import 'bootstrap'; //JavaScript
import 'bootstrap-icons/font/bootstrap-icons.css'; //Icons
import './style.css' // TailWind

const app = createApp(App);
app.use(createPinia());
app.use(router);
app.mount('#app');