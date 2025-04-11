package com.softcraft.ohhsansibackend.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LettersAndSpacesValidator implements ConstraintValidator<LettersAndSpaces, String> {

    private static final String LETTERS_AND_SPACES_PATTERN = "^[a-zA-Z\\s]+$";

    @Override
    public void initialize(LettersAndSpaces constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.matches(LETTERS_AND_SPACES_PATTERN);
    }
}