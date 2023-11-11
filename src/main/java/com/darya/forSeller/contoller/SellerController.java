package com.darya.forSeller.contoller;

import com.darya.forSeller.entity.ImageProduct;
import com.darya.forSeller.entity.Product;
import com.darya.forSeller.entity.Seller;
import com.darya.forSeller.repository.*;
import com.darya.forSeller.security.SellerDetails;
import com.darya.forSeller.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
public class SellerController {
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final SellerRepository sellerRepository;
    private final ImageRepository imageRepository;
    private final BasketRepository basketRepository;
    private final ProductService productService;

    @Autowired
    public SellerController(ProductService productService, ProductRepository productRepository, ClientRepository clientRepository, SellerRepository sellerRepository, ImageRepository imageRepository, BasketRepository basketRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
        this.sellerRepository = sellerRepository;
        this.imageRepository = imageRepository;
        this.basketRepository = basketRepository;
    }
    @GetMapping("/hello")
    public String index(){
        return "hello";
    }

    ;    @GetMapping("/")
    public String showInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        SellerDetails clientDetails = (SellerDetails) authentication.getPrincipal();
        System.out.println(clientDetails.getSeller().getDescription());

        return "hello";
    }
    @GetMapping("/products")
    public String showProducts(Model model){
        Seller seller = sellerRepository.findById(getCurrentSeller().getId()).get();
        model.addAttribute("seller", seller);

        Map<Integer, String> productBase64Images = new HashMap<>();
        for(Product product: seller.getProducts()){
            productBase64Images.put(product.getId(), Base64.getEncoder().encodeToString(product.getImages().get(0).getImage()));
        }
        model.addAttribute("images", productBase64Images);

        return "products";
    }

    @GetMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") Product product){
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String saveProduct(@RequestParam("file1") MultipartFile file1,
                              @RequestParam("file2") MultipartFile file2,
                              @RequestParam("file3") MultipartFile file3,
                              @ModelAttribute("product") @Valid Product product,
                              BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()){
            return "redirect:/addProduct";
        }

        productService.save(product, getCurrentSeller(), file1, file2, file3);
        return "redirect:/products";
    }

    @GetMapping("/product/{id}")
    public String showProduct(Model model,
                              @PathVariable("id")int id){
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("product not found"));

        model.addAttribute("product", product);
        List<String> images = new ArrayList<>();
        for(ImageProduct image : product.getImages()){
            images.add(Base64.getEncoder().encodeToString(image.getImage()));
        }
        model.addAttribute("images", images);
        return "product";
    }

    @PostMapping("/product/{id}")
    public String changeCount(@ModelAttribute("product") Product product,
                              @PathVariable("id") int id){
        Product productDb = productRepository.findById(id).orElseThrow(()-> new RuntimeException("product not found"));

        productDb.setCount(product.getCount());

        productRepository.save(productDb);

        return "redirect:/product/" + id;
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productRepository.deleteById(id);
        return "redirect:/";
    }

    private Seller getCurrentSeller(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        SellerDetails sellerDetails = (SellerDetails) authentication.getPrincipal();
        return sellerDetails.getSeller();
    }
}
