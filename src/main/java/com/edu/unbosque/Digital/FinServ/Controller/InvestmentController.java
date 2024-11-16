package com.edu.unbosque.Digital.FinServ.Controller;

import com.edu.unbosque.Digital.FinServ.Model.InvestmentModel;
import com.edu.unbosque.Digital.FinServ.Service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/investments")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    /**
     * Crea una nueva inversión para un cliente específico.
     *
     * @param payload Un mapa que contiene el objeto de inversión y el nombre del tipo de producto
     * @return La inversión creada o un mensaje de error
     */
    @PostMapping("/create")
    @Operation(summary = "Create a new investment", description = "Create a new investment")
    @ApiResponse(responseCode = "200", description = "Investment created")
    public ResponseEntity<?> createInvestment(@RequestBody Map<String, Object> payload) {
        try {
            InvestmentModel investment = new InvestmentModel();
            investment.setCustomerId((Integer) payload.get("customerId"));

            // Convertir el investmentAmount a Double explícitamente
            Object investmentAmountObj = payload.get("investmentAmount");
            Double investmentAmount = investmentAmountObj instanceof Integer ?
                    ((Integer) investmentAmountObj).doubleValue() :
                    (Double) investmentAmountObj;
            investment.setInvestmentAmount(investmentAmount);

            String productTypeName = (String) payload.get("productTypeName");
            InvestmentModel createdInvestment = investmentService.createInvestment(investment, productTypeName);
            return ResponseEntity.ok(createdInvestment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating investment: " + e.getMessage());
        }
    }


    /**
     * Elimina una inversión existente, gestionando las relaciones correspondientes.
     *
     * @param investmentId El ID de la inversión a eliminar
     * @return Mensaje de éxito o error
     */
    @DeleteMapping("/delete/{investmentId}")
    @Operation(summary = "Delete an investment", description = "Delete an existing investment")
    @ApiResponse(responseCode = "200", description = "Investment deleted")
    public ResponseEntity<?> deleteInvestment(@PathVariable int investmentId) {
        try {
            investmentService.deleteInvestment(investmentId);
            return ResponseEntity.ok("Investment deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error deleting investment: " + e.getMessage());
        }
    }

    /**
     * Obtiene todas las inversiones de un cliente específico.
     *
     * @param customerId El ID del cliente
     * @return Lista de inversiones del cliente o un mensaje de error
     */
    @GetMapping("/byCustomer/{customerId}")
    @Operation(summary = "Get investments by customer ID", description = "Get investments by customer ID")
    @ApiResponse(responseCode = "200", description = "Investments retrieved")
    public ResponseEntity<?> getInvestmentsByCustomerId(@PathVariable int customerId) {
        try {
            List<InvestmentModel> investments = investmentService.getInvestmentsByCustomerId(customerId);
            return investments.isEmpty() ?
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("No investments found for customer ID: " + customerId) :
                    ResponseEntity.ok(investments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving investments: " + e.getMessage());
        }
    }

    /**
     * Modifica una inversión existente.
     *
     * @param investmentId El ID de la inversión a modificar
     * @param payload Un mapa que contiene los campos a modificar
     * @return La inversión actualizada o un mensaje de error
     */
    @PutMapping("/update/{investmentId}")
    @Operation(summary = "Update an investment", description = "Update an existing investment")
    @ApiResponse(responseCode = "200", description = "Investment updated")
    public ResponseEntity<?> updateInvestment(@PathVariable int investmentId, @RequestBody Map<String, Object> payload) {
        try {
            InvestmentModel updatedInvestment = investmentService.updateInvestment(investmentId, payload);
            return ResponseEntity.ok(updatedInvestment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error updating investment: " + e.getMessage());
        }
    }

}
