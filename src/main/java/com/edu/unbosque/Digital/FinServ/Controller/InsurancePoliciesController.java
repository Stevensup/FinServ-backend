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

    /**
     * Create a new Insurance Policy.
     *
     * @param payload data needed to create a new Insurance Policy
     * @return the created Insurance Policy
     */

    @PostMapping("/create")
    @Operation(summary = "Create a new Insurance Policy", description = "Create a new insurance policy for a customer")
    @ApiResponse(
            responseCode = "200", description = "Insurance policy created"
            responseCode = "500", description = "Error creating insurance policy"
    )
    public ResponseEntity<?> createInsurancePolicy(@RequestBody Map<String, Object> payload) {
        try {
            int customerId = (int) payload.get("customerId");
            String productTypeName = (String) payload.get("productTypeName");

            InsurancePoliciesModel insurancePolicy = new InsurancePoliciesModel();
            insurancePolicy.setCustomerId(customerId);

            InsurancePoliciesModel createdPolicy = insurancePoliciesService.createInsurancePolicy(insurancePolicy, productTypeName);
            return ResponseEntity.ok(createdPolicy);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating insurance policy: " + e.getMessage());
        }
    }

    /**
     * Get an Insurance Policy by ID.
     *
     * @param policyId the ID of the Insurance Policy
     * @return the Insurance Policy found
     */
    @PutMapping("/updateStatus/{policyId}")
    @Operation(summary = "Update Policy Status", description = "Update the status of an existing insurance policy")
    @ApiResponse(
            responseCode = "200", description = "Policy status updated"
            responseCode = "400", description = "Error updating policy status"
    )
    public ResponseEntity<?> updatePolicyStatus(@PathVariable int policyId, @RequestBody Map<String, Object> policyDetails) {
        try {
            String policyStatus = (String) policyDetails.get("policyStatus");

            InsurancePoliciesModel updatedPolicy = insurancePoliciesService.updatePolicyStatus(policyId, policyStatus);
            return ResponseEntity.ok(updatedPolicy);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating policy status: " + e.getMessage());
        }
    }

    /**
     * Get an Insurance Policy by ID.
     *
     * @param policyId the ID of the Insurance Policy
     * @return the Insurance Policy found
     */
    @DeleteMapping("/delete/{policyId}")
    @Operation(summary = "Delete an Insurance Policy", description = "Delete an existing insurance policy")
    @ApiResponse(
            responseCode = "200", description = "Insurance policy deleted"
            responseCode = "404", description = "Error deleting insurance policy"
    )
    public ResponseEntity<?> deleteInsurancePolicy(@PathVariable int policyId) {
        try {
            insurancePoliciesService.deleteInsurancePolicy(policyId);
            return ResponseEntity.ok("Policy deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error deleting policy: " + e.getMessage());
        }
    }

    /**
     * Get an Insurance Policy by ID.
     *
     * @param policyId the ID of the Insurance Policy
     * @return the Insurance Policy found
     */
    @GetMapping("/detailsByCustomer/{customerId}")
    @Operation(summary = "Get Insurance Policy Details by Customer ID", description = "Get insurance policy details including product name, policy status, and expiration date")
    @ApiResponse(
            responseCode = "200", description = "Insurance policy details retrieved"
            responseCode = "404", description = "No policies found for customer ID"
    )
    public ResponseEntity<?> getPolicyDetailsByCustomerId(@PathVariable int customerId) {
        List<Map<String, Object>> policyDetails = insurancePoliciesService.getPolicyDetailsByCustomerId(customerId);
        return policyDetails.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No policies found for customer ID: " + customerId) :
                ResponseEntity.ok(policyDetails);
    }

}