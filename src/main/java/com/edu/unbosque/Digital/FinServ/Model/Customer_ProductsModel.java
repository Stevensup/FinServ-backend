package com.edu.unbosque.Digital.FinServ.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Data
@Entity
@Getter
@Setter

@Table(name = "customer_products")
public class Customer_ProductsModel {

    @Id
    @Column(name = "customer_product_id", nullable = false, updatable = false)
    private int customerProductId;

    @Column(name = "customer_id", nullable = false, updatable = true)
    private int customerId;

    @Column(name = "product_id", nullable = false, updatable = true)
    private int productId;

    @Column(name = "acquisition_date", nullable = false, updatable = false)
    private Date acquisitionDate;  

    @Enumerated(EnumType.STRING) //  O EnumType.
    @Column(name = "product_status", nullable = false, updatable = true)
    private ProductStatus productStatus;

    public enum ProductStatus {
        ACTIVE,
        INACTIVE
    }

    // @ManyToOne
    // @JoinColumn(name = "customer_product_id")
    // private Customer_ProductsModel customerProductsModel;

    // @ManyToOne
    // @JoinColumn(name = "product_id")
    // private Financial_ProductsModel financialProductsModel;


    @Override
    public String toString() {
        return "Customer_ProductsModel [customerProductId=" + customerProductId + ", customerId=" + customerId + ", productId=" + productId + ", acquisitionDate=" + acquisitionDate + ", productStatus=" + productStatus + "]";
    
    }
}
