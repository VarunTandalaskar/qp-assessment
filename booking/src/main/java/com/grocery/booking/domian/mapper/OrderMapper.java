package com.grocery.booking.domian.mapper;

import com.grocery.booking.domian.Order;
import com.grocery.booking.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy= ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderMapper {

    OrderDTO toOrderDTO(Order order);
    Order toOrder(OrderDTO orderDTO);
}
