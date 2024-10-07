package com.edu.unbosque.Digital.FinServ.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
//import java.util.Date;


@Data
@Getter
@Setter
@Entity


@Table(name = "product_types")
public class Product_TypesModel {

    @Id
    @Column(name = "product_type_id", nullable = false, updatable = false)
    private int productTypeId;

    @Column(name = "type_name", nullable = false, length = 50)
    private String typeName;


    @Override
    public String toString() {
        return "Product_TypesModel [productTypeId=" + productTypeId + ", typeName=" + typeName + "]";
    
    }
}
