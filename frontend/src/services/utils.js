import { defineStore } from "pinia";

export const useUtilsStore = defineStore("utils", {

    state: () => ({

        offCanvasContent: JSON.parse(localStorage.getItem("offCanvasContent")) || '',

    }),

    actions: {

        setOffCanvasContent(content) {

            this.offCanvasContent = content;
            this.saveOffCanvasState();

        },

        saveOffCanvasState() {

            localStorage.setItem("offCanvasContent", JSON.stringify(this.offCanvasContent));

        },

    },

});
