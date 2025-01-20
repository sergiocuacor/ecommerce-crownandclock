<template>    
    <main class="container py-5">
      <section class="row py-3 g-2">
        <div class="col-12 col-md-6 col-lg-4">
          <BrandSelectorComponent @brandSelected="handleBrandSelected" />
        </div>
        <div class="col-12 col-md-6 col-lg-4">
          <CategorySelectorComponent @categorySelected="handleCategorySelected" />
        </div>
        <div class="col-12 tw-space-x-2">
          <span class="badge text-bg-primary">
            {{ 'Marca: ' + selectedBrandId }}
          </span>
          <span class="badge text-bg-success">
            {{ 'Categoría: ' + selectedCategoryId }}
          </span>
        </div>  
      </section>      
      <ItemsComponent />
    </main>
</template>

<script setup>

    import { ref } from 'vue';

    const selectedBrandId = ref(null);
    const selectedCategoryId = ref(null);

    const handleBrandSelected = (brandId) => {
        selectedBrandId.value = brandId;
    };    

    const handleCategorySelected = (categoryId) => {
        selectedCategoryId.value = categoryId;
    };
    
</script>