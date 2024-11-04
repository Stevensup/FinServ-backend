package com.edu.unbosque.Digital.FinServ.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "financial_products")
public class Financial_ProductsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false, updatable = false)
    private int productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @ManyToOne
    @JoinColumn(name = "product_type_id", nullable = false)
    private Product_TypesModel productType;  // Cambiado de productTypeId a productType

    @Column(name = "description")
    private String description;

    @Column(name = "interest_rate")
    private double interestRate;

    @Column(name = "credit_limit")
    private double creditLimit;

    public void setProductType(Product_TypesModel productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "Financial_ProductsModel [productId=" + productId + ", productName=" + productName +
                ", productType=" + productType + ", description=" + description +
                ", interestRate=" + interestRate + ", creditLimit=" + creditLimit + "]";
    }
}
