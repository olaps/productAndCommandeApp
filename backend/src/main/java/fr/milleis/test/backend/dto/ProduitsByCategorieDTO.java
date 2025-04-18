package fr.milleis.test.backend.dto;

import fr.milleis.test.backend.model.Categorie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * DTO for products grouped by category
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduitsByCategorieDTO {
    private Map<Categorie, List<ProduitDTO>> produitsByCategorie;
}