<template>

    <div class="ui-card">
      <div class="ui-loader">
        <p>{{ props.mainWords }}</p>
        <div class="ui-words">
          <span v-for="word, index in props.secondaryWords" :key="index" class="ui-word">{{ word }}</span>
        </div>
      </div>
    </div>

</template>

<script setup>

  const props = defineProps({
    mainWords: {
      type: String,
      default: 'loading',
      required: false,
    },
    secondaryWords: {
      type: Array,
      default: ['buttons', 'forms', 'switches', 'cards'],
    },
  });

</script>

<style scoped>

  .ui-card {
    /* color used to softly clip top and bottom of the .words container */
    --ui-bg-color: transparent;
    background-color: var(--ui-bg-color);
    padding: 1rem;
    border-radius: 1.25rem;
  }

  .ui-loader {
    color: rgb(124, 124, 124);
    font-family: "Poppins", sans-serif;
    font-weight: 500;
    font-size: 25px;
    -webkit-box-sizing: content-box;
    box-sizing: content-box;
    height: 40px;
    padding: 10px 10px;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    border-radius: 8px;
  }

  .ui-words {
    overflow: hidden;
    position: relative;
  }
  
  .ui-words::after {
    content: "";
    position: absolute;
    inset: 0;
    background: linear-gradient(
      var(--ui-bg-color) 10%,
      transparent 30%,
      transparent 70%,
      var(--ui-bg-color) 90%
    );
    z-index: 20;
  }

  .ui-word {
    display: block;
    height: 100%;
    padding-left: 6px;
    color: rgb(64, 64, 255);
    animation: ui-spin_4991 4s infinite;
  }

  @keyframes ui-spin_4991 {
    10% {
      -webkit-transform: translateY(-102%);
      transform: translateY(-102%);
    }

    25% {
      -webkit-transform: translateY(-100%);
      transform: translateY(-100%);
    }

    35% {
      -webkit-transform: translateY(-202%);
      transform: translateY(-202%);
    }

    50% {
      -webkit-transform: translateY(-200%);
      transform: translateY(-200%);
    }

    60% {
      -webkit-transform: translateY(-302%);
      transform: translateY(-302%);
    }

    75% {
      -webkit-transform: translateY(-300%);
      transform: translateY(-300%);
    }

    85% {
      -webkit-transform: translateY(-402%);
      transform: translateY(-402%);
    }

    100% {
      -webkit-transform: translateY(-400%);
      transform: translateY(-400%);
    }
  }
</style>