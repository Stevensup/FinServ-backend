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
     * Crea una nueva tarjeta de crédito con un límite de crédito aleatorio.
     *
     * @param creditCard el modelo de la tarjeta de crédito a crear
     * @return la tarjeta de crédito creada
     */
    @Transactional
    public Credit_CardModel createCreditCard(Credit_CardModel creditCard) {
        try {
            // Verificar si el cliente existe
            if (!customerRepository.existsById(creditCard.getCustomerId())) {
                throw new RuntimeException("Customer with ID " + creditCard.getCustomerId() + " does not exist.");
            }

            // Paso 1: Obtener el objeto Product_TypesModel para el tipo de producto "Tarjeta de credito"
            Product_TypesModel productType = productTypesRepository.findByTypeName("Tarjeta de credito")
                    .orElseThrow(() -> new RuntimeException("Tipo de producto 'Tarjeta de credito' no encontrado"));

            // Paso 2: Crear el producto financiero asociado
            Financial_ProductsModel financialProduct = new Financial_ProductsModel();
            financialProduct.setProductName("Tarjeta de Crédito " + (creditCard.getIdCreditCard() + 1));
            financialProduct.setProductType(productType);
            financialProduct.setDescription("Tarjeta de crédito bancaria con beneficios y límites personalizados.");
            financialProduct.setInterestRate(0.0);
            financialProduct.setCreditLimit(creditCard.getCreditLimit());
            Financial_ProductsModel savedFinancialProduct = financialProductsRepository.save(financialProduct);

            // Paso 3: Crear la relación en customer_products
            Customer_ProductsModel customerProduct = new Customer_ProductsModel();
            customerProduct.setCustomerId(creditCard.getCustomerId());
            customerProduct.setProductId(savedFinancialProduct.getProductId());
            customerProduct.setAcquisitionDate(new Date());
            customerProduct.setProductStatus(Customer_ProductsModel.ProductStatus.ACTIVE);
            customerProductsRepository.save(customerProduct);

            // Paso 4: Asignar el productId al modelo de tarjeta de crédito y guardarlo
            creditCard.setProductId(savedFinancialProduct.getProductId());
            return creditCardRepository.save(creditCard);

        } catch (Exception e) {
            throw new RuntimeException("Error creating credit card: " + e.getMessage());
        }
    }

    /**
     * Elimina una tarjeta de crédito por su ID.
     *
     * @param idCreditCard el ID de la tarjeta de crédito a eliminar
     */
    @Transactional
    public void deleteCreditCard(int idCreditCard) {
        Credit_CardModel creditCard = creditCardRepository.findById(idCreditCard)
                .orElseThrow(() -> new RuntimeException("Credit card not found with id " + idCreditCard));

        creditCardRepository.delete(creditCard);
        customerProductsRepository.deleteAll(customerProductsRepository.findByProductId(creditCard.getProductId()));
        financialProductsRepository.deleteById(creditCard.getProductId());
    }

    /**
     * Obtiene todas las tarjetas de crédito de un cliente específico.
     *
     * @param customerId el ID del cliente
     * @return una lista de tarjetas de crédito asociadas al cliente
     */
    public List<Credit_CardModel> getCreditCardsByCustomerId(int customerId) {
        return creditCardRepository.findByCustomerId(customerId);
    }

    /**
     * Obtiene una tarjeta de crédito por su ID.
     *
     * @param idCreditCard el ID de la tarjeta de crédito
     * @return la tarjeta de crédito, si existe
     */
    public Credit_CardModel getCreditCardById(int idCreditCard) {
        return creditCardRepository.findById(idCreditCard)
                .orElseThrow(() -> new RuntimeException("Credit card not found with id " + idCreditCard));
    }

    /**
     * Actualiza el límite de crédito de una tarjeta existente.
     *
     * @param idCreditCard el ID de la tarjeta de crédito
     * @param newCreditLimit el nuevo límite de crédito
     * @return la tarjeta de crédito actualizada
     */
    @Transactional
    public Credit_CardModel updateCreditCard(int idCreditCard, double newCreditLimit) {
        Credit_CardModel existingCard = creditCardRepository.findById(idCreditCard)
                .orElseThrow(() -> new RuntimeException("Credit card not found with id " + idCreditCard));

        existingCard.setCreditLimit(newCreditLimit);
        existingCard.setAvailableBalance(newCreditLimit); // Ajustar el balance disponible al nuevo límite de crédito
        return creditCardRepository.save(existingCard);
    }
}
