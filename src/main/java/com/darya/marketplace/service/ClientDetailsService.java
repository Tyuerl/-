package com.darya.marketplace.service;

import com.darya.marketplace.repository.ClientRepository;
import com.darya.marketplace.security.ClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailsService implements UserDetailsService {
    private ClientRepository clientRepository;

    @Autowired
    public ClientDetailsService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new ClientDetails(clientRepository.findByName(username)
                .orElseThrow(()->new UsernameNotFoundException("Client with name " + username + " not")));
    }
}
