package com.example.demo.validators;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 *
 *
 *
 */
public class EnufPartsValidator implements ConstraintValidator<ValidEnufParts, Product> {
    @Autowired
    private ApplicationContext context;
    public static  ApplicationContext myContext;
    @Override
    public void initialize(ValidEnufParts constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Product product, ConstraintValidatorContext constraintValidatorContext) {
        if (context == null) return true;

        ProductService repo = context.getBean(ProductServiceImpl.class);
        if (product.getId() != 0) {
            // Fetch the existing product from the database
            Product myProduct = repo.findById((int) product.getId());
            int inventoryChange = product.getInv() - myProduct.getInv(); // Calculate the change in inventory

            // Check each part for sufficient inventory
            for (Part part : myProduct.getParts()) {
                int newInventory = part.getInv() - inventoryChange; // Calculate new inventory after the change
                if (newInventory < part.getMinInv()) { // Check against the minimum inventory
                    return false; // Not enough inventory for this part
                }
            }
        }
        // If creating a new product or no parts, return true
        return true;
    }
}
