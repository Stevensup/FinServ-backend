package com.edu.unbosque.Digital.FinServ.Controller;

import com.edu.unbosque.Digital.FinServ.Model.NotificationsModel;
import com.edu.unbosque.Digital.FinServ.Service.NotificationsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Transactional
@CrossOrigin(
    origins = { "http://localhost:8090", "http://localhost:8080", "*" }
)
@RestController
@RequestMapping("/Notifications")
public class NotificationsController{
    
    @Autowired
    private NotificationsService notificationsService;

    @PostMapping("create")
    @Operation(summary = "Create a new notifications", description = "Create a new notifications")
    @ApiResponse(
            responseCode = "200", description = "Notifications created"
            responseCode = "400", description = "Invalid input"
    )

    public NotificationsModel createNotifications(@RequestBody NotificationsModel notifications){
        return notificationsService.createNotifications(notifications);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a notifications by ID", description = "Get a notifications by ID")
    @ApiResponse(
        responseCode = "200", description = "Notifications found"
        responseCode = "400", description = "Invalid input"
    )

    public Optional<NotificationsModel> getNotificationsById(@PathVariable int id){
        return Optional.ofNullable(notificationsService.getNotificationsById(id));
    }

    @GetMapping("/all")
    @Operation(summary = "Get all notifications", description = "Get all notifications")
    @ApiResponse(
        responseCode = "200", description = "Notifications retrieved"
        responseCode = "400", description = "Invalid input"
    )

    public Iterable<NotificationsModel> getAllNotifications(){
        return notificationsService.getAllNotifications();
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a notifications", description = "Update a notifications")
    @ApiResponse(
        responseCode = "200", description = "Notifications updated"
        responseCode = "400", description = "Invalid input"
    )

    public NotificationsModel updateNotifications(@PathVariable int id, @RequestBody NotificationsModel notifications){
        return notificationsService.updateNotifications(id, notifications);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a notifications", description = "Delete a notifications")
    @ApiResponse(
        responseCode = "200", description = "Notifications deleted"
        responseCode = "400", description = "Invalid input"
    )
    public void deleteNotifications(@PathVariable int id){
        notificationsService.deleteNotifications(id);
    }
}
