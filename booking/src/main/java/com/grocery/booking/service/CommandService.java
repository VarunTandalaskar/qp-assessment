package com.grocery.booking.service;

import com.grocery.booking.constants.AppConstants;
import com.grocery.booking.domian.Grocery;
import com.grocery.booking.domian.Order;
import com.grocery.booking.domian.mapper.GroceryMapper;
import com.grocery.booking.domian.mapper.OrderMapper;
import com.grocery.booking.dto.GroceryDTO;
import com.grocery.booking.dto.OrderDTO;
import com.grocery.booking.exception.InsufficientStockException;
import com.grocery.booking.exception.ResourceNotFoundException;
import com.grocery.booking.repository.GroceryRepository;
import com.grocery.booking.repository.OrderRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandService {

    @Autowired
    GroceryRepository groceryRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    GroceryMapper groceryMapper;


    public OrderDTO bookOrder(OrderDTO orderDTO) {
        if (orderDTO != null) {
            try {
                Order order = orderMapper.toOrder(orderDTO);
                order.getItems().forEach(orderItem -> {
                    Grocery groceryItem = groceryRepository.findById(orderItem.getGroceryItemId())
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Grocery item with ID " + orderItem.getGroceryItemId() + AppConstants.NOT_FOUND));
                    if (groceryItem.getQuantity() < orderItem.getQuantity()) {
                        throw new InsufficientStockException(
                                AppConstants.INSUFFICIENT_STOCK + groceryItem.getName() +
                                        ". Available: " + groceryItem.getQuantity());
                    }
                    groceryItem.setQuantity(groceryItem.getQuantity() - orderItem.getQuantity());
                    groceryRepository.save(groceryItem);
                });
                return orderMapper.toOrderDTO(orderRepository.save(order));
            } catch (ServiceException e) {
                throw new ServiceException(AppConstants.ORDER_PROCESS_EXCEPTION + orderDTO);
            }
        } else throw new ServiceException(AppConstants.NULL_ORDER);
    }

    public GroceryDTO addGroceryItem(GroceryDTO groceryItem) {
        if (groceryItem != null) {
            return groceryMapper.toGroceryDTO(groceryRepository.save(groceryMapper.toGrocery(groceryItem)));
        } else throw new ServiceException(AppConstants.NULL_GROCERIES);
    }
}
