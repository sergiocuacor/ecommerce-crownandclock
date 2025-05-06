<template>

    <main class="container tw-pt-14 sm:tw-pt-20 sm:tw-pb-6">
        <section class="tw-bg-white tw-py-12 tw-px-6 tw-max-w-6xl tw-mx-auto tw-rounded-2xl tw-shadow-lg">
            <h2 class="tw-text-3xl tw-font-bold tw-text-center tw-mb-10">
                {{ `Our Pricing` }}
            </h2>
            <div class="tw-grid tw-grid-cols-1 md:tw-grid-cols-3 tw-gap-8">
                <div
                v-for="(tier, index) in pricingTiers"
                :key="index"
                class="tw-bg-gray-50 tw-rounded-xl tw-p-6 tw-shadow-md tw-text-center"
                >
                <h3 class="tw-text-xl tw-font-semibold tw-text-gray-800 tw-mb-4">{{ tier.title }}</h3>
                <p class="tw-text-3xl tw-font-bold tw-text-black tw-mb-2">
                    {{ tier.priceRange }}
                </p>
                <p class="tw-text-gray-600 tw-mb-4">
                    {{ tier.description }}
                </p>
                <ul class="tw-text-sm tw-text-gray-700 tw-mb-6 tw-text-left tw-space-y-1">
                    <li v-for="(feature, i) in tier.features" :key="i" class="tw-flex tw-items-start">
                    <span class="tw-mr-2 tw-text-green-500">✔</span>
                    <span>{{ feature }}</span>
                    </li>
                </ul>
                <router-link :to="{ name: 'home' }" class="tw-bg-black tw-text-white tw-py-2 tw-px-4 tw-rounded-lg tw-font-medium hover:tw-bg-gray-800">
                    {{ `Explore` }}
                </router-link>
                </div>
            </div>
        </section>
    </main>

</template>

<script setup>

import router from '../router';

    const pricingTiers = [
        {
            title: "Everyday Collection",
            priceRange: "$50 – $499",
            description: "Affordable and stylish watches for daily wear.",
            features: [
                "Quartz & analog models",
                "Durable materials",
                "Great for gifts and casual use",
            ],
        },
        {
            title: "Premium Selection",
            priceRange: "$500 – $4,999",
            description: "Crafted pieces that balance function and luxury.",
            features: [
                "Swiss and Japanese movements",
                "Luxury materials and design",
                "Warranties and brand authenticity",
            ],
        },
        {
            title: "Collector’s Vault",
            priceRange: "$5,000 – $250,000",
            description: "Exquisite timepieces for collectors and connoisseurs.",
            features: [
                "Limited editions & heritage models",
                "Certified authenticity",
                "Concierge support and secure delivery",
            ],
        },
    ];

</script>
