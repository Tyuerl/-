package com.darya.marketplace.service;

import com.darya.marketplace.entity.ImageProduct;
import com.darya.marketplace.entity.Product;
import com.darya.marketplace.entity.Seller;
import com.darya.marketplace.repository.ImageRepository;
import com.darya.marketplace.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ImageRepository imageRepository;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
    }

    public Product save(Product product,
                        Seller seller,
                        MultipartFile file1,
                        MultipartFile file2,
                        MultipartFile file3) throws IOException {
        product.setSeller(seller);
        Product savedProduct = productRepository.save(product);
        if (!file1.isEmpty()){
            ImageProduct imageProduct = new ImageProduct();
            imageProduct.setImage(file1.getBytes());
            imageProduct.setProduct(savedProduct);
            imageRepository.save(imageProduct);
        }
        if (!file2.isEmpty()){
            ImageProduct imageProduct = new ImageProduct();
            imageProduct.setImage(file2.getBytes());
            imageProduct.setProduct(savedProduct);
            imageRepository.save(imageProduct);
        }
        if(!file3.isEmpty()){
            ImageProduct imageProduct = new ImageProduct();
            imageProduct.setImage(file3.getBytes());
            imageProduct.setProduct(savedProduct);
            imageRepository.save(imageProduct);
        }
        return savedProduct;
    }
}
