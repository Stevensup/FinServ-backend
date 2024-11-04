package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.*;
import com.edu.unbosque.Digital.FinServ.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
     * @return la póliza de seguro creada
     */
    @Transactional
    public InsurancePoliciesModel createInsurancePolicy(InsurancePoliciesModel insurancePolicy) {
        try {
            // Paso 1: Obtener el objeto Product_TypesModel para el tipo de producto "Seguros"
            Product_TypesModel productType = productTypesRepository.findByTypeName("Seguros")
                    .orElseThrow(() -> new RuntimeException("Tipo de producto 'Seguros' no encontrado"));

            // Paso 2: Crear el producto financiero asociado
            Financial_ProductsModel financialProduct = new Financial_ProductsModel();
            financialProduct.setProductName("Seguro " + insurancePolicy.getPolicyId()+1); // Nombre de ejemplo
            financialProduct.setProductType(productType); // Establecemos el objeto productType
            financialProduct.setDescription("Seguro bancario que brinda respaldo financiero ante imprevistos, cubriendo riesgos específicos como accidentes o daños personales, con opciones de cobertura adaptadas a las necesidades del cliente."); // Añade una descripción adecuada
            financialProduct.setInterestRate(0.0); // Ajusta según los requisitos
            financialProduct.setCreditLimit(0.0); // Ajusta según los requisitos
            Financial_ProductsModel savedFinancialProduct = financialProductsRepository.save(financialProduct);

            // Paso 3: Crear la relación en customer_products
            Customer_ProductsModel customerProduct = new Customer_ProductsModel();
            customerProduct.setCustomerId(insurancePolicy.getCustomerId());
            customerProduct.setProductId(savedFinancialProduct.getProductId());
            customerProduct.setAcquisitionDate(new Date());
            customerProduct.setProductStatus(Customer_ProductsModel.ProductStatus.valueOf("ACTIVE"));
            customerProductsRepository.save(customerProduct);

            // Paso 4: Crear y guardar la póliza en insurance_policies
            insurancePolicy.setProductId(savedFinancialProduct.getProductId());
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
        // Verificar si existe la póliza
        InsurancePoliciesModel insurancePolicy = insurancePoliciesRepository.findById(policyId)
                .orElseThrow(() -> new RuntimeException("Policy not found with id " + policyId));

        // Paso 1: Eliminar la póliza de seguro en insurance_policies
        insurancePoliciesRepository.delete(insurancePolicy);

        // Paso 2: Eliminar la relación en customer_products
        List<Customer_ProductsModel> customerProducts = customerProductsRepository.findByProductId(insurancePolicy.getProductId());
        for (Customer_ProductsModel customerProduct : customerProducts) {
            customerProductsRepository.delete(customerProduct);
        }

        // Paso 3: Eliminar el producto financiero asociado en financial_products
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
