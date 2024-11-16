package com.edu.unbosque.Digital.FinServ.Model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


/**
 * An entity InvestmentModel that describes the Investment Model
 * @Model InvestmentModel
 */
@Data
@Entity
@Table(name = "investments")
public class InvestmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "investment_id", nullable = false, updatable = false)
    private int investmentId;

    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column(name = "product_id", nullable = false)
    private int productId;

    @Column(name = "investment_amount", nullable = false)
    private double investmentAmount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private InvestmentStatus status;

    public enum InvestmentStatus {
        Activo, Finalizado, Cancelado
    }
}
