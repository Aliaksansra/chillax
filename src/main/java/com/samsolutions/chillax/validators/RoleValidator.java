package com.samsolutions.chillax.validators;

import com.samsolutions.chillax.dto.RolesDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * This class implements Validator class for
 * validating fields of RolesDTO class
 *
 * @see Validator
 * @see RolesDTO
 */
@Component
public class RoleValidator implements Validator
{
    /**
     * regular expression for field 'role',
     * expression is in file 'application.properties'
     *
     * @see Pattern
     */
    @Value("#{T(java.util.regex.Pattern).compile('${role.regex}')}")
    private Pattern roleRegex;

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
     * Overrides method 'support', assigns RolesDTO class
     *
     * @param aClass validated class
     * @return assigned RolesDTO class
     * @see RolesDTO
     */
    @Override
    public boolean supports(Class<?> aClass)
    {
        return RolesDTO.class.isAssignableFrom(aClass);
    }

    /**
     * Overrides method 'validate',
     * validates field "role" of RolesDTO class
     *
     * @param target validated object
     * @param errors receives validation errors
     * @see RolesDTO
     */
    @Override
    public void validate(Object target, Errors errors)
    {
        RolesDTO role = (RolesDTO) target;
        String roleName = role.getRole();
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "role.empty");
        if (roleName.length() > maxLength)
        {
            errors.rejectValue("role", "role.tooLong");
        } else if (roleName.length() < minLength)
        {
            errors.rejectValue("role", "role.tooshort");
        }

        if (!roleRegex.matcher(roleName).matches())
        {
            errors.rejectValue("role", "role.invalid");
        }
    }
}