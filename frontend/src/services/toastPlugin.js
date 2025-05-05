import { h, render } from "vue";
import ToastComponent from "../components/utils/ToastComponent.vue";

export default {
    install(app) {
        const container = document.createElement("div");
        container.className = "toast-container position-fixed bottom-0 end-0 p-3 tw-space-y-2";
        container.style.zIndex = 1100;
        document.body.appendChild(container);
        app.config.globalProperties.$toast = (message, options = {}) => {
        const vnode = h(ToastComponent, {
            message,
            autohide: options.autohide ?? true,
            delay: options.delay ?? 5000,
            textColor: options.textColor ?? 'tw-text-black',
            bgColor: options.bgColor ?? 'tw-bg-white',
            onHidden: () => {
                render(null, el);
                container.removeChild(el);
            },
        });
        const el = document.createElement("div");
            container.appendChild(el);
            render(vnode, el);
        };
    },
};