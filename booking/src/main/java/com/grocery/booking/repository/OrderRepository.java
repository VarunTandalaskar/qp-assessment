package com.grocery.booking.repository;

import com.grocery.booking.domian.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
