package com.darya.forSeller.contoller;

import com.darya.forSeller.entity.Seller;
import com.darya.forSeller.service.SellerService;
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
    private final SellerService sellerService;

    @Autowired
    public AuthController(SellerService sellerService){
        this.sellerService = sellerService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/reg")
    public String regPage(@ModelAttribute Seller seller){
        return "registration";
    }

    @PostMapping("/reg")
    public String performReg(@ModelAttribute("seller") @Valid Seller seller,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/auth/reg";
        }
        System.out.println(sellerService.registration(seller));
        return "redirect:/auth/login";
    }
}
