<template>
    <main :style="{ height: dynamicHeight }" class="overflow-auto p-2">
        <div class="d-flex flex-column align-items-center justify-content-center h-100">
            <slot name="header"></slot>
            <slot name="footer"></slot>
        </div>
    </main>
</template>

<script>
    import { nextTick } from 'vue';

    export default {
        data() {
            return {
                dynamicHeight: '100vh',
            };
        },
        mounted() {

            this.adjustHeight();
            window.addEventListener('resize', this.adjustHeight);

        },
        beforeDestroy() {
            
            window.removeEventListener('resize', this.adjustHeight);

        },
        methods: {
            adjustHeight() {
                nextTick(() => {
                    const footer = document.querySelector('footer');

                    if (footer) {
                        const footerHeight = footer.offsetHeight;

                        if (window.innerWidth >= 992) {

                            const availableHeight = window.innerHeight - footerHeight;
                            this.dynamicHeight = `${availableHeight}px`;

                        } else {

                            this.dynamicHeight = `100vh`;

                        }
                    
                    }
                });
            }
        }
    };
</script>