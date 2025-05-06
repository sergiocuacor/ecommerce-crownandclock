<template>

    <div class="ui-container">
        <div class="ui-folder">
            <div class="ui-front-side">
            <div class="ui-tip"></div>
            <div class="ui-cover"></div>
            </div>
            <div class="ui-back-side ui-cover"></div>
        </div>
        <label class="ui-custom-file-upload">
            <input class="ui-title" type="file" />
            {{ props.text }}
        </label>
    </div>

</template>

<script setup>

    const props = defineProps({
        text: {
            required: true
        },
    });

</script>

<style scoped>

    .ui-container {
        --transition: 350ms;
        --folder-W: 120px;
        --folder-H: 80px;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-end;
        padding: 10px;
        background: linear-gradient(135deg, #2e2e2e, #131313);
        border-radius: 15px;
        box-shadow: 0 15px 30px rgba(0, 0, 0, 0.2);
        height: calc(var(--folder-H) * 1.7);
        position: relative;
    }

    .ui-folder {
        position: absolute;
        top: -20px;
        left: calc(50% - 60px);
        animation: ui-float 2.5s infinite ease-in-out;
        transition: transform var(--transition) ease;
    }

    .ui-folder:hover {
        transform: scale(1.05);
    }

    .ui-folder .ui-front-side,
    .ui-folder .ui-back-side {
        position: absolute;
        transition: transform var(--transition);
        transform-origin: bottom center;
    }

    .ui-folder .ui-back-side::before,
    .ui-folder .ui-back-side::after {
        content: "";
        display: block;
        background-color: white;
        opacity: 0.5;
        z-index: 0;
        width: var(--folder-W);
        height: var(--folder-H);
        position: absolute;
        transform-origin: bottom center;
        border-radius: 15px;
        transition: transform 350ms;
        z-index: 0;
    }

    .ui-container:hover .ui-back-side::before {
        transform: rotateX(-5deg) skewX(5deg);
    }
    .ui-container:hover .ui-back-side::after {
        transform: rotateX(-15deg) skewX(12deg);
    }

    .ui-folder .ui-front-side {
        z-index: 1;
    }

    .ui-container:hover .ui-front-side {
        transform: rotateX(-40deg) skewX(15deg);
    }

    .ui-folder .ui-tip {
        background: linear-gradient(135deg, #ff9a56, #ff6f56);
        width: 80px;
        height: 20px;
        border-radius: 12px 12px 0 0;
        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        position: absolute;
        top: -10px;
        z-index: 2;
    }

    .ui-folder .ui-cover {
        background: linear-gradient(135deg, #ffe563, #ffc663);
        width: var(--folder-W);
        height: var(--folder-H);
        box-shadow: 0 15px 30px rgba(0, 0, 0, 0.3);
        border-radius: 10px;
    }

    .ui-custom-file-upload {
        font-size: 1.1em;
        color: #ffffff;
        text-align: center;
        background: rgba(255, 255, 255, 0.2);
        border: none;
        border-radius: 10px;
        box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
        cursor: pointer;
        transition: background var(--transition) ease;
        display: inline-block;
        width: 100%;
        padding: 10px 35px;
        position: relative;
    }

    .ui-custom-file-upload:hover {
        background: rgba(255, 255, 255, 0.4);
    }

    .ui-custom-file-upload input[type="file"] {
        display: none;
    }

    @keyframes ui-float {
        0% {
            transform: translateY(0px);
        }

        50% {
            transform: translateY(-20px);
        }

        100% {
            transform: translateY(0px);
        }
    }
</style>