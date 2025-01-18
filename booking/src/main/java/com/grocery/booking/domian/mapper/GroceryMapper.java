package com.grocery.booking.domian.mapper;

import com.grocery.booking.domian.Grocery;
import com.grocery.booking.dto.GroceryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy= ReportingPolicy.IGNORE, componentModel = "spring")
public interface GroceryMapper {
    Grocery toGrocery(GroceryDTO groceryDTO);
    GroceryDTO toGroceryDTO(Grocery grocery);

    List<GroceryDTO> toGroceryDTOList(List<Grocery> groceries);
}
