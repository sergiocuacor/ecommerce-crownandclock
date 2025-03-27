<template>    
    <main class="container pt-5 pb-3">
      <section class="row py-3 g-2">
        <div class="col-12 col-md-6 col-lg-4">
          <BrandSelectorComponent @brandSelected="handleBrandSelected" />
        </div>
        <div class="col-12 col-md-6 col-lg-4">
          <CategorySelectorComponent @categorySelected="handleCategorySelected" />
        </div>
        <div class="col-12 tw-space-x-2">
          <span class="badge text-bg-primary">
            {{ 'Marca: ' + selectedBrand }}
          </span>
          <span class="badge text-bg-success">
            {{ 'Categoría: ' + selectedCategory }}
          </span>
        </div>
      </section>  
      <ItemsComponent />      
    </main>
</template>

<script setup>

    import { ref } from 'vue';

    const selectedBrand = ref(null);
    const selectedCategory = ref(null);

    const handleBrandSelected = (brandId) => {
        selectedBrand.value = brandId;
    };    

    const handleCategorySelected = (categoryId) => {
        selectedCategory.value = categoryId;
    };
    
</script>