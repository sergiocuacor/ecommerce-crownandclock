<template>
    <main>
        <VideoBackgroundComponent :videoSrc="'/video1.mp4'">
            <template #main>
                <p class="tw-font-semibold tw-subpixel-antialiased tw-font-mono tw-text-8xl tw-tracking-widest tw-text-white">{{ 'MARCAS' }}</p>
            </template>
        </VideoBackgroundComponent>

        <BrandsComponent />
    </main>
</template>