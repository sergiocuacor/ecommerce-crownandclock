<template>
    <main>
        <div class="tw-relative tw-h-screen tw-overflow-hidden">
            <video class="tw-object-cover tw-w-full tw-h-full" src="../../video1.mp4" autoplay loop>

            </video>
            <div class="tw-absolute tw-top-1/2 tw-left-1/2 tw-transform tw--translate-x-1/2 tw--translate-y-1/2">
                <p class="tw-font-semibold tw-subpixel-antialiased tw-font-mono tw-text-8xl hover:tw-text-9xl tw-tracking-widest tw-text-white">{{ 'MARCAS' }}</p>
            </div>
        </div>

        <BrandsComponent />
    </main>
</template>