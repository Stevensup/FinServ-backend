package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.*;
import com.edu.unbosque.Digital.FinServ.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
            if (!customerRepository.existsById(creditCard.getCustomerId())) {
                throw new RuntimeException("Customer with ID " + creditCard.getCustomerId() + " does not exist.");
            }

            Product_TypesModel productType = productTypesRepository.findByTypeName(productTypeName)
                    .orElseThrow(() -> new RuntimeException("Tipo de producto '" + productTypeName + "' no encontrado"));

            Financial_ProductsModel financialProduct = new Financial_ProductsModel();
            financialProduct.setProductName(productTypeName);
            financialProduct.setProductType(productType);
            financialProduct.setDescription(productTypeName);
            financialProduct.setInterestRate(2);
            financialProduct.setCreditLimit(creditCard.getCreditLimit());
            Financial_ProductsModel savedFinancialProduct = financialProductsRepository.save(financialProduct);

            Customer_ProductsModel customerProduct = new Customer_ProductsModel();
            customerProduct.setCustomerId(creditCard.getCustomerId());
            customerProduct.setProductId(savedFinancialProduct.getProductId());
            customerProduct.setAcquisitionDate(new Date());
            customerProduct.setProductStatus(Customer_ProductsModel.ProductStatus.ACTIVE);
            customerProductsRepository.save(customerProduct);

            creditCard.setProductId(savedFinancialProduct.getProductId());

            return creditCardRepository.save(creditCard);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating credit card: " + e.getMessage());
        }
    }

    @Transactional
    public Credit_CardModel updateCreditLimit(int cardId, double newCreditLimit) {
        Credit_CardModel creditCard = creditCardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Tarjeta de crédito no encontrada con el ID: " + cardId));

        creditCard.setCreditLimit(newCreditLimit);
        creditCard.setAvailableBalance(newCreditLimit); // Ajusta el saldo disponible al nuevo límite
        return creditCardRepository.save(creditCard);
    }

    public List<Credit_CardModel> getCreditCardsByCustomerId(int customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new RuntimeException("Cliente con ID " + customerId + " no existe.");
        }

        // Lista de nombres válidos de productos
        List<String> validProductNames = Arrays.asList(
                "Tarjeta de Crédito con Recompensas en Efectivo",
                "Tarjeta de Crédito para Compras en Línea",
                "Tarjeta de Crédito de Bajos Intereses"
        );

        // Obtener las tarjetas de crédito y filtrar por nombres válidos
        return creditCardRepository.findByCustomerId(customerId).stream()
                .filter(card -> validProductNames.contains(card.getProductName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteCreditCard(int cardId) {
        if (!creditCardRepository.existsById(cardId)) {
            throw new RuntimeException("Tarjeta de crédito no encontrada con el ID: " + cardId);
        }
        creditCardRepository.deleteById(cardId);
    }

}
