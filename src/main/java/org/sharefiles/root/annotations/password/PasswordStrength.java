package org.sharefiles.root.annotations.password;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // -> this means where you can use your annotation, this one can be used on FIELDS only
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidation.class)
public @interface PasswordStrength {
    String message() default "Password is to weak"; // default message if you will not type in any.

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
