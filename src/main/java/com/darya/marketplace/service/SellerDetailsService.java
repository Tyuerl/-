package com.darya.marketplace.service;

import com.darya.marketplace.repository.ClientRepository;
import com.darya.marketplace.repository.SellerRepository;
import com.darya.marketplace.security.SellerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SellerDetailsService implements UserDetailsService {
    private SellerRepository sellerRepository;

    @Autowired
    public SellerDetailsService(SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new SellerDetails(sellerRepository.findByName(username)
                .orElseThrow(()->new UsernameNotFoundException("Seller with name " + username + " not")));
    }
}
