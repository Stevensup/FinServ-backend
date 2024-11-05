package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.*;
import com.edu.unbosque.Digital.FinServ.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


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
            Product_TypesModel productType = productTypesRepository.findByTypeName(productTypeName)
                    .orElseThrow(() -> new RuntimeException("Tipo de producto '" + productTypeName + "' no encontrado"));

            Financial_ProductsModel financialProduct = new Financial_ProductsModel();
            financialProduct.setProductName("Seguro " + insurancePolicy.getPolicyId() + 1);
            financialProduct.setProductType(productType);
            financialProduct.setDescription("Descripción del seguro para el tipo " + productTypeName);
            financialProduct.setInterestRate(0.0);
            financialProduct.setCreditLimit(0.0);
            Financial_ProductsModel savedFinancialProduct = financialProductsRepository.save(financialProduct);

            Customer_ProductsModel customerProduct = new Customer_ProductsModel();
            customerProduct.setCustomerId(insurancePolicy.getCustomerId());
            customerProduct.setProductId(savedFinancialProduct.getProductId());
            customerProduct.setAcquisitionDate(new Date());
            customerProduct.setProductStatus(Customer_ProductsModel.ProductStatus.ACTIVE);
            customerProductsRepository.save(customerProduct);

            insurancePolicy.setProductId(savedFinancialProduct.getProductId());

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 1);
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

    /**
     * Actualiza una póliza de seguro existente.
     *
     * @param policyId el ID de la póliza a actualizar
     * @param policyDetails el modelo con los datos actualizados de la póliza
     * @return la póliza de seguro actualizada
     */
    @Transactional
    public InsurancePoliciesModel updateInsurancePolicy(int policyId, InsurancePoliciesModel policyDetails) {
        InsurancePoliciesModel existingPolicy = insurancePoliciesRepository.findById(policyId)
                .orElseThrow(() -> new RuntimeException("Policy not found with id " + policyId));

        existingPolicy.setPolicyStatus(policyDetails.getPolicyStatus());
        existingPolicy.setExpirationDate(policyDetails.getExpirationDate());
        return insurancePoliciesRepository.save(existingPolicy);
    }

    /**
     * Obtiene todas las pólizas de seguro de un cliente específico.
     *
     * @param customerId el ID del cliente
     * @return una lista de pólizas de seguro asociadas al cliente
     */
    public List<InsurancePoliciesModel> getPoliciesByCustomerId(int customerId) {
        return insurancePoliciesRepository.findByCustomerId(customerId);
    }

}
