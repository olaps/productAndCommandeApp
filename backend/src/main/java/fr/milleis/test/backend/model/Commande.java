package fr.milleis.test.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entity representing an order in the system
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    @NotNull(message = "Quantity cannot be null")
    @Positive(message = "Quantity must be positive")
    private Integer quantite;

    @NotNull(message = "Desired delivery date cannot be null")
    private LocalDate dateLivraisonSouhaitee;

    @NotBlank(message = "Delivery address cannot be empty")
    private String adresseLivraison;

    @NotNull(message = "Payment mode cannot be null")
    @Enumerated(EnumType.STRING)
    private ModePaiement modePaiement;

    private LocalDate dateCreation = LocalDate.now();
}