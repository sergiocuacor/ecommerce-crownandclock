<template>

  <!-- SUCCESS -->
  <section v-if="!loading && !error" id="itemCarousel" class="carousel slide" data-bs-ride="carousel">
    <!-- SLIDE BUTTONS -->
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

    <!-- SLIDES -->
    <div class="carousel-inner">
      <div v-for="(item, index) in items" :key="'slide-' + index" :class="['carousel-item', { active: index === 0 }]">
        <div class="ratio ratio-16x9">
          <ImageExtensionCheckerComponent :class="`object-fit-contain p-4`" :src="apiBaseURL + `/images/` + item.mask + `/image.png`" :alt="item.mask || 'unknown-product'"/>
        </div>
        <div class="carousel-caption d-none d-md-block p-3 tw-rounded-md tw-bg-gradient-to-b tw-from-gray-700 tw-from-30% tw-via-gray-600 tw-via-70% tw-to-gray-500 tw-to-100%">
          <h5>{{ item.name || 'Unknown Product' }}</h5>
          <p>{{ item.description || '' }}</p>
        </div>
      </div>
    </div>

    <!-- CONTROLS -->
    <button class="carousel-control-prev" type="button" data-bs-target="#itemCarousel" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">{{ 'Anterior' }}</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#itemCarousel" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">{{ 'Siguiente' }}</span>
    </button>
  </section>

  <!-- LOADING -->
  <section v-if="loading" class="tw-aspect-square lg:tw-aspect-video tw-relative">
    <div class="tw-absolute tw-top-1/2 tw-left-1/2 tw-transform tw--translate-x-1/2 tw--translate-y-1/2">
      <div class="spinner-grow tw-text-sky-400/100">
        <span class="visually-hidden">{{ 'Loading...' }}</span>
      </div>
      <div class="spinner-grow tw-text-sky-400/100 tw-mx-1.5">
        <span class="visually-hidden">{{ 'Loading...' }}</span>
      </div>
      <div class="spinner-grow tw-text-sky-400/100">
        <span class="visually-hidden">{{ 'Loading...' }}</span>
      </div>      
    </div>
  </section>

  <!-- ERROR -->
  <section v-if="error" class="tw-aspect-square lg:tw-aspect-video tw-relative border border-3 rounded">        
    <div class="tw-absolute tw-top-1/2 tw-left-1/2 tw-transform tw--translate-x-1/2 tw--translate-y-1/2">
      <div class="flex flex-col tw-text-center tw-font-semibold tw-rounded-lg">
        <div class="tw-text-xl md:tw-text-5xl"><i class="bi bi-exclamation-circle-fill tw-text-red-500"></i></div>
        <div class="tw-text-xs md:tw-text-xl">{{ 'Error loading slides' }}</div>
      </div>     
    </div>
  </section>
</template>

<script setup>
  
  import { ref, onMounted } from 'vue';
  import apiClient from '../../services/api.js';

  const apiBaseURL = ref(apiClient.getApiBaseURL());
  
  const items = ref([]);
  const loading = ref(true);
  const error = ref(null);

  const getRandomItems = (array, count) => {
    const shuffled = [...array].sort(() => 0.5 - Math.random());
    return shuffled.slice(0, count);
  };
  
  const fetchItems = async () => {

    const response = await apiClient.getItems();

    if (response.success) {
        items.value = getRandomItems(response.data, 3);
    } else {
        error.value = response.error;
    }

  };
  
  onMounted(async () => {
    loading.value = true;
    await fetchItems();
    loading.value = false;
  });

</script>  