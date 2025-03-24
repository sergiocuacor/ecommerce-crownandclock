<template>
    <div class="ui-book">
        <div class="ratio ratio-1x1">
            <img class="object-fit-contain p-1" src="/public/watch_back.avif" alt="back-image" />
        </div>
        <div class="ui-cover">
            <div class="ratio ratio-1x1">
                <img class="object-fit-contain p-1" src="/public/watch_front.avif" alt="front-image" />
            </div>
        </div>
       </div>
</template>

<style scoped>

  .ui-book {
    position: relative;
    border-radius: 10px;
    width: 100%;
    height: auto;
    aspect-ratio: 1/1;
    background-color: whitesmoke;
    -webkit-box-shadow: 1px 1px 12px #000;
    box-shadow: 1px 1px 12px #000;
    -webkit-transform: preserve-3d;
    -ms-transform: preserve-3d;
    transform: preserve-3d;
    -webkit-perspective: 2000px;
    perspective: 2000px;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    -webkit-box-pack: center;
    -ms-flex-pack: center;
    justify-content: center;
    color: #000;
  }

  .ui-cover {
    top: 0;
    position: absolute;
    background-color: lightgray;
    width: 100%;
    height: 100%;
    border-radius: 10px;
    cursor: pointer;
    -webkit-transition: all 0.5s;
    transition: all 0.5s;
    -webkit-transform-origin: 0;
    -ms-transform-origin: 0;
    transform-origin: 0;
    -webkit-box-shadow: 1px 1px 6px #000;
    box-shadow: 1px 1px 6px #000;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    -webkit-box-pack: center;
    -ms-flex-pack: center;
    justify-content: center;
  }

  .ui-book:hover .ui-cover {
    -webkit-transition: all 0.5s;
    transition: all 0.5s;
    -webkit-transform: rotatey(-80deg);
    -ms-transform: rotatey(-80deg);
    transform: rotatey(-80deg);
  }

</style>