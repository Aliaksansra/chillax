package com.samsolutions.chillax.validators;

import com.samsolutions.chillax.dto.PlaceDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * This class implements Validator class for
 * validating fields of PlaceDTO class
 *
 * @see Validator
 * @see PlaceDTO
 */
@Component
public class PlaceValidator implements Validator
{
    /**
     * regular expression for field 'city',
     * expression is in file 'application.properties'
     *
     * @see Pattern
     */
    @Value("#{T(java.util.regex.Pattern).compile('${city.regex}')}")
    private Pattern cityRegex;

    /**
     * maximum string length of fields value,
     * length is in file 'application.properties'
     */
    @Value("${max.length}")
    private int maxLength;

    /**
     * minimum string length of fields value,
     * length is in file 'application.properties'
     */
    @Value("${min.length}")
    private int minLength;

    /**
     * Overrides method 'support', assigns PlaceDTO class
     *
     * @param aClass validated class
     * @return assigned PlaceDTO class
     * @see PlaceDTO
     */
    @Override
    public boolean supports(Class<?> aClass)
    {
        return PlaceDTO.class.isAssignableFrom(aClass);
    }

    /**
     *  Overrides method 'validate',
     *  validates fields "city", "address", "placeName" of PlaceDTO class
     *
     * @param target validated object
     * @param errors receives validation errors
     * @see PlaceDTO
     */
    @Override
    public void validate(Object target, Errors errors)
    {
        PlaceDTO place = (PlaceDTO) target;
        String city = place.getCity();
        String address = place.getAddress();
        String placeName = place.getPlaceName();
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "city.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "address.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "placeName", "placeName.empty");
        if (!cityRegex.matcher(String.valueOf(city)).matches())
        {
            errors.rejectValue("city", "city.invalid");
        }

        if (city.length() > maxLength)
        {
            errors.rejectValue("city", "city.tooLong");
        } else if (city.length() < minLength)
        {
            errors.rejectValue("city", "city.tooshort");
        }
        if (address.length() > maxLength)
        {
            errors.rejectValue("address", "address.tooLong");
        } else if (address.length() < minLength)
        {
            errors.rejectValue("address", "address.tooshort");
        }
        if (placeName.length() > maxLength)
        {
            errors.rejectValue("placeName", "placeName.tooLong");
        } else if (placeName.length() < minLength)
        {
            errors.rejectValue("placeName", "placeName.tooshort");
        }
    }
}