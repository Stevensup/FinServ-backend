package com.edu.unbosque.Digital.FinServ.Controller;

import com.edu.unbosque.Digital.FinServ.Service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/correo")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Método para enviar un correo electrónico.
     *
     * @param json el contenido del correo electrónico en formato JSON
     * @return un mensaje indicando si el correo se envió exitosamente
     */
    @PostMapping("/enviar")
    @Operation(summary = "Enviar correo electrónico", description = "Envía un correo electrónico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Correo enviado exitosamente"),
            @ApiResponse(responseCode = "412", description = "Precondición fallida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<String> enviarCorreo(@RequestBody String json) {
        try {
            emailService.enviarCorreo(json);
            return ResponseEntity.ok("{\"message\":\"Correo enviado exitosamente\"}");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body("{\"message\":\"Precondición fallida: " + e.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\":\"Error interno del servidor: " + e.getMessage() + "\"}");
        }
    }
}
