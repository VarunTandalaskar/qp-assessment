package com.grocery.booking.service;

import com.grocery.booking.constants.AppConstants;
import com.grocery.booking.domian.Grocery;
import com.grocery.booking.domian.mapper.GroceryMapper;
import com.grocery.booking.dto.GroceryDTO;
import com.grocery.booking.exception.ResourceNotFoundException;
import com.grocery.booking.repository.GroceryRepository;
import org.hibernate.service.spi.ServiceException;
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

    public GroceryDTO addGrocery(GroceryDTO groceryDTO) {
        Grocery groceryToAdd = groceryMapper.toGrocery(groceryDTO);
        return groceryMapper.toGroceryDTO(groceryRepository.save(groceryToAdd));
    }
    public List<GroceryDTO> getAllGroceryItems() {
        List<GroceryDTO> groceries = groceryMapper.toGroceryDTOList(groceryRepository.findAll());
        if (!CollectionUtils.isEmpty(groceries)) {
            return groceries;
        } else throw new ResourceNotFoundException(AppConstants.NO_INVENTORY);
    }
    public void removeGroceryItem(Long id) {
        Grocery grocery = groceryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.GROCERY_ITEM_NOT_FOUND + id));
        groceryRepository.delete(grocery);
    }

    public GroceryDTO updateGroceryItem(Long id, GroceryDTO updatedItem) {
        return groceryRepository.findById(id).map(item -> {
            item.setName(updatedItem.getName());
            item.setPrice(updatedItem.getPrice());
            item.setQuantity(updatedItem.getQuantity());
            return groceryMapper.toGroceryDTO(groceryRepository.save(item));
        }).orElseThrow(() -> new ResourceNotFoundException(AppConstants.GROCERY_ITEM_NOT_FOUND + id));
    }
}
