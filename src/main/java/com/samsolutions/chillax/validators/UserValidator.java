package com.samsolutions.chillax.validators;

import com.samsolutions.chillax.dto.UsersDTO;
import com.samsolutions.chillax.facades.UsersFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Pattern;

/**
 * This class implements Validator class for
 * validating fields of UsersDTO class
 *
 * @see Validator
 * @see UsersDTO
 */
@Component
public class UserValidator implements Validator
{
    /**
     * regular expression for field 'name',
     * expression is in file 'application.properties'
     *
     * @see Pattern
     */
    @Value("#{T(java.util.regex.Pattern).compile('${name.regex}')}")
    private Pattern nameRegex;

    /**
     * regular expression for field 'login',
     * expression is in file 'application.properties'
     *
     * @see Pattern
     */
    @Value("#{T(java.util.regex.Pattern).compile('${login.regex}')}")
    private Pattern loginRegex;

    /**
     * regular expression for field 'phone',
     * expression is in file 'application.properties'
     *
     * @see Pattern
     */
    @Value("#{T(java.util.regex.Pattern).compile('${phone.regex}')}")
    private Pattern phoneRegex;

    /**
     * maximum string length of fields value,
     * length is in file 'application.properties'
     */
    @Value("${max.length.short}")
    private int maxLength;

    /**
     * minimum string length of fields value,
     * length is in file 'application.properties'
     */
    @Value("${min.length}")
    private int minLength;

    /**
     * automatic injects of UsersFacade class
     * to check the user login and email uniqueness
     *
     * @see UsersFacade
     */
    @Autowired
    private UsersFacade usersFacade;

    /**
     * Overrides method 'support', assigns UsersDTO class
     *
     * @param aClass validated class
     * @return assigned UsersDTO class
     * @see UsersDTO
     */
    @Override
    public boolean supports(Class<?> aClass)
    {
        return UsersDTO.class.isAssignableFrom(aClass);

    }

    /**
     * Overrides method 'validate',
     * validates fields "name", "surname", "email",
     * "password", "login" of UsersDTO class
     *
     * @param target validated object
     * @param errors receives validation errors
     * @see UsersDTO
     */
    @Override
    public void validate(Object target, Errors errors)
    {
        UsersDTO user = (UsersDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "username.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "surname.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "login.empty");
        String name = user.getName();
        String surname = user.getSurname();
        if (name.length() > maxLength)
        {
            errors.rejectValue("name", "username.tooLong");
        } else if (name.length() < minLength)
        {
            errors.rejectValue("name", "username.tooshort");
        }
        if (!nameRegex.matcher(user.getName()).matches())
        {
            errors.rejectValue("name", "username.invalid");
        }
        if (surname.length() > maxLength)
        {
            errors.rejectValue("surname", "surname.tooLong");
        } else if (name.length() < minLength)
        {
            errors.rejectValue("surname", "surname.tooshort");
        }
        if (!nameRegex.matcher(user.getSurname()).matches())
        {
            errors.rejectValue("surname", "surname.invalid");
        }
        if (!usersFacade.checkUniqueUser(user, "login"))
        {
            errors.rejectValue("login", "login.notUnique");
        }
        if (!loginRegex.matcher(user.getLogin()).matches())
        {
            errors.rejectValue("login", "login.invalid");
        }
        if (!loginRegex.matcher(user.getPassword()).matches())
        {
            errors.rejectValue("password", "password.invalid");
        }
        if (!usersFacade.checkUniqueUser(user, "email"))
        {
            errors.rejectValue("email", "email.notUnique");
        }
        if (!EmailValidator.getInstance().isValid(user.getEmail()))
        {
            errors.rejectValue("email", "email.notValid");
        }
        if (!phoneRegex.matcher(user.getPhone()).matches())
        {
            errors.rejectValue("phone", "phone.invalid");
        }
    }
}