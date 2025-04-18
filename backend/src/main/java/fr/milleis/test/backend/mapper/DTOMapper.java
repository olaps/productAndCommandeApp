package fr.milleis.test.backend.mapper;

import fr.milleis.test.backend.dto.CommandeDTO;
import fr.milleis.test.backend.dto.ProduitDTO;
import fr.milleis.test.backend.dto.ProduitsByCategorieDTO;
import fr.milleis.test.backend.model.Categorie;
import fr.milleis.test.backend.model.Commande;
import fr.milleis.test.backend.model.Produit;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Mapper for converting between entities and DTOs
 */
@Component
public class DTOMapper {

    /**
     * Convert Product entity to DTO
     * @param produit Product entity
     * @return Product DTO
     */
    public ProduitDTO toDTO(Produit produit) {
        if (produit == null) {
            return null;
        }

        ProduitDTO dto = new ProduitDTO();
        dto.setId(produit.getId());
        dto.setNom(produit.getNom());
        dto.setReference(produit.getReference());
        dto.setCategorie(produit.getCategorie());
        dto.setPrix(produit.getPrix());
        dto.setStock(produit.getStock());
        dto.setDelaiLivraison(produit.getDelaiLivraison());

        return dto;
    }

    /**
     * Convert Product DTO to entity
     * @param dto Product DTO
     * @return Product entity
     */
    public Produit toEntity(ProduitDTO dto) {
        if (dto == null) {
            return null;
        }

        Produit produit = new Produit();
        produit.setId(dto.getId());
        produit.setNom(dto.getNom());
        produit.setReference(dto.getReference());
        produit.setCategorie(dto.getCategorie());
        produit.setPrix(dto.getPrix());
        produit.setStock(dto.getStock());
        produit.setDelaiLivraison(dto.getDelaiLivraison());

        return produit;
    }

    /**
     * Convert Order entity to DTO
     * @param commande Order entity
     * @return Order DTO
     */
    public CommandeDTO toDTO(Commande commande) {
        if (commande == null) {
            return null;
        }

        CommandeDTO dto = new CommandeDTO();
        dto.setId(commande.getId());
        dto.setProduit(toDTO(commande.getProduit()));
        dto.setQuantite(commande.getQuantite());
        dto.setDateLivraisonSouhaitee(commande.getDateLivraisonSouhaitee());
        dto.setAdresseLivraison(commande.getAdresseLivraison());
        dto.setModePaiement(commande.getModePaiement());
        dto.setDateCreation(commande.getDateCreation());

        return dto;
    }

    /**
     * Convert a list of Product entities to DTOs
     * @param produits List of product entities
     * @return List of product DTOs
     */
    public List<ProduitDTO> toDTOList(List<Produit> produits) {
        return produits.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convert a list of Order entities to DTOs
     * @param commandes List of order entities
     * @return List of order DTOs
     */
    public List<CommandeDTO> toCommandeDTOList(List<Commande> commandes) {
        return commandes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convert a map of products by category to DTO
     * @param produitsByCategorie Map of products by category
     * @return DTO with products by category
     */
    public ProduitsByCategorieDTO toCategoriesDTO(Map<Categorie, List<Produit>> produitsByCategorie) {
        Map<Categorie, List<ProduitDTO>> dtoMap = produitsByCategorie.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> toDTOList(entry.getValue())
                ));

        return new ProduitsByCategorieDTO(dtoMap);
    }
}