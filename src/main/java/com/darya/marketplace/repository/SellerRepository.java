package com.darya.marketplace.repository;

import com.darya.marketplace.entity.Client;
import com.darya.marketplace.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
    Optional<Seller> findByName(String name);
}
