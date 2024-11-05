package com.edu.unbosque.Digital.FinServ.Controller;

import com.edu.unbosque.Digital.FinServ.Model.InsurancePoliciesModel;
import com.edu.unbosque.Digital.FinServ.Service.InsurancePoliciesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/insurancePolicies")
public class InsurancePoliciesController {

    @Autowired
    private InsurancePoliciesService insurancePoliciesService;

    @PostMapping("/create")
    @Operation(summary = "Create a new Insurance Policy", description = "Create a new insurance policy for a customer")
    @ApiResponse(responseCode = "200", description = "Insurance policy created")
    public ResponseEntity<?> createInsurancePolicy(@RequestBody Map<String, Object> payload) {
        try {
            int customerId = (int) payload.get("customerId");
            int productId = (int) payload.get("productId");
            String productTypeName = (String) payload.get("productTypeName");

            InsurancePoliciesModel insurancePolicy = new InsurancePoliciesModel();
            insurancePolicy.setCustomerId(customerId);
            insurancePolicy.setProductId(productId);

            InsurancePoliciesModel createdPolicy = insurancePoliciesService.createInsurancePolicy(insurancePolicy, productTypeName);
            return ResponseEntity.ok(createdPolicy);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating insurance policy: " + e.getMessage());
        }
    }

    @GetMapping("/findByCustomer/{customerId}")
    @Operation(summary = "Get Insurance Policies by Customer ID", description = "Get all insurance policies associated with a customer")
    @ApiResponse(responseCode = "200", description = "Insurance policies retrieved")
    public ResponseEntity<?> getPoliciesByCustomerId(@PathVariable int customerId) {
        List<InsurancePoliciesModel> policies = insurancePoliciesService.getPoliciesByCustomerId(customerId);
        return policies.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No policies found for customer ID: " + customerId) :
                ResponseEntity.ok(policies);
    }

    @PutMapping("/update/{policyId}")
    @Operation(summary = "Update an Insurance Policy", description = "Update an existing insurance policy")
    @ApiResponse(responseCode = "200", description = "Insurance policy updated")
    public ResponseEntity<?> updateInsurancePolicy(@PathVariable int policyId, @RequestBody InsurancePoliciesModel policyDetails) {
        try {
            InsurancePoliciesModel updatedPolicy = insurancePoliciesService.updateInsurancePolicy(policyId, policyDetails);
            return ResponseEntity.ok(updatedPolicy);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error updating policy: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{policyId}")
    @Operation(summary = "Delete an Insurance Policy", description = "Delete an existing insurance policy")
    @ApiResponse(responseCode = "200", description = "Insurance policy deleted")
    public ResponseEntity<?> deleteInsurancePolicy(@PathVariable int policyId) {
        try {
            insurancePoliciesService.deleteInsurancePolicy(policyId);
            return ResponseEntity.ok("Policy deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error deleting policy: " + e.getMessage());
        }
    }
}