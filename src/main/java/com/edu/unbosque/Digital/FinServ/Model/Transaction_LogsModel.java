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
@Table(name = "transaction_logs")
public class Transaction_LogsModel {

    @Id
    @Column(name = "log_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int logId;

    @Column(name = "transaction_id", nullable = false)
    private int transactionId;

    @Column(name = "log_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date logDate = new Date();

    // Relación con la tabla: Transactions (Verificada)

    @ManyToOne
    @JoinColumn(name = "transaction_id", insertable = false, updatable = false)
    private TransactionsModel transaction;

}