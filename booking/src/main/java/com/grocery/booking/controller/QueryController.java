package com.grocery.booking.controller;

import com.grocery.booking.domian.Grocery;
import com.grocery.booking.dto.GroceryDTO;
import com.grocery.booking.service.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QueryController {
    @Autowired
    GroceryService groceryService;


    /**
     * Retrieves all grocery items from the inventory.
     *
     * @return A list of all grocery items.
     */
    @GetMapping("/common/groceries")
    public List<GroceryDTO> getAllGroceryItems() {
        return groceryService.getAllGroceryItems();
    }

    /**
     * Removes a grocery item from the inventory by its ID.
     *
     * @param id The ID of the grocery item to remove.
     */
    @DeleteMapping("/admin/grocery/{id}")
    public void removeGroceryItem(@PathVariable Long id) {
        groceryService.removeGroceryItem(id);
    }

    /**
     * Updates the details of an existing grocery item by its ID.
     *
     * @param id   The ID of the grocery item to update.
     * @param item The updated grocery item details.
     * @return The updated grocery item details.
     */
    @PutMapping("/admin/groceries/{id}")
    public GroceryDTO updateGroceryItem(@PathVariable Long id, @RequestBody GroceryDTO item) {
        return groceryService.updateGroceryItem(id, item);
    }

}
