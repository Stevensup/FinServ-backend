package com.edu.unbosque.Digital.FinServ.Controller;


import com.edu.unbosque.Digital.FinServ.Model.Notification_PreferencesModel;
import com.edu.unbosque.Digital.FinServ.Service.Notification_PreferencesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8080", "*" })
@RestController
@RequestMapping("/Notificacion_Preferences")
public class Notification_PreferencesController {

    @Autowired
    private Notification_PreferencesService notification_preferencesService;

    @PostMapping("/create")
    @Operation(summary = "Create a new notification_preferences", description = "Create a new notification_preferences")
    @ApiResponse(responseCode = "200", description = "Notification_Preferences created")
    public Notification_PreferencesModel createNotification_Preferences(@RequestBody Notification_PreferencesModel notification_preferences) {
        return notification_preferencesService.createNotification_Preferences(notification_preferences);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a notification_preferences by ID", description = "Get a notification_preferences by ID")
    @ApiResponse(responseCode = "200", description = "Notification_Preferences found")
    public Optional<Notification_PreferencesModel> getNotification_PreferencesById(@PathVariable int id) {
        return Optional.ofNullable(notification_preferencesService.getNotification_PreferencesById(id));
    }

    @GetMapping("/all")
    @Operation(summary = "Get all notification_preferences", description = "Get all notification_preferences")
    @ApiResponse(responseCode = "200", description = "Notification_Preferences retrieved")
    public Iterable<Notification_PreferencesModel> getAllNotification_Preferences() {
        return notification_preferencesService.getAllNotification_Preferences();
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a notification_preferences", description = "Update a notification_preferences")
    @ApiResponse(responseCode = "200", description = "Notification_Preferences updated")
    public Notification_PreferencesModel updateNotification_Preferences(@PathVariable int id, @RequestBody Notification_PreferencesModel notification_preferences) {
        return notification_preferencesService.updateNotification_Preferences(id, notification_preferences);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a notification_preferences", description = "Delete a notification_preferences")
    @ApiResponse(responseCode = "200", description = "Notification_Preferences deleted")
    public void deleteNotification_Preferences(@PathVariable int id) {
        notification_preferencesService.deleteNotification_Preferences(id);
    }








}
