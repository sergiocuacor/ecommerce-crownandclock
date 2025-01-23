<template>

    <section
        class="d-flex flex-column align-items-center justify-content-center h-100">       
        <div class="p-4 rounded tw-bg-gray-200 border border-2 tw-border-gray-300 tw-space-y-2.5">
            <div class="text-center">
                {{ 'Login' }}
            </div>
            <div class="">
                <label for="inputEmail" class="form-label">{{ 'Email address' }}</label>
                <input type="email" class="form-control" id="inputEmail" placeholder="name@example.com">
            </div>
            <div class="">
                <label for="inputPassword" class="form-label">{{ 'Password' }}</label>
                <input type="password" id="inputPassword" name="inputPassword" class="form-control">
                <div id="passwordHelpBlock" class="form-text">
                    {{ 'Your password must be 8-20 characters long, contain letters and numbers, and must not contain spaces, special characters, or emoji.' }}
                </div>
            </div>
        </div>
    </section>

</template>

<script setup>


</script>