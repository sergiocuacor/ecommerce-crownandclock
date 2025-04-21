import { defineStore } from "pinia";

const fetchTokenValidity = async () => {

  const response = await apiClient.getTokenValidation();

  let isValid = false;

  if (response.success) {

    isValid = response.data.valid;

  }

  return isValid;

};

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

        async tokenValidation() {

            if(this.token != null) {

                const isValid = await fetchTokenValidity();

                if(!isValid) {

                    this.logOutUser();

                }

            }

        }

    },

});