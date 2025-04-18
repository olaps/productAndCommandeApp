package fr.milleis.test.backend.repository;

import fr.milleis.test.backend.model.Categorie;
import fr.milleis.test.backend.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Product entities
 */
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    /**
     * Find a product by its reference
     * @param reference the product reference
     * @return Optional containing the product if found
     */
    Optional<Produit> findByReference(String reference);

    /**
     * Find all products of a specific category
     * @param categorie the product category
     * @return List of products belonging to the category
     */
    List<Produit> findByCategorie(Categorie categorie);

    /**
     * Query to find products grouped by category
     * @return Map of categories to lists of products
     */
    @Query("SELECT p FROM Produit p ORDER BY p.categorie")
    List<Produit> findAllOrderByCategorie();
}