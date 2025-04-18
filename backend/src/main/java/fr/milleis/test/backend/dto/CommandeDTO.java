package fr.milleis.test.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.milleis.test.backend.model.ModePaiement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO for Order data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommandeDTO {
    private Long id;
    private ProduitDTO produit;
    private Integer quantite;
    private LocalDate dateLivraisonSouhaitee;
    private String adresseLivraison;
    private ModePaiement modePaiement;
    private LocalDate dateCreation;
}