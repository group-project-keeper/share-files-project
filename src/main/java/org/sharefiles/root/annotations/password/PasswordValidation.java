package org.sharefiles.root.annotations.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidation implements ConstraintValidator<PasswordStrength, String> {


    @Override
    public void initialize(PasswordStrength constraintAnnotation) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        if (password.length() >= 6 && isFirstLetterUpperCase(password) && hasNumber(password)){
            return true;
        } else {
            context.buildConstraintViolationWithTemplate("Password is to weak")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }

    }



    public boolean isFirstLetterUpperCase(String password) {
        String firstLetter = password.substring(0, 1);

        for(char i = 'A'; i <= 'Z'; i++) {
            if(firstLetter.equals(Character.toString(i))){
                return true;
            }
        }
        return false;

    }

    public boolean hasNumber(String password) {

        for(int i = 48; i <= 57; i++) {
            if(password.contains(Character.toString((char) i))){
                return true;
            }
        }

        return false;

    }
}
