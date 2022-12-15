package com.samsolutions.chillax.converters;

import com.samsolutions.chillax.dto.OrdersDTO;
import com.samsolutions.chillax.entity.Orders;
import com.samsolutions.chillax.facades.UsersFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class implements the Converter
 * for transforming an orders entities from a DTO.
 *
 * @see Converter
 * @see OrdersDTO
 * @see Orders
 */
@Component
public class OrdersConverterFromDTO implements Converter<OrdersDTO, Orders>
{
    /**
     * Automatic injects of the UsersFacade class
     * to get the Users entity by id required to create the Orders entity.
     *
     * @see UsersFacade
     */
    @Autowired
    private UsersFacade usersFacade;

    /**
     * Overrides method 'convert', converts the @param OrdersDTO
     * to the Orders entity and returns it.
     *
     * @param ordersDTO OrdersDTO
     * @return Orders
     */
    @Override
    public Orders convert(OrdersDTO ordersDTO)
    {
        Orders orders = null;
        if (ordersDTO != null)
        {
            orders = new Orders(ordersDTO.getIdOrders(), ordersDTO.getGuid(), ordersDTO.getTotalPrice(),
                    ordersDTO.getDiscount(), ordersDTO.getDateOfBooking(),
                    ordersDTO.isPaid(), usersFacade.getUserByPK(ordersDTO.getIdUser()));
        }
        return orders;
    }
}