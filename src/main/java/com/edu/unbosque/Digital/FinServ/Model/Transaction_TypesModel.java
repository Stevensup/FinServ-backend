package com.edu.unbosque.Digital.FinServ.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

    // Relación bidireccional (si es necesario agregar)
    // @OneToMany(mappedBy = "transactionType")
    // private List<TransactionsModel> transactions;
}
