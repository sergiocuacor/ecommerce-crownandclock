<template>

    <main>
        <VideoBackgroundComponent :videoSrc="'/video1.mp4'">
            <template #main>
                <LoginFormComponent />
            </template>
        </VideoBackgroundComponent>
    </main>

</template>