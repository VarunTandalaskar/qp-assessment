package com.grocery.booking.repository;

import com.grocery.booking.domian.Grocery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryRepository extends JpaRepository<Grocery, Long> {
}
