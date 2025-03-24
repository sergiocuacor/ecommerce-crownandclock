<template>

    <div class="ui-cloader">
        <div class="ui-clface">
            <div class="ui-clsface">
                <div id="ui-h2" class="ui-hand"></div>
            </div>
            <div class="ui-top"></div>
            <div class="ui-bottom"></div>
            <div class="ui-left"></div>
            <div class="ui-right"></div>
            <div id="ui-sub" class="ui-pin"></div>
            <div id="ui-h1" class="ui-hand"></div>
            <div id="ui-main" class="ui-pin"></div>
        </div>
    </div>
    
</template>

<style scoped>

    .ui-cloader {
        width: 10em;
        height: 10em;
        border: 4px solid white;
        border-radius: 50%;
        margin: 50px auto;
        position: relative;
        padding: 2rem;
    }

    .ui-clface {
        position: relative;
        width: 100%;
        height: 100%;
        transform: translateY(-3px);
    }

    .ui-top {
        height: 10px;
        width: 4px;
        content: '';
        background-color: white;
        border-radius: 5em;
        margin-top: -30px;
        margin-left: 42px;
    }

    .ui-bottom {
        height: 10px;
        width: 4px;
        content: '';
        background-color: white;
        border-radius: 5em;
        margin-top: 135px;
        margin-left: 42px;
    }

    .ui-left {
        height: 4px;
        width: 10px;
        content: '';
        background-color: white;
        border-radius: 5em;
        margin-top: -82px;
        margin-left: 112px;
    }

    .ui-right {
        height: 4px;
        width: 10px;
        content: '';
        background-color: white;
        border-radius: 5em;
        margin-top: -4px;
        margin-left: -35px;
    }

    .ui-clsface {
        position: absolute;
        width: 40px;
        height: 40px;
        margin-left: 24px;
        margin-top: 15px;
        border-radius: 6em;
        border: 2px solid #636363;
    }

    .ui-hand {
        width: 65%;
        height: 4px;
        background-color: white;
        border-radius: 3em;
        border: none;
        position: absolute;
        top: 85%;
        left: -15%;
        transform-origin: 100%;
        transform: rotate(90deg);
    }

    .ui-pin {
        width: 25%;
        height: 25%;
        border-radius: 50%;
        background: white;
        position: absolute;
        top: 86%;
        left: 50%;
        transform: translate(-50%, -50%);
    }

    #ui-sub {
        width: 10%;
        height: 10%;
        border-radius: 50%;
        background: #636363;
        position: absolute;
        top: 40%;
        left: 50%;
        transform: translate(-50%, -50%);
    }

    #ui-h1 {
        animation: rotate 4s linear infinite;
    }

    #ui-h2 {
        width: 45%;
        left: 5%;
        top: 45%;
        background-color: #636363;
        animation: rotate 1s linear infinite;
    }

    @keyframes rotate {
        from {
            transform: rotate(90deg);
        }

        to {
            transform: rotate(450deg);
        }
    }

</style>