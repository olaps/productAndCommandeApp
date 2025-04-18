package fr.milleis.test.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.milleis.test.backend.model.Categorie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO for Product data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProduitDTO {
    private Long id;
    private String nom;
    private String reference;
    private Categorie categorie;
    private BigDecimal prix;
    private Integer stock;
    private Integer delaiLivraison;
}