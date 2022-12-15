package com.samsolutions.chillax.converters;

import com.samsolutions.chillax.dto.OrdersDTO;
import com.samsolutions.chillax.entity.Orders;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class implements the Converter
 * for transforming an orders entities into a DTO.
 *
 * @see Converter
 * @see Orders
 * @see OrdersDTO
 */
@Component
public class OrdersConverterToDTO implements Converter<Orders, OrdersDTO>
{
    /**
     * Overrides method 'convert', converts the @param Orders
     * to the OrdersDTO and returns it.
     *
     * @param orders Orders
     * @return OrdersDTO
     */
    @Override
    public OrdersDTO convert(Orders orders)
    {
        OrdersDTO ordersDTO = null;
        if (orders != null)
        {
            ordersDTO = new OrdersDTO(orders.getIdOrders(), orders.getGuid(), orders.getTotalPrice(),
                    orders.getDiscount(), orders.getDateOfBooking(), orders.isPaid(), orders.getUser().getIdUser());
        }
        return ordersDTO;
    }
}