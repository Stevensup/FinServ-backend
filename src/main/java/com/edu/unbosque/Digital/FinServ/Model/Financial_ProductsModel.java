package com.edu.unbosque.Digital.FinServ.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "financial_products")
public class Financial_ProductsModel {

    @Id
    @Column(name = "product_id", nullable = false, updatable = false)
    private int productId;

    @Column(name = "product_name", nullable = false, length = 255)
    private String productName;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "interest_rate", nullable = false, updatable = false)
    private double interestRate;

    @Column(name = "credit_limit", nullable = false, updatable = false)
    private double creditLimit;

    @ManyToOne
    @JoinColumn(name = "product_type_id", nullable = false)
    private Product_TypesModel productType;

    @Override
    public String toString() {
        return "Financial_ProductsModel [productId=" + productId + ", productName=" + productName + ", productType=" + productType
                + ", description=" + description + ", interestRate=" + interestRate + ", creditLimit=" + creditLimit + "]";
    }
}