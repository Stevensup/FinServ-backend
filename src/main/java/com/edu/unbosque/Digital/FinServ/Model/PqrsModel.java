package com.edu.unbosque.Digital.FinServ.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;



/**
 * An entity PqrsModel that describes the PQRS Model
 * @Model PqrsModel
 */
@Data
@Entity
@Table(name = "pqrs")
public class PqrsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pqrs_id", nullable = false, updatable = false)
    private int pqrsId;

    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "pqrs_type", nullable = false)
    private PqrsType pqrsType;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PqrsStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    public enum PqrsType {
        Petición, Queja, Reclamo, Sugerencia
    }

    public enum PqrsStatus {
        Abierto, Proceso, Cerrado
    }
}
