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

    // Relación inversa con la tabla Transactions: Uno a muchos. (Pendiente revisar con el equipo si se va a usar, de manera que un tipo de transacción puede estar asociado con varias transacciones)

    // @OneToMany(mappedBy = "transactionType")
    // private List<TransactionsModel> transactions;
}
