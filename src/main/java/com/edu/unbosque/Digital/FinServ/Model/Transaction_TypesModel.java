package com.edu.unbosque.Digital.FinServ.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


/**
 * An entity Transaction_TypesModel that describes the Transaction Types Model
 * @Model Transaction_TypesModel
 */
@Data
@Entity
@Getter
@Setter
@Table(name = "transaction_types")
public class Transaction_TypesModel {

    @Id
    @Column(name = "transaction_type_id", nullable = false, updatable = false)
    private int transactionTypeId;

    @Column(name = "transaction_name", nullable = false, length = 50)
    private String transactionName;

    @OneToMany(mappedBy = "transactionType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransactionsModel> transactions;
}