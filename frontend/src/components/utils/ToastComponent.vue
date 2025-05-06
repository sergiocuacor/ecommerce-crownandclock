<template>

    <div ref="toastEl" :class="[props.textColor, props.bgColor, 'tw-border-2 tw-border-gray-400']" class="toast align-items-center" role="alert"
        aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                {{ props.message }}
            </div>
            <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"
                aria-label="Close"></button>
        </div>
    </div>

</template>

<script setup>

    import { onMounted, ref } from 'vue';
    import { Toast } from 'bootstrap';
    
    const toastEl = ref(null);
    const emit = defineEmits(['hidden']);

    const props = defineProps({
        message:{
            type: String,
            required: true
        },
        autohide: {
            type: Boolean,
            default: true
        },
        delay: {
            type: Number,
            default: 5000
        },
        textColor:{
            type: String,
            default: 'tw-text-black'
        },
        bgColor:{
            type: String,
            default: 'tw-bg-white'
        },
    });

    onMounted(() => {
        const el = toastEl.value;
        if (!el) return;
        const toast = new Toast(el, {
            autohide: props.autohide,
            delay: props.delay
        });
        el.addEventListener('hidden.bs.toast', () => {
            emit('hidden');
        });
        toast.show();
    })

</script>