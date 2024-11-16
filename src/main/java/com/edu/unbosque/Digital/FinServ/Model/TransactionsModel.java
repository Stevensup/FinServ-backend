package com.edu.unbosque.Digital.FinServ.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


/**
 * An entity TransactionsModel that describes the Transactions Model
 * @Model TransactionsModel
 */
@Data
@Entity
@Getter
@Setter
@Table(name = "transactions")
public class TransactionsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id", nullable = false, updatable = false)
    private int transactionId;

    @Column(name = "customer_id", nullable = false, insertable = false, updatable = false)
    private int customerId;

    @Column(name = "product_id", nullable = false, insertable = false, updatable = false)
    private int productId;

    @Column(name = "transaction_type_id", nullable = false, insertable = false, updatable = false)
    private int transactionTypeId;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "transaction_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerModel customer;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Financial_ProductsModel product;

    @ManyToOne
    @JoinColumn(name = "transaction_type_id", nullable = false)
    private Transaction_TypesModel transactionType;
}