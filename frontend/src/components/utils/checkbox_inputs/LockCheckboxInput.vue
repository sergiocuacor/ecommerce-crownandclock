<template>

    <label class="tw-relative tw-inline-flex tw-items-center tw-cursor-pointer" v-if="props.isChecked != null">
        <input v-model="radioInput" @change="emitChange" class="tw-peer tw-sr-only" type="checkbox"/>
        <div
            class="tw-border-gray-500 tw-shadow-sm peer-checked:tw-shadow-green-600 tw-shadow-red-600 tw-border tw-flex tw-h-6 tw-w-12 tw-items-center tw-outline-none tw-rounded tw-bg-red-600 tw-pl-7 tw-text-white tw-transition-all tw-duration-300 peer-checked:tw-bg-green-600 peer-checked:tw-pl-2 peer-focus:tw-outline-none"
        ></div>

        <i class="bi bi-ban peer-checked:tw-opacity-0 tw-transition-all tw-duration-500 tw-opacity-100 tw-absolute tw-left-6 tw-stroke-gray-900 px-1"></i>
        <i class="bi bi-check-lg tw-absolute tw-transition-all tw-duration-500 peer-checked:tw-opacity-100 tw-opacity-0 tw-left-1 tw-stroke-gray-900"
        ></i>
        <div
            class="tw-absolute tw-left-1 tw-top-1 tw-flex tw-h-3.5 tw-w-4 tw-items-center tw-justify-center tw-rounded-sm tw-bg-white tw-shadow-lg tw-transition-all tw-duration-300 peer-checked:tw-left-7">
        </div>
    </label>

</template>

<script setup>

    import { defineEmits, defineProps, ref } from 'vue';

    const emit = defineEmits(['inputChanged']);
    const props = defineProps({
        target: {
            required: true
        },
        isChecked: {
            type: Boolean,
            required: true,
        },
    });
    const radioInput = ref(props.isChecked)

    const emitChange = () => {
        emit('inputChanged', props.target, { enabled: radioInput.value });
    }

</script>