package com.edu.unbosque.Digital.FinServ.Controller;

import com.edu.unbosque.Digital.FinServ.Model.PqrsModel;
import com.edu.unbosque.Digital.FinServ.Service.PqrsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/pqrs")
public class PqrsController {

    @Autowired
    private PqrsService pqrsService;

    /**
     * Create a new PQRS.
     *
     * @param pqrs data needed to create a new PQRS
     * @return the created PQRS
     */
    @PostMapping("/create")
    @Operation(summary = "Create a new PQRS", description = "Create a new PQRS")
    @ApiResponse(
            responseCode = "200", description = "PQRS created"
    )
    public ResponseEntity<?> createPqrs(@RequestBody PqrsModel pqrs) {
        try {
            PqrsModel createdPqrs = pqrsService.createPqrs(pqrs);
            return ResponseEntity.ok(createdPqrs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear PQRS: " + e.getMessage());
        }
    }

    /**
     * Get a PQRS by ID.
     *
     * @param id the ID of the PQRS
     * @return the PQRS found
     */
    @GetMapping("/findByCustomer/{customerId}")
    @Operation(summary = "Get all PQRS by Customer ID", description = "Get all PQRS entries associated with a customer")
    @ApiResponse(
            responseCode = "200", description = "PQRS entries found"
    )
    public ResponseEntity<?> getPqrsByCustomerId(@PathVariable int customerId) {
        List<PqrsModel> pqrsList = pqrsService.getPqrsByCustomerId(customerId);
        if (!pqrsList.isEmpty()) {
            return ResponseEntity.ok(pqrsList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron PQRS asociados al cliente con ID: " + customerId);
        }
    }


    /**
     * Get a PQRS by ID.
     *
     * @param id the ID of the PQRS
     * @return the PQRS found
     */
    @PutMapping("/update/{id}")
    @Operation(summary = "Update a PQRS", description = "Update a PQRS")
    @ApiResponse(
            responseCode = "200", description = "PQRS updated"
    )
    public ResponseEntity<?> updatePqrs(@PathVariable int id, @RequestBody PqrsModel pqrs) {
        try {
            PqrsModel updatedPqrs = pqrsService.updatePqrs(id, pqrs);
            return ResponseEntity.ok(updatedPqrs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al actualizar PQRS: " + e.getMessage());
        }
    }

    /**
     * Get a PQRS by ID.
     *
     * @param id the ID of the PQRS
     * @return the PQRS found
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a PQRS", description = "Delete a PQRS")
    @ApiResponse(
                responseCode = "200", description = "PQRS deleted"
    )
    public ResponseEntity<?> deletePqrs(@PathVariable int id) {
        try {
            pqrsService.deletePqrs(id);
            return ResponseEntity.ok("PQRS eliminada con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al eliminar PQRS: " + e.getMessage());
        }
    }
}
