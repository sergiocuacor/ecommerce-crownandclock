import { defineStore } from "pinia";
import axios from 'axios';

export const useAuthStore = defineStore("auth", {

    state: () => ({

        token: localStorage.getItem("token") || null,

    }),

    actions: {

        logInUser(token) {

            localStorage.setItem("token", token);
            this.token = token;
            this.establishTokenInAxios();

        },

        logOutUser() {

            localStorage.removeItem('token');
            this.token = null;
            this.establishTokenInAxios();
            
        },

        establishTokenInAxios() {

            if (this.token != null) {

                axios.defaults.headers.common['Authorization'] = 'Bearer ' + this.token;

            } else {

                delete axios.defaults.headers.common['Authorization'];

            }

        },

    },

});