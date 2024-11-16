package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.*;
import com.edu.unbosque.Digital.FinServ.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Service class for managing insurance policies.
 */
@Service
public class InsurancePoliciesService {

    @Autowired
    private InsurancePoliciesRepository insurancePoliciesRepository;

    @Autowired
    private Financial_ProductsRepository financialProductsRepository;

    @Autowired
    private Customer_ProductsRepository customerProductsRepository;

    @Autowired
    private Product_TypesRepository productTypesRepository;

    /**
     * Crea una nueva póliza de seguro para un cliente.
     *
     * @param insurancePolicy el modelo de la póliza a crear
     * @param productTypeName el nombre del tipo de producto
     * @return la póliza de seguro creada
     */
    @Transactional
    public InsurancePoliciesModel createInsurancePolicy(InsurancePoliciesModel insurancePolicy, String productTypeName) {
        try {
            // Obtener el tipo de producto a partir del nombre proporcionado
            Product_TypesModel productType = productTypesRepository.findByTypeName(productTypeName)
                    .orElseThrow(() -> new RuntimeException("Tipo de producto '" + productTypeName + "' no encontrado"));

            // Crear el producto financiero asociado
            Financial_ProductsModel financialProduct = new Financial_ProductsModel();
            financialProduct.setProductName( productTypeName);
            financialProduct.setProductType(productType);
            financialProduct.setDescription( productTypeName);
            financialProduct.setInterestRate(0.0);
            financialProduct.setCreditLimit(0.0);
            Financial_ProductsModel savedFinancialProduct = financialProductsRepository.save(financialProduct);

            // Crear la relación en customer_products
            Customer_ProductsModel customerProduct = new Customer_ProductsModel();
            customerProduct.setCustomerId(insurancePolicy.getCustomerId());
            customerProduct.setProductId(savedFinancialProduct.getProductId());
            customerProduct.setAcquisitionDate(new Date());
            customerProduct.setProductStatus(Customer_ProductsModel.ProductStatus.ACTIVE);
            customerProductsRepository.save(customerProduct);

            // Asignar el productId al modelo de póliza de seguro
            insurancePolicy.setProductId(savedFinancialProduct.getProductId());

            // Establecer la fecha de expiración y el estado de la póliza
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 1);  // Añade un año
            insurancePolicy.setExpirationDate(calendar.getTime());
            insurancePolicy.setPolicyStatus(InsurancePoliciesModel.PolicyStatus.Activo);

            return insurancePoliciesRepository.save(insurancePolicy);

        } catch (Exception e) {
            throw new RuntimeException("Error creating insurance policy: " + e.getMessage());
        }
    }

    /**
     * Elimina una póliza de seguro por su ID junto con las asociaciones en customer_products y financial_products.
     *
     * @param policyId el ID de la póliza a eliminar
     */
    @Transactional
    public void deleteInsurancePolicy(int policyId) {

        InsurancePoliciesModel insurancePolicy = insurancePoliciesRepository.findById(policyId)
                .orElseThrow(() -> new RuntimeException("Policy not found with id " + policyId));


        insurancePoliciesRepository.delete(insurancePolicy);


        List<Customer_ProductsModel> customerProducts = customerProductsRepository.findByProductId(insurancePolicy.getProductId());
        for (Customer_ProductsModel customerProduct : customerProducts) {
            customerProductsRepository.delete(customerProduct);
        }


        Financial_ProductsModel financialProduct = financialProductsRepository.findById(insurancePolicy.getProductId())
                .orElseThrow(() -> new RuntimeException("Financial product not found with id " + insurancePolicy.getProductId()));
        financialProductsRepository.delete(financialProduct);
    }

    @Transactional
    public InsurancePoliciesModel updatePolicyStatus(int policyId, String policyStatus) {
        InsurancePoliciesModel existingPolicy = insurancePoliciesRepository.findById(policyId)
                .orElseThrow(() -> new RuntimeException("Policy not found with id " + policyId));

        existingPolicy.setPolicyStatus(InsurancePoliciesModel.PolicyStatus.valueOf(policyStatus));
        return insurancePoliciesRepository.save(existingPolicy);
    }


    /**
     * Obtiene los detalles de todas las pólizas de seguro de un cliente específico, incluyendo el nombre del producto, estado de la póliza y fecha de expiración.
     *
     * @param customerId el ID del cliente
     * @return una lista de mapas que contienen los detalles de cada póliza de seguro
     */
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getPolicyDetailsByCustomerId(int customerId) {
        List<InsurancePoliciesModel> policies = insurancePoliciesRepository.findByCustomerId(customerId);

        List<Map<String, Object>> policyDetailsList = new ArrayList<>();

        for (InsurancePoliciesModel policy : policies) {
            Optional<Financial_ProductsModel> financialProductOpt = financialProductsRepository.findById(policy.getProductId());

            if (financialProductOpt.isPresent()) {
                Financial_ProductsModel financialProduct = financialProductOpt.get();
                Map<String, Object> policyDetails = new HashMap<>();
                policyDetails.put("policyId", policy.getPolicyId());
                policyDetails.put("productName", financialProduct.getProductName());
                policyDetails.put("policyStatus", policy.getPolicyStatus());
                policyDetails.put("expirationDate", policy.getExpirationDate());
                policyDetailsList.add(policyDetails);
            }
        }
        return policyDetailsList;
    }

}
