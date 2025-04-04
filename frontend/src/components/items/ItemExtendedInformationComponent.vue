<template>

  <!-- SUCCESS -->
  <div v-if="!loading && !error" class="row overflow-hidden sm:tw-border-2 sm:tw-rounded-lg">

      <div class="col-12 col-lg-4 tw-bg-cyan-500/10">
          <div class="ratio ratio-1x1">
            <ImageExtensionCheckerComponent :class="`object-fit-contain p-5`" :src="apiBaseURL + `/images/` + item.mask + `/image.png`" :alt="item.name"/>
          </div>          
      </div>

      <div class="col-12 col-lg-8 tw-bg-cyan-500/25">
        <div class="tw-grid tw-content-center tw-justify-start tw-space-y-2 tw-p-4 tw-h-full">
          <div class="tw-font-semibold tw-text-2xl">{{ item.name }}</div>
          <div class="tw-font-semibold tw-text-lg">{{ 'Precio: ' + item.price.toFixed(2) + '€'}}</div>
          <div class="tw-font-semibold tw-text-lg">{{ 'Disponibles: ' + item.stock }}</div>
          <div class="tw-font-semibold tw-text-lg">{{ 'Marca: ' + item.brand_id }}</div>
        </div>          
      </div>

      <div class="col-12 tw-bg-cyan-500/35">
        <div class="tw-space-y-2 tw-p-4">
          <div class="tw-font-semibold tw-text-lg">{{ 'Descripción: ' }}</div>
          <div class="tw-font-semibold tw-text-lg">{{ item.description }}</div>
        </div>         
      </div>

  </div>

  <!-- LOADING -->
  <div v-if="loading" class="row overflow-hidden sm:tw-border-2 sm:tw-rounded-lg">

      <div class="col-12 col-lg-4 tw-bg-cyan-500/10">
          <div class="ratio ratio-1x1">
            <ImageExtensionCheckerComponent :class="`object-fit-contain`" :src="'/no_image.png'" :alt="item.name"/>
          </div>          
      </div>

      <div class="col-12 col-lg-8 tw-bg-cyan-500/25">
        <div class="tw-grid tw-content-center tw-justify-start tw-space-y-2 tw-p-4 tw-h-full">
          <WordsLoaderComponent :mainWords="`cargando...`" :secondaryWords="['nombre', 'precio', 'existencias', 'marca', 'nombre']"/>
        </div>          
      </div>

      <div class="col-12 tw-bg-cyan-500/35">
        <div class="tw-space-y-2 tw-p-4">
          <WordsLoaderComponent :mainWords="`cargando...`" :secondaryWords="['descripción', 'descripción', 'descripción', 'descripción', 'descripción']"/>
        </div>         
      </div>

  </div>

  <!-- ERROR -->
  <div v-if="error" class="tw-text-center tw-text-red-500">
      <i class="bi bi-exclamation-diamond-fill fs-1"></i>
      <div class="text-center text-xs md:text-2xl">{{ 'Error al cargar el producto o no existe' }}</div>
  </div>

</template>

<script setup>

    import { ref, onMounted, defineProps } from 'vue';
    import apiClient from '../../services/api.js';

    const apiBaseURL = ref(apiClient.getApiBaseURL());

    const props = defineProps({
        itemMask: {
            type: Number,
            default: 0,
            required: true
        },
    });

    const item = ref({});
    const loading = ref(true);
    const error = ref(null); 

    const fetchItem = async () => {

      loading.value = true;

      const response = await apiClient.getItem(props.itemMask);

      if (response.success) {

        item.value = response.data;

      } else {

        error.value = response.error;
        
      }

      setTimeout(() => {
        loading.value = false;
      }, 2500);

    };

    onMounted(fetchItem);

</script>