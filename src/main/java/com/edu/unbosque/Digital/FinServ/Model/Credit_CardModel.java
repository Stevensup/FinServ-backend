package com.edu.unbosque.Digital.FinServ.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * An entity Credit_CardModel that describes the Credit Card Model
 * @Model Credit_CardModel
 */
@Data
@Entity
@Table(name = "credit_card")
public class Credit_CardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCreditCard", nullable = false, updatable = false)
    private int idCreditCard;

    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column(name = "product_id", nullable = false)
    private int productId;

    @Column(name = "creditLimit", nullable = false)
    private double creditLimit;

    @Column(name = "availableBalance")
    private double availableBalance;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "expirationDate", nullable = false)
    private Date expirationDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    @JsonIgnore
    private CustomerModel customer;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Financial_ProductsModel financialProduct;

    // Campo transitorio para devolver el nombre del producto
    @Transient
    public String getProductName() {
        return financialProduct != null ? financialProduct.getProductName() : null;
    }
}
