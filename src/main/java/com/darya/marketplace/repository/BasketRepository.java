package com.darya.marketplace.repository;

import com.darya.marketplace.entity.Basket;
import com.darya.marketplace.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer> {
    List<Basket> findAllByClient(Client client);
}
