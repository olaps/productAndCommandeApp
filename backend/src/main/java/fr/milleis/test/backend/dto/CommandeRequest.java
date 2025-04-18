package fr.milleis.test.backend.dto;

import fr.milleis.test.backend.model.ModePaiement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

/**
 * DTO for creating an order
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeRequest {

    @NotNull(message = "Product ID cannot be null")
    private Long produitId;

    @NotNull(message = "Quantity cannot be null")
    @Positive(message = "Quantity must be positive")
    private Integer quantite;

    @NotNull(message = "Desired delivery date cannot be null")
    private LocalDate dateLivraisonSouhaitee;

    @NotBlank(message = "Delivery address cannot be empty")
    private String adresseLivraison;

    @NotNull(message = "Payment mode cannot be null")
    private ModePaiement modePaiement;
}