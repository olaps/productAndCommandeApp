package fr.milleis.test.backend.controller;

import fr.milleis.test.backend.dto.CommandeRequest;
import fr.milleis.test.backend.model.Commande;
import fr.milleis.test.backend.service.CommandeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for order management
 */
@RestController
@RequestMapping("/api/commandes")
@RequiredArgsConstructor
@Tag(name = "Commandes", description = "API for order management")
public class CommandeController {

    private final CommandeService commandeService;

    /**
     * Get all orders
     * @return List of all orders
     */
    @GetMapping
    @Operation(
            summary = "Get all orders",
            description = "Returns a list of all orders in the system"
    )
    public ResponseEntity<List<Commande>> getAllCommandes() {
        return ResponseEntity.ok(commandeService.getAllCommandes());
    }

    /**
     * Get order by ID
     * @param id Order ID
     * @return Order with the given ID
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Get order by ID",
            description = "Returns an order with the specified ID"
    )
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.getCommandeById(id));
    }

    /**
     * Create a new order
     * @param commandeRequest Order request DTO
     * @return Created order
     */
    @PostMapping
    @Operation(
            summary = "Create a new order",
            description = "Creates a new order in the system with validation for delivery dates and stock availability"
    )
    public ResponseEntity<Commande> createCommande(@Valid @RequestBody CommandeRequest commandeRequest) {
        return new ResponseEntity<>(commandeService.createCommande(commandeRequest), HttpStatus.CREATED);
    }
}