package com.samsolutions.chillax.validators;

import com.samsolutions.chillax.dto.TypesDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * This class implements Validator class for
 * validating fields of TypesDTO class
 *
 * @see Validator
 * @see TypesDTO
 */
@Component
public class TypeValidator implements Validator
{
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
     * Overrides method 'support', assigns TypesDTO class
     *
     * @param aClass validated class
     * @return assigned TypesDTO class
     * @see TypesDTO
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return TypesDTO.class.equals(aClass);
    }

    /**
     * Overrides method 'validate',
     * validates field "nameOfType" of TypesDTO class
     *
     * @param target validated object
     * @param errors receives validation errors
     * @see TypesDTO
     */
    @Override
    public void validate(Object target, Errors errors)
    {
        TypesDTO type = (TypesDTO) target;
        String name = type.getNameOfType();
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameOfType", "nameOfType.empty");
        if (name.length() > maxLength)
        {
            errors.rejectValue("nameOfType", "nameOfType.tooLong");
        } else if (name.length() < minLength)
        {
            errors.rejectValue("nameOfType", "nameOfType.tooshort");
        }
    }
}