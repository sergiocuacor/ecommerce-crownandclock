<template>

    <div class="ui-container" :title="`Pay`">
        <div class="ui-left-side">
            <div class="ui-card">
            <div class="ui-card-line"></div>
            <div class="ui-buttons"></div>
            </div>
            <div class="ui-post">
            <div class="ui-post-line"></div>
            <div class="ui-screen">
                <div class="ui-dollar">{{ '€' }}</div>
            </div>
            <div class="ui-numbers"></div>
            <div class="ui-numbers-line2"></div>
            </div>
        </div>
        <div class="ui-right-side">
            <div class="ui-new">
                {{ 'Pay' }}
            </div>
        </div>
    </div>

</template>

<style scoped>

    .ui-container {
        background-color: #ffffff;
        display: flex;
        width: 100%;
        height: 120px;
        position: relative;
        border-radius: 6px;
        transition: 0.3s ease-in-out;
    }

    .ui-container:hover {
        transform: scale(1.03);
    }

    .ui-container:hover .ui-left-side {
        width: 100%;
    }

    .ui-left-side {
        background-color: #5de2a3;
        width: 130px;
        height: 120px;
        border-radius: 4px;
        position: relative;
        display: flex;
        justify-content: center;
        align-items: center;
        cursor: pointer;
        transition: 0.3s;
        flex-shrink: 0;
        overflow: hidden;
    }

    .ui-right-side {
        width: 100%;
        display: flex;
        align-items: center;
        overflow: hidden;
        cursor: pointer;
        justify-content: space-between;
        white-space: nowrap;
        transition: 0.3s;
    }

    .ui-right-side:hover {
        background-color: #f9f7f9;
    }

    .ui-arrow {
        width: 20px;
        height: 20px;
        margin-right: 20px;
    }

    .ui-new {
        width: 100%;
        font-size: 23px;
        font-family: "Lexend Deca", sans-serif;
        text-align: center;
    }

    .ui-card {
        width: 70px;
        height: 46px;
        background-color: #c7ffbc;
        border-radius: 6px;
        position: absolute;
        display: flex;
        z-index: 10;
        flex-direction: column;
        align-items: center;
        box-shadow: 9px 9px 9px -2px rgba(77, 200, 143, 0.72);
        -webkit-box-shadow: 9px 9px 9px -2px rgba(77, 200, 143, 0.72);
        -moz-box-shadow: 9px 9px 9px -2px rgba(77, 200, 143, 0.72);
    }

    .ui-card-line {
        width: 64px;
        height: 13px;
        background-color: #80ea69;
        border-radius: 2px;
        margin-top: 7px;
    }

    @media only screen and (max-width: 480px) {
        .ui-container {
            transform: scale(0.7);
        }

        .ui-container:hover {
            transform: scale(0.74);
        }

        .ui-new {
            font-size: 18px;
        }
    }

    .ui-buttons {
        width: 8px;
        height: 8px;
        background-color: #379e1f;
        box-shadow: 0 -10px 0 0 #26850e, 0 10px 0 0 #56be3e;
        border-radius: 50%;
        transform: rotate(90deg);
        margin: 10px 0 0 -30px;
    }

    .ui-container:hover .ui-card {
        animation: ui-slide-top 1.2s cubic-bezier(0.645, 0.045, 0.355, 1) both;
    }

    .ui-container:hover .ui-post {
        animation: ui-slide-post 1s cubic-bezier(0.165, 0.84, 0.44, 1) both;
    }

    @keyframes ui-slide-top {
        0% {
            -webkit-transform: translateY(0);
            transform: translateY(0);
        }

        50% {
            -webkit-transform: translateY(-70px) rotate(90deg);
            transform: translateY(-70px) rotate(90deg);
        }

        60% {
            -webkit-transform: translateY(-70px) rotate(90deg);
            transform: translateY(-70px) rotate(90deg);
        }

        100% {
            -webkit-transform: translateY(-8px) rotate(90deg);
            transform: translateY(-8px) rotate(90deg);
        }
    }

    .ui-post {
        width: 63px;
        height: 75px;
        background-color: #dddde0;
        position: absolute;
        z-index: 11;
        bottom: 10px;
        top: 120px;
        border-radius: 6px;
        overflow: hidden;
    }

    .ui-post-line {
        width: 47px;
        height: 9px;
        background-color: #545354;
        position: absolute;
        border-radius: 0px 0px 3px 3px;
        right: 8px;
        top: 8px;
    }

    .ui-post-line:before {
        content: "";
        position: absolute;
        width: 47px;
        height: 9px;
        background-color: #757375;
        top: -8px;
    }

    .ui-screen {
        width: 47px;
        height: 23px;
        background-color: #ffffff;
        position: absolute;
        top: 22px;
        right: 8px;
        border-radius: 3px;
    }

    .ui-numbers {
        width: 12px;
        height: 12px;
        background-color: #838183;
        box-shadow: 0 -18px 0 0 #838183, 0 18px 0 0 #838183;
        border-radius: 2px;
        position: absolute;
        transform: rotate(90deg);
        left: 25px;
        top: 52px;
    }

    .ui-numbers-line2 {
        width: 12px;
        height: 12px;
        background-color: #aaa9ab;
        box-shadow: 0 -18px 0 0 #aaa9ab, 0 18px 0 0 #aaa9ab;
        border-radius: 2px;
        position: absolute;
        transform: rotate(90deg);
        left: 25px;
        top: 68px;
    }

    @keyframes ui-slide-post {
        
        50% {
            -webkit-transform: translateY(0);
            transform: translateY(0);
        }

        100% {
            -webkit-transform: translateY(-70px);
            transform: translateY(-70px);
        }
    }

    .ui-dollar {
        position: absolute;
        font-size: 16px;
        font-family: "Lexend Deca", sans-serif;
        width: 100%;
        left: 0;
        top: 0;
        color: #4b953b;
        text-align: center;
    }

    .ui-container:hover .ui-dollar {
        animation: ui-fade-in-fwd 0.3s 1s backwards;
    }

    @keyframes ui-fade-in-fwd {
        0% {
            opacity: 0;
            transform: translateY(-5px);
        }

        100% {
            opacity: 1;
            transform: translateY(0);
        }
    }

</style>
