<template>

    <main class="container tw-pt-16 lg:tw-pt-14">
        
        <section class="row">

            <article class="col-12 col-lg-3">

                <UserProfileImageComponent />

            </article>        

            <article class="col-12 col-lg-9">

                <p class="fs-3 fw-bold">{{ user.nombre }} {{ user.apellidos }}</p>
                <p class="fs-5">{{ user.email }}</p>

            </article>
        
        </section>

        <section class="row">
            
            <article class="col-12">


                
            </article>

        </section>        

    </main>
    
</template>

<script setup>

    import { ref } from 'vue';

    const user = ref([]);

    user.value = {
        id: 1,
        nombre: 'Juan',
        apellidos: 'Perez',
        email: 'mondongo@sohotmail.com',
        password: 'aaaaaa',
        direccion: 'c/ de la piruleta, 69',
        telefono: '888888888',
        fechaRegistro: '12/12/2021',
        listaPedidos: [
            {
                id: 1,
                fecha: '12/12/2021',
                total: 100,
                productos: [
                    {
                        id: 1,
                        nombre: 'Producto 1',
                        precio: 10,
                        cantidad: 2,
                    },
                    {
                        id: 2,
                        nombre: 'Producto 2',
                        precio: 20,
                        cantidad: 3,
                    },
                ],
            },
            {
                id: 2,
                fecha: '13/12/2021',
                total: 200,
                productos: [
                    {
                        id: 3,
                        nombre: 'Producto 3',
                        precio: 30,
                        cantidad: 4,
                    },
                    {
                        id: 4,
                        nombre: 'Producto 4',
                        precio: 40,
                        cantidad: 5,
                    },
                ],
            },
        ],
    };

</script>