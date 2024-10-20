package com.example.demo.domain;

import com.example.demo.validators.ValidDeletePart;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 *
 *
 */
@Entity
@ValidDeletePart
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="part_type",discriminatorType = DiscriminatorType.INTEGER)
@Table(name="Parts")
public abstract class Part implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    @Min(value = 0, message = "Price value must be positive")
    double price;
    @Min(value = 0, message = "Inventory value must be positive")
    int inv;

    // added fields
    @Column(name = "MIN_INV")
    @Min(value = 2, message = "Minimum inventory must be at least 2")
    private Integer minInv = 2;

    @Column(name = "MAX_INV")
    @Max(value = 150, message = "Maximum inventory cannot exceed 35")
    private Integer maxInv = 150;
    //

    @ManyToMany
    @JoinTable(name="product_part", joinColumns = @JoinColumn(name="part_id"),
            inverseJoinColumns=@JoinColumn(name="product_id"))
    Set<Product> products= new HashSet<>();

    public Part() {
    }

    public Part(String name, double price, int inv) {
        this.name = name;
        this.price = price;
        this.inv = inv;
    }

    // overloaded from above (adding min and max fields)
    public Part(long id, String name, double price, int inv, int minInv, int maxInv) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.minInv = minInv;
        this.maxInv = maxInv;
    }
    //

    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInv() {
        return inv;
    }

    public void setInv(int inv) {
        if (inv >= this.minInv && inv <= this.maxInv) {
            this.inv = inv;
        } else {
            throw new IllegalArgumentException("Your input below dictates inventory must be between " + this.minInv + " and " + this.maxInv);
        }
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    // added setters / getters
    public Integer getMinInv() {
        return minInv;
    }

    public void setMinInv(Integer minInv) {
       if (minInv >= 2 && minInv <= this.maxInv) {
            this.minInv = minInv;
        } else {
            throw new IllegalArgumentException("Minimum inventory must be between 2 and " + this.maxInv);
        }
    }

    public Integer getMaxInv() {
        return maxInv;
    }

    public void setMaxInv(Integer maxInv) {
        if (maxInv <= 150 && maxInv >= this.minInv) {
            this.maxInv = maxInv;
        } else {
            throw new IllegalArgumentException("Maximum inventory must be between " + this.minInv + " and 150");
        }
    }
    //

    public String toString(){
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Part part = (Part) o;

        return id == part.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
