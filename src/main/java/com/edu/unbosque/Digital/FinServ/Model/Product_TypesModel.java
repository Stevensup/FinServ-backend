package com.edu.unbosque.Digital.FinServ.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "product_types")
public class Product_TypesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_type_id", nullable = false, updatable = false)
    private int productTypeId;

    @Column(name = "type_name", nullable = false)
    private String typeName;

    @OneToMany(mappedBy = "productType")
    @JsonIgnore
    private List<Financial_ProductsModel> financialProducts;

    @Override
    public String toString() {
        return "Product_TypesModel [productTypeId=" + productTypeId + ", typeName=" + typeName + "]";
    }
}
