<template>

    <article class="border border-3 rounded">

        <div class="position-relative bg-white">

            <!-- Image with link to element -->
            <router-link v-if="toRoute" :to="toRoute" class="ratio ratio-1x1" @mouseover="isHovered = true; console.log(isHovered)" @mouseleave="isHovered = false; console.log(isHovered)">
                <img v-if="imgSrcFront" class="object-fit-contain p-2" :src="imgSrcFront" :alt="imgAlt"/>
                <img v-if="imgSrcFront && imgSrcBack" class="object-fit-contain p-2" :src="isHovered ? imgSrcBack : imgSrcFront" :alt="imgAlt"/>
                <!-- <img class="object-fit-contain p-2" :src="isHovered ? '/watch_back.avif' : '/watch_front.avif'" :alt="imgAlt"/> -->
            </router-link>
            <div v-else class="ratio ratio-1x1">
                
            </div>

            <!-- Top-left position -->
            <div v-if="hasSlotContent('top-left')" class="position-absolute top-0 start-0">
                <slot name="top-left"></slot>
            </div>

            <!-- Top-middle position -->
            <div v-if="hasSlotContent('top-middle')" class="position-absolute top-0 start-50 translate-middle-x">
                <slot name="top-middle"></slot>
            </div>

            <!-- Top-right position -->
            <div v-if="hasSlotContent('top-right')" class="position-absolute top-0 end-0">
                <slot name="top-right"></slot>
            </div>

            <!-- Middle-left position -->
            <div v-if="hasSlotContent('middle-left')" class="position-absolute top-50 start-0 translate-middle-y">
                <slot name="middle-left"></slot>
            </div>

            <!-- Middle-center position -->
            <div v-if="hasSlotContent('middle-center')" class="position-absolute top-50 start-50 translate-middle">
                <slot name="middle-center"></slot>
            </div>

            <!-- Middle-right position -->
            <div v-if="hasSlotContent('middle-right')" class="position-absolute top-50 end-0 translate-middle-y">
                <slot name="middle-right"></slot>
            </div>

            <!-- Bottom-left position -->
            <div v-if="hasSlotContent('bottom-left')" class="position-absolute bottom-0 start-0">
                <slot name="bottom-left"></slot>
            </div>

            <!-- Bottom-middle position -->
            <div v-if="hasSlotContent('bottom-middle')" class="position-absolute bottom-0 start-50 translate-middle-x">
                <slot name="bottom-middle"></slot>
            </div>

            <!-- Bottom-right position -->
            <div v-if="hasSlotContent('bottom-right')" class="position-absolute bottom-0 end-0">
                <slot name="bottom-right"></slot>
            </div>            

        </div>

        <!-- Footer Section -->
        <div v-if="hasSlotContent('footer')" class="d-block bg-primary-subtle">
            <div class="row p-2">
                <slot name="footer"></slot>
            </div>
        </div>

    </article>

</template>

<script setup>

    import { ref } from 'vue';

    let isHovered = ref(false);

    defineProps({
        toRoute: {
            type: Object,
            required: false,
        },
        imgSrcFront: {
            type: String,
            default: '',
            required: false,
        },
        imgSrcBack: {
            type: String,
            default: '',
            required: false,
        },
        imgAlt: {
            type: String,
            default: '',
            required: false,
        },
    });

    const slots = defineSlots();

    const hasSlotContent = (slotName) => {

        return !!slots[slotName] && slots[slotName]().length > 0;

    };

</script>