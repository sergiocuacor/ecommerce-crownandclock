import { defineStore } from "pinia";

export const useAuthStore = defineStore("auth", {

    state: () => ({

        token: localStorage.getItem("token") || null,

    }),

    actions: {

        logInUser(token) {

            localStorage.setItem("token", token);
            this.token = token;

        },

        logOutUser() {

            localStorage.removeItem('token');
            this.token = null;
            
        },

    },

});