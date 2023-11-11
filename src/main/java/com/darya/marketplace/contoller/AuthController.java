package com.darya.marketplace.contoller;

import com.darya.marketplace.entity.Client;
import com.darya.marketplace.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final ClientService clientService;

    @Autowired
    public AuthController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/reg")
    public String regPage(@ModelAttribute Client client){
        return "registration";
    }

    @PostMapping("/reg")
    public String performReg(@ModelAttribute("client") @Valid Client client,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/auth/reg";
        }
        System.out.println(clientService.registration(client));
        return "redirect:/auth/login";
    }
}
