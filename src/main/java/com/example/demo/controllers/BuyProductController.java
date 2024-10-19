package com.example.demo.controllers;
import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

@Controller
public class BuyProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/buyProduct")
    public ModelAndView buyProduct(@RequestParam("productID") Long productID) {
        ModelAndView modelAndView = new ModelAndView();

        // Try to find the product by ID
        Optional<Product> foundID = productRepository.findById(productID);

        if (foundID.isPresent()) {
            Product product = foundID.get();

            // Check if there's inventory available
            if (product.getInv() > 0) {
                product.setInv(product.getInv() - 1);  // Decrement inventory
                productRepository.save(product);       // Save the updated product
                modelAndView.setViewName("confirmationbuyproduct");  // Redirect to success view
            } else {
                modelAndView.setViewName("productoos");  // If no inventory, redirect to out-of-stock view
            }
        } else {
            modelAndView.setViewName("productoos");  // Product not found
        }

        return modelAndView;
    }
}
