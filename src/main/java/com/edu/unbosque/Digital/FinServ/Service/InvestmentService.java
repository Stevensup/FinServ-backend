package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.*;
import com.edu.unbosque.Digital.FinServ.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service
public class InvestmentService {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private Financial_ProductsRepository financialProductsRepository;

    @Autowired
    private Customer_ProductsRepository customerProductsRepository;

    @Autowired
    private Product_TypesRepository productTypesRepository;

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Crea una nueva inversión para un cliente, asegurando la relación con los productos financieros y customer_products.
     *
     * @param investment El modelo de inversión a crear
     * @param productTypeName El nombre del tipo de producto de inversión
     * @return La inversión creada
     */
    @Transactional
    public InvestmentModel createInvestment(InvestmentModel investment, String productTypeName) {
        try {
            if (!customerRepository.existsById(investment.getCustomerId())) {
                throw new RuntimeException("Cliente con ID " + investment.getCustomerId() + " no existe.");
            }


            Product_TypesModel productType = productTypesRepository.findByTypeName(productTypeName)
                    .orElseThrow(() -> new RuntimeException("Tipo de producto '" + productTypeName + "' no encontrado"));


            Financial_ProductsModel financialProduct = new Financial_ProductsModel();
            financialProduct.setProductName(productTypeName);
            financialProduct.setProductType(productType);
            financialProduct.setDescription(productTypeName);
            financialProduct.setInterestRate(2.5);
            financialProduct.setCreditLimit(0.0);
            Financial_ProductsModel savedFinancialProduct = financialProductsRepository.save(financialProduct);


            Customer_ProductsModel customerProduct = new Customer_ProductsModel();
            customerProduct.setCustomerId(investment.getCustomerId());
            customerProduct.setProductId(savedFinancialProduct.getProductId());
            customerProduct.setAcquisitionDate(new Date());
            customerProduct.setProductStatus(Customer_ProductsModel.ProductStatus.ACTIVE);
            customerProductsRepository.save(customerProduct);


            investment.setProductId(savedFinancialProduct.getProductId());


            investment.setStartDate(new Date());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 1);
            investment.setEndDate(calendar.getTime());
            investment.setStatus(InvestmentModel.InvestmentStatus.Activo);

            return investmentRepository.save(investment);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creando la inversión: " + e.getMessage());
        }
    }

    /**
     * Elimina una inversión y gestiona las relaciones en customer_products y financial_products.
     * Si no quedan otras inversiones para el mismo producto y cliente, elimina la relación en customer_products.
     * Si el producto financiero no está asociado a ningún otro cliente, también lo elimina de financial_products.
     *
     * @param investmentId El ID de la inversión a eliminar
     */
    @Transactional
    public void deleteInvestment(int investmentId) {
        InvestmentModel investment = investmentRepository.findById(investmentId)
                .orElseThrow(() -> new RuntimeException("Inversión no encontrada con id " + investmentId));


        investmentRepository.delete(investment);


        List<InvestmentModel> remainingInvestments = investmentRepository.findByCustomerIdAndProductId(
                investment.getCustomerId(), investment.getProductId());
        if (remainingInvestments.isEmpty()) {

            Customer_ProductsModel customerProduct = customerProductsRepository.findByCustomerIdAndProductId(
                    investment.getCustomerId(), investment.getProductId()).orElse(null);
            if (customerProduct != null) {
                customerProductsRepository.delete(customerProduct);
            }


            List<Customer_ProductsModel> otherCustomerProducts = customerProductsRepository.findByProductId(investment.getProductId());
            if (otherCustomerProducts.isEmpty()) {

                Financial_ProductsModel financialProduct = financialProductsRepository.findById(investment.getProductId())
                        .orElseThrow(() -> new RuntimeException("Producto financiero no encontrado con id " + investment.getProductId()));
                financialProductsRepository.delete(financialProduct);
            }
        }
    }

    /**
     * Modifica varios campos de una inversión existente.
     *
     * @param investmentId El ID de la inversión a modificar
     * @param updates Un mapa con los campos a modificar y sus nuevos valores
     *               - "investmentAmount" (Double): el nuevo monto de la inversión
     *               - "endDate" (String en formato "yyyy-MM-dd"): la nueva fecha de vencimiento de la inversión
     *               - "status" (String): el nuevo estado de la inversión ("Activo", "Finalizado", "Cancelado")
     * @return La inversión actualizada
     * @throws RuntimeException si ocurre un error en la conversión de endDate o si el estado es inválido
     */
    @Transactional
    public InvestmentModel updateInvestment(int investmentId, Map<String, Object> updates) {
        InvestmentModel investment = investmentRepository.findById(investmentId)
                .orElseThrow(() -> new RuntimeException("Inversión no encontrada con id " + investmentId));


        if (updates.containsKey("investmentAmount")) {
            investment.setInvestmentAmount((Double) updates.get("investmentAmount"));
        }


        if (updates.containsKey("endDate")) {
            try {
                String endDateStr = (String) updates.get("endDate");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date endDate = dateFormat.parse(endDateStr);
                investment.setEndDate(endDate);
            } catch (Exception e) {
                throw new RuntimeException("Error al convertir endDate: " + e.getMessage());
            }
        }


        if (updates.containsKey("status")) {
            investment.setStatus(InvestmentModel.InvestmentStatus.valueOf((String) updates.get("status")));
        }

        return investmentRepository.save(investment);
    }


    /**
     * Obtiene todas las inversiones de un cliente específico.
     *
     * @param customerId El ID del cliente
     * @return Lista de inversiones del cliente
     */
    @Transactional(readOnly = true)
    public List<InvestmentModel> getInvestmentsByCustomerId(int customerId) {
        return investmentRepository.findByCustomerId(customerId);
    }
}
