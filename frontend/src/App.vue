<template>

  <LoaderWrapperComponent v-if="isLoading">
    <ClockLoaderComponent />
  </LoaderWrapperComponent>

  <CustomNavBarComponent/> 
  <router-view />
  <CustomFooterComponent />  

</template>

<script setup>

  import { ref, watch } from 'vue';
  import { useRoute } from 'vue-router';

  const isLoading = ref(true);
  const route = useRoute();

  watch(route, () => {
    isLoading.value = true;
    setTimeout(() => {
      isLoading.value = false;
    }, 1000);
  }, { immediate: true });
  
</script>