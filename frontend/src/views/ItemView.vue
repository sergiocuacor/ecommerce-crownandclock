<template>

    <main class="container tw-pt-14 sm:tw-pt-20 sm:tw-pb-6">

      <ItemExtendedInformationComponent :itemMask="itemMask" />
      
    </main>

</template>

<script setup>

  import { computed } from 'vue';
  import { useRoute } from 'vue-router';

  const route = useRoute();
  const itemMask = computed(() => route.params.mask);

</script>