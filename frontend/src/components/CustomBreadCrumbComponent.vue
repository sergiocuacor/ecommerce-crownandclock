<template>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb breadcrumb-chevron p-3 bg-body-tertiary rounded-3">
            <li v-for="(crumb, index) in breadcrumbs" :key="index" class="breadcrumb-item"
                :class="{ active: index === breadcrumbs.length - 1 }"
                :aria-current="index === breadcrumbs.length - 1 ? 'page' : null">
                <router-link v-if="index !== breadcrumbs.length - 1" :to="crumb.path" class="link-body-emphasis fw-semibold text-decoration-none">
                    {{ crumb.label }}
                </router-link>
                <span v-else>{{ crumb.label }}</span>
            </li>
        </ol>
    </nav>
</template>

<script setup>

    import { computed } from 'vue';
    import { useRoute } from 'vue-router';

    const route = useRoute();
    const breadcrumbs = computed(() => route.meta.breadcrumbs || []);
    
</script>