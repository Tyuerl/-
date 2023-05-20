package com.darya.marketplace.repository;

import com.darya.marketplace.entity.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageProduct, Integer> {
}
