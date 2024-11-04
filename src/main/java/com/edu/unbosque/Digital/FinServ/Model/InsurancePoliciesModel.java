package com.edu.unbosque.Digital.FinServ.Model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@Data
@Entity
@Table(name = "insurance_policies")
public class InsurancePoliciesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policy_id", nullable = false, updatable = false)
    private int policyId;

    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column(name = "product_id", nullable = false)
    private int productId;

    @Enumerated(EnumType.STRING)
    @Column(name = "policy_status", nullable = false)
    private PolicyStatus policyStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "expiration_date", nullable = false)
    private Date expirationDate;

    public enum PolicyStatus {
        Activo, Caducado, Cancelado
    }
}