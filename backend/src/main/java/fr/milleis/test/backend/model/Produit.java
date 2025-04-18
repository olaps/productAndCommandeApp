package fr.milleis.test.backend.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Entity representing a product in the system
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name cannot be empty")
    private String nom;

    @NotBlank(message = "Product reference cannot be empty")
    @Column(unique = true)
    private String reference;

    @NotNull(message = "Product category cannot be null")
    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    @NotNull(message = "Product price cannot be null")
    @Positive(message = "Product price must be positive")
    private BigDecimal prix;

    // Only for STANDARD products
    private Integer stock;

    // Only for SUR_COMMANDE products
    private Integer delaiLivraison;
}