package com.thecodereveal.clickart.repositories;

import com.thecodereveal.clickart.auth.entities.User;
import com.thecodereveal.clickart.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findByUser(User user);
}
