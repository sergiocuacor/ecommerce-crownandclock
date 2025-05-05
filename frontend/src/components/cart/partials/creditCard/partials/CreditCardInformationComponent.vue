<template>

    <div class="ui-card-container">
        <div class="ui-credit-card">
            <div class="ui-magnetic-strip"></div>
            <div class="ui-inner">
            <div class="ui-card-number">
                <label class="ui-number-label">
                    {{ 'CARD NUMBER' }}
                </label>
                <br />
                <span>
                    {{ cardNumber }}
                </span>
            </div>
            <div class="ui-card-details">
                <div class="ui-card-holder">
                    <label>{{ 'CARDHOLDER NAME' }}</label>
                    <span class="ui-card-name">
                        {{ cardHolderName }}
                    </span>
                </div>
                <div class="ui-card-expiry">
                    <label>{{ 'EXPIRY DATE' }}</label>
                    <span class="ui-card-date">
                        {{ expiryDate }}
                    </span>
                </div>
            </div>
            </div>
        </div>
    </div>
        
</template>

<script setup>

    import { computed, defineProps } from 'vue';

    const props = defineProps({
        number: {
            type: String,
        },
        holderName: {
            type: String,
        },
        expiryDate: {
            type: String,
        }
    });

    const cardNumber = computed(() => props.number ? props.number.replace(/\s+/g, '').replace(/(.{4})/g, '$1 ').trim() : '0000 0000 0000 0000');
    const cardHolderName = computed(() => props.holderName ? props.holderName.toUpperCase() : 'JHON DOE');
    const expiryDate = computed(() => {
        const dateParts = props.expiryDate ? props.expiryDate.split('/') : ['--', '--'];
        return `${dateParts[0]}/${dateParts[1]}`;
    });

</script>

<style scoped>

    .ui-card-container {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .ui-credit-card {
        background: linear-gradient(145deg, #7dbbcc, #aa1100);
        border-radius: 16px;
        box-shadow: 0 4px 30px rgba(0, 0, 0, 0.2);
        color: white;
        padding: 0;
        position: relative;
    }

    .ui-inner {
        width: 320px;
        height: 150px;
        background: inherit;
        border-radius: 16px;
        box-shadow: inherit;
        color: white;
        padding: 20px;
        position: relative;
    }

    .ui-magnetic-strip {
        width: 100%;
        height: 30px;
        margin-top: 20px;
        background: linear-gradient(90deg, #000000, #434343 20%, #000000);
        border-radius: 4px;
    }

    .ui-card-number {
        margin-top: -10px;
        font-size: 20px;
        font-weight: 600;
        letter-spacing: 2px;
    }

    .ui-card-details {
        display: flex;
        justify-content: space-between;
        margin-top: 14px;
    }

    .ui-card-details div {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
    }

    .ui-card-details label,
    .ui-number-label {
        font-size: 10px;
        color: rgba(255, 255, 255, 0.7);
        margin-bottom: 5px;
    }

    .ui-card-name,
    .ui-card-date {
        color: white;
        font-size: 12px;
        font-weight: 900;
    }

    .ui-number-label {
        font-size: 10px;
    }

</style>
