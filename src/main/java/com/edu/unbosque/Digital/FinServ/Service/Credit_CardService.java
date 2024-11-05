package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.*;
import com.edu.unbosque.Digital.FinServ.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class Credit_CardService {

    @Autowired
    private Credit_CardRepository creditCardRepository;

    @Autowired
    private Financial_ProductsRepository financialProductsRepository;

    @Autowired
    private Customer_ProductsRepository customerProductsRepository;

    @Autowired
    private Product_TypesRepository productTypesRepository;

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Crea una nueva tarjeta de crédito para un cliente.
     *
     * @param creditCard el modelo de la tarjeta de crédito a crear
     * @param productTypeName el nombre del tipo de producto seleccionado por el cliente
     * @return la tarjeta de crédito creada
     */
    @Transactional
    public Credit_CardModel createCreditCard(Credit_CardModel creditCard, String productTypeName) {
        try {
            // Verificar si el cliente existe
            if (!customerRepository.existsById(creditCard.getCustomerId())) {
                throw new RuntimeException("Customer with ID " + creditCard.getCustomerId() + " does not exist.");
            }

            // Obtener el tipo de producto a partir del nombre proporcionado
            Product_TypesModel productType = productTypesRepository.findByTypeName(productTypeName)
                    .orElseThrow(() -> new RuntimeException("Tipo de producto '" + productTypeName + "' no encontrado"));

            // Crear el producto financiero asociado
            Financial_ProductsModel financialProduct = new Financial_ProductsModel();
            financialProduct.setProductName("Tarjeta de Crédito - " + productTypeName);
            financialProduct.setProductType(productType);
            financialProduct.setDescription("Descripción de la tarjeta de crédito para el tipo " + productTypeName);
            financialProduct.setInterestRate(0.0);
            financialProduct.setCreditLimit(creditCard.getCreditLimit());
            Financial_ProductsModel savedFinancialProduct = financialProductsRepository.save(financialProduct);

            // Crear la relación en customer_products
            Customer_ProductsModel customerProduct = new Customer_ProductsModel();
            customerProduct.setCustomerId(creditCard.getCustomerId());
            customerProduct.setProductId(savedFinancialProduct.getProductId());
            customerProduct.setAcquisitionDate(new Date());
            customerProduct.setProductStatus(Customer_ProductsModel.ProductStatus.ACTIVE);
            customerProductsRepository.save(customerProduct);

            // Asignar el productId al modelo de tarjeta de crédito y guardarlo
            creditCard.setProductId(savedFinancialProduct.getProductId());

            return creditCardRepository.save(creditCard);

        } catch (Exception e) {
            throw new RuntimeException("Error creating credit card: " + e.getMessage());
        }
    }
}
