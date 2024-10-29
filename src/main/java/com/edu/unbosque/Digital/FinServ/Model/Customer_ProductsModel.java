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

    @Column(name = "customer_id", nullable = false, insertable = false, updatable = false)
    private int customerId;

    @Column(name = "product_id", nullable = false, insertable = false, updatable = false)
    private int productId;

    @Column(name = "acquisition_date", nullable = false, updatable = false)
    private Date acquisitionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status", nullable = false, updatable = false)
    private ProductStatus productStatus;

    public enum ProductStatus {
        ACTIVE,
        INACTIVE
    }

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerModel customer;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Financial_ProductsModel financialProduct;

    @Override
    public String toString() {
        return "Customer_ProductsModel [customerProductId=" + customerProductId + ", customerId=" + customerId +
                ", productId=" + productId + ", acquisitionDate=" + acquisitionDate + ", productStatus=" + productStatus
                + "]";
    }
}