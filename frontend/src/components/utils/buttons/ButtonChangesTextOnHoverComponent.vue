<template>

    <button
        :class="props.background, props.shadow"
        class="tw-cursor-pointer tw-px-6 tw-py-3 tw-rounded-xl tw-border-[1px] tw-border-slate-500 tw-text-white tw-font-medium tw-group"
    >
        <div class="tw-relative tw-overflow-hidden">            
            <p class="group-hover:tw--translate-y-7 tw-duration-[1.125s] tw-ease-[cubic-bezier(0.19,1,0.22,1)]">
                <i v-if="props.icon1" class="tw-me-1 bi" :class="props.icon1"></i>{{ props.text1 }}
            </p>
            <p class="tw-absolute tw-top-7 tw-left-0 group-hover:tw-top-0 tw-duration-[1.125s] tw-ease-[cubic-bezier(0.19,1,0.22,1)] tw-text-center">
                <i v-if="props.icon2" class="tw-me-1 bi" :class="props.icon2"></i>{{ props.text2 }}
            </p>
        </div>
    </button>

</template>

<script setup>

    const props = defineProps({
        background: {
            type: String,
            default: 'tw-bg-gradient-to-b tw-from-indigo-500 tw-to-indigo-600 hover:tw-from-red-500 hover:tw-to-red-600'
        },
        shadow: {
            type: String,
            default: 'tw-shadow-[0px_4px_32px_0_rgba(99,102,241,.70)]'
        },
        text1: {
            type: String,
            default: ''
        },
        icon1: {
            type: String,
        },
        text2: {
            type: String,
            default: ''
        },
        icon2: {
            type: String,
        },
    });

</script>