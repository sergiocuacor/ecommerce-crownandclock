<template>
    <DynamicHeightDivComponent v-if="mask === null">
        <template #header>
            <div class="fs-1 fw-semibold text-center">
                Socials
            </div>
        </template>
        <template #footer>
            <div class="d-flex justify-content-center gap-4">
                <router-link :to="{ name: 'twitter' }" class="link-body-emphasis">
                    <TwitterXIcon :class="'fs-1 fw-semibold'"/>
                </router-link>
                <router-link :to="{ name: 'instagram' }" class="link-body-emphasis">
                    <InstagramIcon :class="'fs-1 fw-semibold'"/>
                </router-link>
                <router-link :to="{ name: 'facebook' }" class="link-body-emphasis">
                    <FacebookIcon :class="'fs-1 fw-semibold'"/>
                </router-link>
            </div>
        </template>
    </DynamicHeightDivComponent>

    <DynamicHeightDivComponent v-else>
        <template #header>
            <div class="fs-1 fw-semibold text-center">
                {{ label }}
            </div>
        </template>
        <template #footer>
            <div class="d-flex justify-content-center gap-4">
                <router-link :to="{ name: mask }" class="link-body-emphasis">
                    <TwitterXIcon v-if="mask === 'twitter'" :class="'fs-1 fw-semibold'"/>
                    <InstagramIcon v-if="mask === 'instagram'" :class="'fs-1 fw-semibold'"/>
                    <FacebookIcon v-if="mask === 'facebook'" :class="'fs-1 fw-semibold'"/>
                </router-link>
            </div>
        </template>
    </DynamicHeightDivComponent>
</template>

<script setup>

    import { computed } from 'vue';
    import { useRoute } from 'vue-router';

    const route = useRoute();
    const mask = computed(() => route.meta.mask || null);
    const label = computed(() => route.meta.label || "");

</script>