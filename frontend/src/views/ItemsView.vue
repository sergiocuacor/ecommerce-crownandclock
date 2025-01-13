<template>    
    <main class="container py-5">
      <section class="row py-2">
        <div class="col-6">
          <BrandSelector @brandSelected="handleBrandSelected" />
        </div>     
      </section>
      <section class="row py-2">
        <div class="col-12">
          <h1>{{ 'Marca seleccionada: ' + selectedBrandId }}</h1>
        </div>
      </section>
      <ItemsComponent />
    </main>
</template>

<script setup>

    import { ref } from 'vue';

    const selectedBrandId = ref(null);

    const handleBrandSelected = (brandId) => {
        selectedBrandId.value = brandId;
    };
    
</script>