<template>

    <div id="loaderWrapper">
        <slot></slot>
    </div>

</template>

<style scoped>

    #loaderWrapper {
        z-index: 500;
        position: fixed;
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100vw;
        height: 100vh;
        background-color: rgba(0, 0, 0, 0.6);
    }

</style>