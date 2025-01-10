<template>
    <DynamicHeightDivComponent v-if="!social">
        <template #header>
            <div class="fs-1 fw-semibold text-center">
                Socials
            </div>
        </template>
        <template #footer>
            <div class="d-flex justify-content-center gap-4">
                <router-link :to="{ name: 'twitter' }" class="link-body-emphasis">
                    <i class="bi bi-twitter-x fs-1 fw-semibold"></i>
                </router-link>
                <router-link :to="{ name: 'instagram' }" class="link-body-emphasis">
                    <i class="bi bi-instagram fs-1 fw-semibold"></i>
                </router-link>
                <router-link :to="{ name: 'facebook' }" class="link-body-emphasis">
                    <i class="bi bi-facebook fs-1 fw-semibold"></i>
                </router-link>
            </div>
        </template>
    </DynamicHeightDivComponent>

    <DynamicHeightDivComponent v-if="social === 'twitter'">
        <template #header>
            <div class="fs-1 fw-semibold text-center">
                
            </div>
        </template>
        <template #footer>
            <div class="d-flex justify-content-center gap-4">
                <router-link :to="{ name: 'twitter' }" class="link-body-emphasis">
                    <i class="bi bi-twitter-x fs-1 fw-semibold"></i>
                </router-link>
            </div>
        </template>
    </DynamicHeightDivComponent>

    <DynamicHeightDivComponent v-if="social === 'instagram'">
        <template #header>
            <div class="fs-1 fw-semibold text-center">

            </div>
        </template>
        <template #footer>
            <div class="d-flex justify-content-center gap-4">
                <router-link :to="{ name: 'instagram' }" class="link-body-emphasis">
                    <i class="bi bi-instagram fs-1 fw-semibold"></i>
                </router-link>
            </div>
        </template>
    </DynamicHeightDivComponent>

    <DynamicHeightDivComponent v-if="social === 'facebook'">
        <template #header>
            <div class="fs-1 fw-semibold text-center">

            </div>
        </template>
        <template #footer>
            <div class="d-flex justify-content-center gap-4">
                <router-link :to="{ name: 'facebook' }" class="link-body-emphasis">
                    <i class="bi bi-facebook fs-1 fw-semibold"></i>
                </router-link>
            </div>
        </template>
    </DynamicHeightDivComponent>
</template>

<script setup>

    defineProps({
        social: {
            type: String,
            required: false
        }
    });
    
</script>