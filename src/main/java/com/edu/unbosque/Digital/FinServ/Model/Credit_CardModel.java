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
@Table(name = "credit_card")
public class Credit_CardModel {

    @Id
    @Column(name = "idCreditCard", nullable = false, updatable = false)
    private int idCreditCard;

    @Column(name = "customer_id", nullable = false, insertable = false, updatable = false)
    private int customerId;

    @Column(name = "product_id", nullable = false, insertable = false, updatable = false)
    private int productId;

    @Column(name = "creditLimit", nullable = false, updatable = false)
    private double creditLimit;

    @Column(name = "availableBalance", nullable = false, updatable = false)
    private double availableBalance;

    @Column(name = "expirationDate", nullable = false, updatable = false)
    private Date expirationDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerModel customer;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Financial_ProductsModel financialProduct;

    @Override
    public String toString() {
        return "Credit_CardModel{" +
                "idCreditCard=" + idCreditCard +
                ", customerId=" + customerId +
                ", productId=" + productId +
                ", creditLimit=" + creditLimit +
                ", availableBalance=" + availableBalance +
                ", expirationDate=" + expirationDate +
                '}';
    }
}