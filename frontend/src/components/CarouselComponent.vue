<template>
      <section v-if="items.length" id="itemCarousel" class="carousel slide" data-bs-ride="carousel">
        <!-- Indicadores -->
        <div class="carousel-indicators">
          <button v-for="(item, index) in items"
            :key="'indicator-' + index"
            :data-bs-target="'#itemCarousel'"
            :data-bs-slide-to="index"
            :class="{ active: index === 0 }"
            aria-current="true"
            :aria-label="'Slide ' + (index + 1)"
          ></button>
        </div>
  
        <!-- Slides -->
        <div class="carousel-inner">
          <div v-for="(item, index) in items" :key="'slide-' + index" :class="['carousel-item', { active: index === 0 }]">
            <div class="ratio ratio-16x9">
              <img :src="item.image" :alt="item.id || 'Product'" class="object-fit-contain p-4" />
            </div>
            <div class="carousel-caption d-none d-md-block p-3 tw-rounded-md tw-bg-gradient-to-b tw-from-gray-700 tw-from-30% tw-via-gray-600 tw-via-70% tw-to-gray-500 tw-to-100%">
              <h5>{{ item.title || 'Product' }}</h5>
              <p>{{ item.description || '' }}</p>
            </div>
          </div>
        </div>
  
        <!-- Controles -->
        <button class="carousel-control-prev" type="button" data-bs-target="#itemCarousel" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">{{ 'Anterior' }}</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#itemCarousel" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">{{ 'Siguiente' }}</span>
        </button>
      </section>
  
      <section v-else>
        <p class="text-center">{{ 'Cargando imágenes...' }}</p>
      </section>
  </template>
  
  <script setup>
  
    import { ref, onMounted } from 'vue';
    import apiClient from '../services/api.js';
    
    const items = ref([]);
    const loading = ref(true);
    const error = ref(null);
    
    const fetchItems = async () => {
      try {
        const response = await apiClient.getItems();
        items.value = getRandomItems(response.data, 3);
      } catch (err) {
        error.value = 'Error al cargar los productos';
      }
    };
    
    const getRandomItems = (array, count) => {
      const shuffled = [...array].sort(() => 0.5 - Math.random());
      return shuffled.slice(0, count);
    };
    
    onMounted(async () => {
      loading.value = true;
      await fetchItems();
      loading.value = false;
    });

  </script>  