<template>

  <!-- SUCCESS -->
  <div v-if="!loading && !error" class="row overflow-hidden sm:tw-border-2 sm:tw-rounded-lg">

      <div class="col-12 col-lg-4 tw-bg-cyan-500/10">
          <div class="ratio ratio-1x1">
            <img class="object-fit-contain p-1" :src="'/watch_back.avif'" :alt="item.name">
          </div>          
      </div>

      <div class="col-12 col-lg-8 tw-bg-cyan-500/25">
        <div class="tw-grid tw-content-center tw-justify-start tw-space-y-2 tw-p-4 tw-h-full">
          <div class="tw-font-semibold tw-text-2xl">{{ item.name }}</div>
          <div class="tw-font-semibold tw-text-lg">{{ 'Precio: ' + item.price.toFixed(2) + '€'}}</div>
          <div class="tw-font-semibold tw-text-lg">{{ 'Disponibles: ' + item.stock }}</div>
          <div class="tw-font-semibold tw-text-lg">{{ 'Marca: ' + item.brand_name }}</div>
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
  <div v-if="loading" class="tw-m-4 sm:tw-m-0">
    <WordsLoaderComponent :mainWords="`cargando...`" :secondaryWords="['nombre', 'descripción', 'precio', 'existencias', 'marca']"/>
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

    const props = defineProps({
        itemMask: {
            type: String,
            default: '',
        },
    });

    const item = ref([]);
    const loading = ref(true);
    const error = ref(null); 

    // const fetchItem = async () => {
    //     try {
    //         const response = await apiClient.getItem(props.itemMask);
    //         item.value = response.data;
    //     } catch (err) {
    //         error.value = 'Error al cargar el producto';
    //     }
    // };

    // onMounted(async () => {
    //     loading.value = true;
    //     await Promise.all([fetchItem()]);
    //     loading.value = false;
    // });

    onMounted(async () => {
        loading.value = true;
        setTimeout(() => {
            loading.value = false;        

            if(props.itemMask == "audemars-piguet-26470st-royal-oak-offshore"){

                item.value = {
                name: '26470ST Royal Oak Offshore',
                description: 'Emblemático Royal Oak Offshore con caja de acero inoxidable de 42mm, cronógrafo automático y esfera "Méga Tapisserie". Resistente al agua hasta 100m y brazalete integrado de acero.',
                price: 34500.00,
                stock: 3,
                mask: 'audemars-piguet-26470st-royal-oak-offshore',
                brand_id: 1,
                brand_name: 'Audemars Piguet'
                };

            } else if(props.itemMask == "hermes-ha3210-harnais"){

                item.value = {
                name: 'HA3.210 Harnais',
                description: 'Harnais con diseño inspirado en los arneses ecuestres, esfera guilloché y movimiento de precisión',
                price: 3950.00,
                stock: 4,
                mask: 'hermes-ha3210-harnais',
                brand_id: 4,
                brand_name: 'Hermès'
                };

            } else {

                error.value = 'Error al cargar el producto';

            }
      }, 4000);
    });

</script>