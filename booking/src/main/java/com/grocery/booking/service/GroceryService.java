package com.grocery.booking.service;

import com.grocery.booking.constants.AppConstants;
import com.grocery.booking.domian.Grocery;
import com.grocery.booking.domian.mapper.GroceryMapper;
import com.grocery.booking.dto.GroceryDTO;
import com.grocery.booking.exception.ResourceNotFoundException;
import com.grocery.booking.repository.GroceryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class GroceryService {

    @Autowired
    GroceryMapper groceryMapper;
    @Autowired
    GroceryRepository groceryRepository;


    /**
     * Retrieves all grocery items from the inventory.
     *
     * @return A list of all grocery items.
     * @throws ResourceNotFoundException If the grocery items not available.
     */
    public List<GroceryDTO> getAllGroceryItems() {
        List<GroceryDTO> groceries = groceryMapper.toGroceryDTOList(groceryRepository.findAll());
        if (!CollectionUtils.isEmpty(groceries)) {
            return groceries;
        } else throw new ResourceNotFoundException(AppConstants.NO_INVENTORY);
    }

    /**
     * Removes a grocery item from the inventory by its ID.
     *
     * @param id The ID of the grocery item to remove.
     * @throws ResourceNotFoundException If the grocery item with the given ID is not found.
     */
    public void removeGroceryItem(Long id) {
        Grocery grocery = groceryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.GROCERY_ITEM_NOT_FOUND + id));
        groceryRepository.delete(grocery);
    }

    /**
     * Updates the details of an existing grocery item by its ID.
     *
     * @param id   The ID of the grocery item to update.
     * @param itemToUpdate The updated grocery item details.
     * @return The updated grocery item details.
     * @throws ResourceNotFoundException If the grocery item with the given ID is not found.
     */
    public GroceryDTO updateGroceryItem(Long id, GroceryDTO itemToUpdate) {
        return groceryRepository.findById(id).map(item -> {
            item.setName(itemToUpdate.getName());
            item.setPrice(itemToUpdate.getPrice());
            item.setQuantity(itemToUpdate.getQuantity());
            return groceryMapper.toGroceryDTO(groceryRepository.save(item));
        }).orElseThrow(() -> new ResourceNotFoundException(AppConstants.GROCERY_ITEM_NOT_FOUND + id));
    }


    /**
     * Updates the inventory level for a specific grocery item.
     *
     * @param id       The ID of the grocery item to update.
     * @param quantity The new inventory quantity for the grocery item.
     * @return The updated grocery item details.
     * @throws ResourceNotFoundException If the grocery item with the given ID is not found.
     */
    public GroceryDTO updateInventoryLevel(Long id, int quantity) {
        Grocery grocery = groceryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.GROCERY_ITEM_NOT_FOUND + id));
        if (quantity < 0) {
            throw new IllegalArgumentException(AppConstants.NEGATIVE_QUANTITY);
        }
        grocery.setQuantity(quantity);
        Grocery updatedGrocery = groceryRepository.save(grocery);

        return groceryMapper.toGroceryDTO(updatedGrocery);
    }
}
