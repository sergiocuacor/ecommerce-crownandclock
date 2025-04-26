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
        const nameRegex = /^[\p{L}.'`]+$/u;
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

    export function validateIsNotEmpty(str) {
        
        return str.trim().length > 0;

    }

    export function validateIsNumber(str) {

        return !isNaN(str) && !isNaN(parseFloat(str));

    }

    export function validateHasOnlyLetters(str) {

        return /^[A-Za-z]+$/.test(str);

    }