<template>

    <DynamicHeightDivComponent>
        <template #header>
            <div class="fs-3 fw-semibold text-center">
                {{ mask === null ? 'Socials' : label }}
            </div>
        </template>
        <template #footer>
            <div v-if="mask === null" class="d-flex justify-content-center gap-4 fs-1 fw-semibold'">
                <router-link :to="{ name: 'twitter' }" class="link-body-emphasis">
                    <TwitterXIcon />
                </router-link>
                <router-link :to="{ name: 'instagram' }" class="link-body-emphasis">
                    <InstagramIcon />
                </router-link>
                <router-link :to="{ name: 'facebook' }" class="link-body-emphasis">
                    <FacebookIcon />
                </router-link>
            </div>
            <div v-else class="d-flex justify-content-center gap-4 fs-1 fw-semibold">
                <a :href="url" class="link-body-emphasis">
                    <TwitterXIcon v-if="mask === 'twitter'" />
                    <InstagramIcon v-if="mask === 'instagram'" />
                    <FacebookIcon v-if="mask === 'facebook'" />
                </a>
            </div>
        </template>
    </DynamicHeightDivComponent>
    
</template>

<script setup>

    import { computed } from 'vue';
    import { useRoute } from 'vue-router';

    const route = useRoute();
    const mask = computed(() => route.meta.mask || null);
    const url = computed(() => route.meta.url || "");
    const label = computed(() => route.meta.label || "");

</script>