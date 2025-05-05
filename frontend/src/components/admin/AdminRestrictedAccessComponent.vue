<template>

    <DynamicHeightDivComponent>
        <template #header>
            <section class="tw-font-mono tw-text-3xl tw-mx-3">
                {{ 'Access Restricted' }}                
            </section>
        </template>
        <template #footer>
            <div class="tw-font-mono tw-text-xl tw-text-gray-500 tw-mx-3">
                {{ 'You do not have permission to access this administrative section..' }}
            </div>
            <div class="tw-font-mono tw-text-xl tw-text-gray-500 tw-mx-3">
                {{ 'If you believe this is a mistake, please contact your system administrator.' }}
            </div>
        </template>
    </DynamicHeightDivComponent>

</template>