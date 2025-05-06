<template>

    <main class="container tw-pt-14 sm:tw-pt-20 sm:tw-pb-6">
        <section class="tw-bg-white tw-p-8 tw-rounded-2xl tw-shadow-lg tw-max-w-4xl tw-mx-auto">
            <h2 class="tw-text-3xl tw-font-bold tw-text-center tw-mb-8">
                {{ 'Frequently Asked Questions' }}
            </h2>
            <div v-for="(faq, index) in faqs" :key="index" class="tw-mb-6">
                <h3 class="tw-text-xl tw-font-semibold tw-text-gray-800 tw-mb-2">
                    {{ faq.question }}
                </h3>
                <p class="tw-text-gray-600 tw-leading-relaxed">
                    {{ faq.answer }}
                </p>
            </div>
        </section>
    </main>

</template>

<script setup>

    const faqs = [
        {
            question: "What types of watches do you sell?",
            answer:
                "We offer a wide variety of watches including luxury, sport, classic, automatic, quartz, and smartwatches from top global brands.",
        },
        {
            question: "Are your watches authentic?",
            answer:
                "Yes, all our watches are 100% authentic and come with a manufacturer’s warranty and original packaging.",
        },
        {
            question: "Do you offer international shipping?",
            answer:
                "Yes, we ship worldwide. Shipping fees and delivery times vary depending on your location.",
        },
        {
            question: "What payment methods do you accept?",
            answer:
                "We accept major credit cards, PayPal, Apple Pay, Google Pay, and bank transfers.",
        },
        {
            question: "Can I return or exchange a watch?",
            answer:
                "Yes, you can return or exchange a watch within 14 days of delivery as long as it is unused, in original condition, and with all packaging.",
        },
        {
            question: "Do your watches come with a warranty?",
            answer:
                "Absolutely. All watches come with a minimum 1-year manufacturer’s warranty. Some brands offer extended warranties.",
        },
        {
            question: "How can I track my order?",
            answer:
                "Once your order is shipped, you will receive an email with a tracking number and instructions to follow your package.",
        },
        {
            question: "Do you offer watch repairs or maintenance?",
            answer:
                "Yes, we offer repair and maintenance services for most watch brands. Please contact our support team for details.",
        },
        {
            question: "Can I get help choosing a watch?",
            answer:
                "Of course! Our customer service team is happy to help you find the perfect watch based on your style, needs, and budget.",
        },
        {
            question: "How can I contact customer service?",
            answer:
                "You can reach us by email at support@yourstore.com, through our contact form, or via live chat during business hours.",
        },
    ];

</script>
