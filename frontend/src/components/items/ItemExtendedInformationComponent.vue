<template>

  <!-- SUCCESS -->
  <div v-if="!loading && !error" class="row overflow-hidden sm:tw-border-2 sm:tw-rounded-lg">

      <div class="col-12 col-lg-4 tw-bg-gray-400/10">
          <div class="ratio ratio-1x1">
            <ImageExtensionCheckerComponent :class="`object-fit-contain p-5`" :src="apiBaseURL + `/images/` + item.mask + `/image.png`" :alt="item.name"/>
          </div>            
      </div>

      <div class="col-12 col-lg-8 tw-bg-gray-400/25 tw-font-serif">
        <div class="tw-grid tw-content-center tw-justify-start tw-space-y-2 tw-p-4 tw-h-full">
          <div class="tw-font-semibold tw-text-2xl">
            {{ item.name }}
          </div>
          <div class="tw-font-semibold tw-text-lg">
            {{ 'Price: ' + item.price.toFixed(2) + '€'}}
          </div>
          <div class="tw-font-semibold tw-text-lg">
            {{ 'Stock: ' + item.stock }}
          </div>
          
        </div>          
      </div>

      <div class="col-12 col-lg-4 tw-bg-gray-400/50 tw-font-serif">
        <ItemRatingComponent v-if="!loading && !error" :averageRating="item.averageRating" :ratingCount="item.ratingCount" :class="`tw-m-2`"/>
      </div>
      <div class="col-12 col-lg-8 tw-bg-gray-400/45 tw-font-serif">
        <router-link :to="{ name: 'brand', params: { mask: item.idBrand } }" class="tw-bg-black hover:tw-bg-gray-800 tw-text-white tw-font-bold tw-uppercase tw-rounded-md tw-flex tw-items-center tw-justify-center tw-p-1.5 tw-m-2 tw-shadow-md tw-cursor-pointer tw-transition tw-delay-150 tw-duration-300 tw-ease-in-out hover:tw-scale-95">
          {{ item.nameBrand }}
        </router-link>   
      </div>

      <div class="col-12 tw-bg-gray-400/35 tw-p-4 tw-space-y-2">
        <div class="tw-space-y-2">
          <div class="tw-font-semibold tw-text-lg">{{ 'Description: ' }}</div>
          <div class="tw-font-semibold tw-text-lg">{{ item.description }}</div>
        </div>
        
      </div>

  </div>
  
  <ReviewsComponent v-if="!loading && !error" :itemId="item.id"/>

  <!-- LOADING -->
  <div v-if="loading" class="row overflow-hidden sm:tw-border-2 sm:tw-rounded-lg">

      <div class="col-12 col-lg-4 tw-bg-gray-400/10">
          <div class="ratio ratio-1x1">
            <ImageExtensionCheckerComponent :class="`object-fit-contain`" :src="'/no_image.png'" :alt="item.name"/>
          </div>          
      </div>

      <div class="col-12 col-lg-8 tw-bg-gray-400/25">
        <div class="tw-grid tw-content-center tw-justify-start tw-space-y-2 tw-p-4 tw-h-full">
          <WordsLoaderComponent :mainWords="`cargando...`" :secondaryWords="['nombre', 'precio', 'existencias', 'marca', 'nombre']"/>
        </div>          
      </div>

      <div class="col-12 tw-bg-gray-400/35">
        <div class="tw-space-y-2 tw-p-4">
          <WordsLoaderComponent :mainWords="`cargando...`" :secondaryWords="['descripción', 'descripción', 'descripción', 'descripción', 'descripción']"/>
        </div>         
      </div>

  </div>

  <!-- ERROR -->
  <div v-if="error" class="row overflow-hidden sm:tw-border-2 sm:tw-rounded-lg">

    <div class="col-12 col-lg-4 tw-bg-gray-400/10">
        <div class="ratio ratio-1x1">
          <ImageExtensionCheckerComponent :class="`object-fit-contain`" :src="'/error_image.png'" :alt="item.name"/>
        </div>          
    </div>

    <div class="col-12 col-lg-8 tw-bg-gray-400/25">
      <div class="tw-grid tw-content-center tw-justify-center tw-space-y-2 tw-p-4 tw-h-full tw-w-full">
        <div class="tw-text-center tw-text-red-500 tw-font-semibold tw-text-lg">
            <i class="bi bi-exclamation-diamond-fill fs-1"></i>
            <div class="text-center text-xs md:text-2xl">
              {{ 'Unable to load the product or it may not exist.' }}
            </div>
        </div>
      </div>          
    </div>

    <div class="col-12 tw-bg-gray-400/35">
      <div class="tw-space-y-2 tw-p-4">
        <div class="tw-text-center tw-text-red-500 tw-font-semibold tw-text-lg">
          <i class="bi bi-exclamation-diamond-fill fs-1"></i>
          <div class="text-center text-xs md:text-2xl">
            {{ 'Unable to load the product or it may not exist.' }}
          </div>
        </div>
      </div>         
    </div>

  </div>

</template>

<script setup>

    import { ref, onMounted, defineProps } from 'vue';
    import apiClient from '../../services/api.js';

    const apiBaseURL = ref(apiClient.getApiBaseURL());

    const props = defineProps({
        itemMask: {
          type: String,
          required: true
        },
    });

    const item = ref({});
    const loading = ref(true);
    const error = ref(null); 

    const fetchItem = async () => {

      loading.value = true;

      const response = await apiClient.getItemByMask(props.itemMask);

      setTimeout(() => {

        if (response.success) {

          if (response.data && Object.keys(response.data).length > 0) {

            error.value = null;
            item.value = response.data;

          } else {

            error.value = 'Product not found';

          }

        } else {        
        
          error.value = response.error.error;            

        }
        
        loading.value = false;

      }, 2500);      

    };

    onMounted(fetchItem);

</script>