package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.Product_TypesModel;
import com.edu.unbosque.Digital.FinServ.Repository.Product_TypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class Product_TypesService {

    @Autowired
    private Product_TypesRepository products_typesRepository;

    public Product_TypesModel createProduct_Types(Product_TypesModel product_types) {
        return products_typesRepository.save(product_types);
    }

    public List<Product_TypesModel> getAllProductTypes() {
        return products_typesRepository.findAll();
    }

    public Optional<Product_TypesModel> getProductTypesById(int id) {
        return products_typesRepository.findById(id);
    }

    public Product_TypesModel updateProduct_Types(int id, Product_TypesModel product_types) {
        if (products_typesRepository.existsById(id)) {
            product_types.setProductTypeId(id);
            return products_typesRepository.save(product_types);
        } else {
            throw new RuntimeException("Product Types not found with id " + id);
        }
    }

    public void deleteProduct_Types(int id) {
        if (products_typesRepository.existsById(id)) {
            products_typesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product Types not found with id " + id);
        }
    }

    
}
