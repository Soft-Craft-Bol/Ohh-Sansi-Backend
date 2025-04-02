package com.softcraft.ohhsansibackend.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = LettersAndSpacesValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface LettersAndSpaces {
    String message() default "solo las letras y espacios son permitidos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}