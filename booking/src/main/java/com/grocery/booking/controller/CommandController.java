package com.grocery.booking.controller;

import com.grocery.booking.domian.Grocery;
import com.grocery.booking.domian.Order;
import com.grocery.booking.dto.GroceryDTO;
import com.grocery.booking.dto.OrderDTO;
import com.grocery.booking.service.CommandService;
import com.grocery.booking.service.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CommandController {

    @Autowired
    CommandService commandService;

    /**
     * Adds a new grocery item to the inventory.
     *
     * @param item The grocery item details to add.
     * @return The added grocery item details.
     */
    @PostMapping("/admin/grocery")
    public GroceryDTO addItem(@RequestBody GroceryDTO item) {
        return commandService.addGroceryItem(item);
    }

    /**
     * Books a new order for a user.
     *
     * @param order The order details to book.
     * @return The booked order details.
     */
    @PostMapping("/user/order")
    public OrderDTO bookOrder(@RequestBody OrderDTO order) {
        return commandService.bookOrder(order);
    }
}
