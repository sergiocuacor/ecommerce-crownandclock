<template>

    <main>
        <VideoBackgroundComponent :videoSrc="'/video1.mp4'">
            <template #main>
                <RegisterFormComponent />
            </template>
        </VideoBackgroundComponent>
    </main>

</template>