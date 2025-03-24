<template>

    <div class="ui-loader">
        <div class="ui-box ui-box-1">
            <div class="ui-side-left"></div>
            <div class="ui-side-right"></div>
            <div class="ui-side-top"></div>
        </div>
        <div class="ui-box ui-box-2">
            <div class="ui-side-left"></div>
            <div class="ui-side-right"></div>
            <div class="ui-side-top"></div>
        </div>
        <div class="ui-box ui-box-3">
            <div class="ui-side-left"></div>
            <div class="ui-side-right"></div>
            <div class="ui-side-top"></div>
        </div>
        <div class="ui-box ui-box-4">
            <div class="ui-side-left"></div>
            <div class="ui-side-right"></div>
            <div class="ui-side-top"></div>
        </div>
    </div>

</template>

<style scoped>

    /* 3D tower loader made by: csozi | Website: www.csozi.hu*/

    .ui-loader {
        scale: 3;
        height: 50px;
        width: 40px;
    }

    .ui-box {
        position: relative;
        opacity: 0;
        left: 10px;
    }

    .ui-side-left {
        position: absolute;
        background-color: #286cb5;
        width: 19px;
        height: 5px;
        transform: skew(0deg, -25deg);
        top: 14px;
        left: 10px;
    }

    .ui-side-right {
        position: absolute;
        background-color: #2f85e0;
        width: 19px;
        height: 5px;
        transform: skew(0deg, 25deg);
        top: 14px;
        left: -9px;
    }

    .ui-side-top {
        position: absolute;
        background-color: #5fa8f5;
        width: 20px;
        height: 20px;
        rotate: 45deg;
        transform: skew(-20deg, -20deg);
    }

    .ui-box-1 {
        animation: from-left 4s infinite;
    }

    .ui-box-2 {
        animation: from-right 4s infinite;
        animation-delay: 1s;
    }

    .ui-box-3 {
        animation: from-left 4s infinite;
        animation-delay: 2s;
    }

    .ui-box-4 {
        animation: from-right 4s infinite;
        animation-delay: 3s;
    }

    @keyframes from-left {
        0% {
            z-index: 20;
            opacity: 0;
            translate: -20px -6px;
        }

        20% {
            z-index: 10;
            opacity: 1;
            translate: 0px 0px;
        }

        40% {
            z-index: 9;
            translate: 0px 4px;
        }

        60% {
            z-index: 8;
            translate: 0px 8px;
        }

        80% {
            z-index: 7;
            opacity: 1;
            translate: 0px 12px;
        }

        100% {
            z-index: 5;
            translate: 0px 30px;
            opacity: 0;
        }
    }

    @keyframes from-right {
        0% {
            z-index: 20;
            opacity: 0;
            translate: 20px -6px;
        }

        20% {
            z-index: 10;
            opacity: 1;
            translate: 0px 0px;
        }

        40% {
            z-index: 9;
            translate: 0px 4px;
        }

        60% {
            z-index: 8;
            translate: 0px 8px;
        }

        80% {
            z-index: 7;
            opacity: 1;
            translate: 0px 12px;
        }

        100% {
            z-index: 5;
            translate: 0px 30px;
            opacity: 0;
        }
    }

</style>