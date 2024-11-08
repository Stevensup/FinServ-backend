package com.edu.unbosque.Digital.FinServ.Controller;


import com.edu.unbosque.Digital.FinServ.Model.NotificationPreferencesModel;
import com.edu.unbosque.Digital.FinServ.Service.NotificationPreferencesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Transactional
@CrossOrigin(origins = { "http://localhost:8090", "http://localhost:8082", "*" })
@RestController
@RequestMapping("/Notificacion_Preferences")
public class Notification_PreferencesController {

    @Autowired
    private NotificationPreferencesService notification_preferencesService;

    @PostMapping("/create")
    @Operation(summary = "Create a new notification_preferences", description = "Create a new notification_preferences")
    @ApiResponse(responseCode = "200", description = "Notification_Preferences created")
    public NotificationPreferencesModel createNotification_Preferences(@RequestBody NotificationPreferencesModel notification_preferences) {
        return notification_preferencesService.createNotification_Preferences(notification_preferences);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a notification_preferences by ID", description = "Get a notification_preferences by ID")
    @ApiResponse(responseCode = "200", description = "Notification_Preferences found")
    public Optional<NotificationPreferencesModel> getNotification_PreferencesById(@PathVariable int id) {
        return Optional.ofNullable(notification_preferencesService.getNotification_PreferencesById(id));
    }

    @GetMapping("/all")
    @Operation(summary = "Get all notification_preferences", description = "Get all notification_preferences")
    @ApiResponse(responseCode = "200", description = "Notification_Preferences retrieved")
    public Iterable<NotificationPreferencesModel> getAllNotification_Preferences() {
        return notification_preferencesService.getAllNotification_Preferences();
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a notification_preferences", description = "Update a notification_preferences")
    @ApiResponse(responseCode = "200", description = "Notification_Preferences updated")
    public NotificationPreferencesModel updateNotification_Preferences(@PathVariable int id, @RequestBody NotificationPreferencesModel notification_preferences) {
        return notification_preferencesService.updateNotification_Preferences(id, notification_preferences);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a notification_preferences", description = "Delete a notification_preferences")
    @ApiResponse(responseCode = "200", description = "Notification_Preferences deleted")
    public void deleteNotification_Preferences(@PathVariable int id) {
        notification_preferencesService.deleteNotification_Preferences(id);
    }








}
