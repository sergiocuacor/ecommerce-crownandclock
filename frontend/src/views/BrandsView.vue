<template>

    <VideoBackgroundComponent :videoSrc="'/video1.mp4'">
        <template #main>
            <p class="tw-font-semibold tw-subpixel-antialiased tw-font-mono tw-text-8xl tw-tracking-widest tw-text-white">{{ 'BRANDS' }}</p>
        </template>
    </VideoBackgroundComponent>

    <main class="container py-3">
        <BrandsComponent/>
    </main>
    
</template>