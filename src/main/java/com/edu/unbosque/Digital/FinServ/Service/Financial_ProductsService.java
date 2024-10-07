package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.Financial_ProductsModel;
import com.edu.unbosque.Digital.FinServ.Repository.Financial_ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class Financial_ProductsService {
    
    @Autowired
    private Financial_ProductsRepository financial_productsRepository;

    public Financial_ProductsModel createFinancial_Products(Financial_ProductsModel financial_products) {	   
        return financial_productsRepository.save(financial_products);
    }
    
    public List<Financial_ProductsModel> getAllFinancialProducts(){
        return financial_productsRepository.findAll();
    }
    
    public Optional<Financial_ProductsModel> getFinancialProductsById(int id) {
        return financial_productsRepository.findById(id);
    }

    public Financial_ProductsModel updateFinancialProducts(int id, Financial_ProductsModel financial_products) {
        if (financial_productsRepository.existsById(id)) {
            return financial_productsRepository.save(financial_products);
        } else {
            throw new RuntimeException("Financial_Products not found with id " + id);
        }
    }
    
    public void deleteFinancialProducts(int id) {
        if (financial_productsRepository.existsById(id)) {
            financial_productsRepository.deleteById(id);
        } else {
            throw new RuntimeException("Financial_Products not found with id " + id);
        }
    }

    
}
