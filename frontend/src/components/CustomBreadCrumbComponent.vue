<template>
  <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
          <li v-for="(crumb, index) in breadcrumbs" :key="index" class="breadcrumb-item"
              :class="{ active: index === breadcrumbs.length - 1 }"
              :aria-current="index === breadcrumbs.length - 1 ? 'page' : null">
              <router-link v-if="index !== breadcrumbs.length - 1" :to="crumb.path">
                  {{ crumb.label }}
              </router-link>
              <span v-else>{{ crumb.label }}</span>
          </li>
      </ol>
  </nav>
</template>

<script>
  import { computed } from 'vue';
  import { useRoute } from 'vue-router';

  export default {
      setup() {
          const route = useRoute();

          const breadcrumbs = computed(() => route.meta.breadcrumbs || []);

          return { breadcrumbs };
      },
  };
</script>