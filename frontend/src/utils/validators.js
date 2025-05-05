    export function validateStringLength(fieldName = 'Field', str, minLength = null, maxLength = null) {
        
        str = str.trim();
        const length = str.length;

        if (minLength === null && maxLength === null) {
            return { valid: true, message: `${fieldName} is valid: no length constraints.` };
        }

        if (minLength !== null && maxLength === null) {
            if (length >= minLength) {
                return { valid: true, message: `${fieldName} is valid: at least ${minLength} characters.` };
            } else {
                return { valid: false, message: `${fieldName} must be at least ${minLength} characters.` };
            }
        }

        if (minLength === null && maxLength !== null) {
            if (length <= maxLength) {
                return { valid: true, message: `${fieldName} is valid: up to ${maxLength} characters.` };
            } else {
                return { valid: false, message: `${fieldName} must be no more than ${maxLength} characters.` };
            }
        }

        if (length >= minLength && length <= maxLength) {
            return { valid: true, message: `${fieldName} is valid: between ${minLength} and ${maxLength} characters.` };
        } else {
            return {
                valid: false,
                message: `${fieldName} must be between ${minLength} and ${maxLength} characters.`,
            };
        }

    }

    export function validateName(fieldName = 'Field', str) {

        str = str.trim();
        const nameRegex = /^[\p{L}.'`\s]+$/u;
        const isValid = nameRegex.test(str);
    
        if (isValid) {
            return {
                valid: true,
                message: `${fieldName} is valid.`
            };
        } else {
            return {
                valid: false,
                message: `${fieldName} must contain only letters and valid punctuation (e.g., apostrophes, periods, or accents).`
            };
        }

    }

    export function validateEmail(fieldName = 'Email', str) {

        str = str.trim();    
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        const isValid = emailRegex.test(str);
    
        if (isValid) {
            return {
                valid: true,
                message: `${fieldName} is valid.`
            };
        } else {
            return {
                valid: false,
                message: `${fieldName} must be a valid email address (e.g., user@example.com).`
            };
        }

    }    

    export function validatePhoneNumber(fieldName = 'Phone number', str) {

        str = str.trim();    
        const phoneRegex = /^\+?[1-9]\d{1,14}$/; // E.164 format
        const isValid = phoneRegex.test(str);
    
        if (isValid) {
            return {
                valid: true,
                message: `${fieldName} is valid.`
            };
        } else {
            return {
                valid: false,
                message: `${fieldName} must be a valid international phone number in E.164 format (e.g., +1234567890).`
            };
        }

    }
    
    export function validateStreetAddress(fieldName = 'Street address', str) {

        str = str.trim();    
        const addressRegex = /^[a-zA-Z0-9\s,'#.-]{5,}$/;    
        const isValid = addressRegex.test(str);
    
        if (isValid) {
            return {
                valid: true,
                message: `${fieldName} is valid.`
            };
        } else {
            return {
                valid: false,
                message: `${fieldName} must be at least 5 characters and contain only letters, numbers, spaces, or common punctuation (, . - #).`
            };
        }

    }

    export function validatePostalCode(fieldName = 'Postal code', str) {

        str = str.trim();    
        const postalCodeRegex = /^[A-Za-z0-9\s\-]{3,10}$/;    
        const isValid = postalCodeRegex.test(str);
    
        if (isValid) {
            return {
                valid: true,
                message: `${fieldName} is valid.`
            };
        } else {
            return {
                valid: false,
                message: `${fieldName} must be 3–10 characters and may include letters, numbers, spaces, or hyphens.`
            };
        }

    }

    export function validateCreditCardNumber(str, fieldName = 'Credit card number') {

        const trimmed = str.replace(/\s+/g, '');

        if (!/^\d+$/.test(trimmed) || trimmed.length !== 16) {
            return {
                valid: false,
                message: `${fieldName} must contain only numbers and must be exactly 16 digits.`
            };
        } else {    
            return {
                valid: true,
                message: `${fieldName} is valid.`
            };
        }

    }
    
    export function validateCreditCardExpiryDate(str, fieldName = 'Credit card expiry date') {

        str = str.trim();    
        const dateRegex = /^(0?[1-9]|1[0-2])\/\d{2}$/;
    
        if (!dateRegex.test(str)) {
            return {
                valid: false,
                message: `${fieldName} must be in MM/YY format.`
            };
        }
    
        const [monthStr, yearStr] = str.split('/');
        const month = parseInt(monthStr, 10);
        const year = parseInt(yearStr, 10);
    
        const today = new Date();
        const currentMonth = today.getMonth() + 1;
        const currentYear = today.getFullYear() % 100;
    
        if (year > currentYear || (year === currentYear && month >= currentMonth)) {
            return {
                valid: true,
                message: `${fieldName} is valid.`
            };
        } else {
            return {
                valid: false,
                message: `${fieldName} must be a future date (MM/YY).`
            };
        }
    }
    
    export function validateCreditCardCVV(str, fieldName = 'Credit card CVV') {

        const trimmed = str.trim();
        
        if (!/^\d+$/.test(trimmed) || trimmed.length !== 3) {
            return {
                valid: false,
                message: `${fieldName} must contain only numbers and must be exactly 3 digits.`
            };
        } else {
            return {
                valid: true,
                message: `${fieldName} is valid.`
            };
        }

    }    

    export function validateIsNotEmpty(str) {
        
        return str.trim().length > 0;

    }

    export function validateIsNumber(str) {

        return !isNaN(str) && !isNaN(parseFloat(str));

    }

    export function validateHasOnlyLetters(str) {

        return /^[A-Za-z]+$/.test(str);

    }