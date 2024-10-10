package com.edu.unbosque.Digital.FinServ.Model;

//import java.util.List;

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


    //Relation product_type with financial_products
    // @OneToMany(mappedBy = "productType")
    // private List<Financial_ProductsModel> financialProducts;


    @Override
    public String toString() {
        return "Product_TypesModel [productTypeId=" + productTypeId + ", typeName=" + typeName + "]";
    
    }
}
