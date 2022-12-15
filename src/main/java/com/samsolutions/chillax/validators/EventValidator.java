package com.samsolutions.chillax.validators;

import com.samsolutions.chillax.dto.EventsDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import java.util.regex.Pattern;

/**
 * This class implements Validator class for
 * validating fields of EventsDTO class
 *
 * @see Validator
 * @see EventsDTO
 */
@Component
public class EventValidator implements Validator
{
    /**
     * regular expression for field 'price',
     * expression is in file 'application.properties'
     *
     * @see Pattern
     */
    @Value("#{T(java.util.regex.Pattern).compile('${price.regex}')}")
    private Pattern priceRegex;

    /**
     * regular expression for field 'tickets',
     * expression is in file 'application.properties'
     *
     * @see Pattern
     */
    @Value("#{T(java.util.regex.Pattern).compile('${tickets.regex}')}")
    private Pattern ticketsRegex;

    /**
     * Overrides method 'support', assigns EventsDTO class
     *
     * @param aClass validated class
     * @return assigned EventsDTO class
     * @see EventsDTO
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return EventsDTO.class.isAssignableFrom(aClass);
    }

    /**
     *  Overrides method 'validate',
     *  validates fields "title", "datetimeOfEvent",
     *  "price", "allTickets" of EventsDTO class
     *
     * @param target validated object
     * @param errors receives validation errors
     * @see EventsDTO
     */
    @Override
    public void validate(Object target, Errors errors)
    {
        EventsDTO event = (EventsDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "datetimeOfEvent", "date.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "price.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "allTickets", "tickets.empty");
        if (!priceRegex.matcher(String.valueOf(event.getPrice())).matches())
        {
            errors.rejectValue("price", "price.invalid");
        }
        if (!ticketsRegex.matcher(String.valueOf(event.getAllTickets())).matches())
        {
            errors.rejectValue("allTickets", "allTickets.invalid");
        }
    }
}