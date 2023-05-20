package com.darya.forSeller.service;

import com.darya.forSeller.entity.Seller;
import com.darya.forSeller.entity.enums.ERole;
import com.darya.forSeller.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    private SellerRepository sellerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SellerService(SellerRepository sellerRepository,
                         PasswordEncoder passwordEncoder) {
        this.sellerRepository = sellerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Seller registration(Seller seller){
        seller.setPassword(passwordEncoder.encode(seller.getPassword()));
        seller.getRoles().add(ERole.ROLE_SELLER);
        return sellerRepository.save(seller);
    }
}
