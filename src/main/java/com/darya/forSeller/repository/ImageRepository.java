package com.darya.forSeller.repository;

import com.darya.forSeller.entity.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageProduct, Integer> {
}
