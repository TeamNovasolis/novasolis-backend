package com.teamnovasolis.novasolis.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailValidator
        implements ConstraintValidator<ValidEmail, String> {

    // Simple regex for basic email validation
    private static final String EMAIL_PATTERN = "^(.+)@(\\\\S+)$";

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        // Empty constructor
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return (validateEmail(email));
    }

    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}