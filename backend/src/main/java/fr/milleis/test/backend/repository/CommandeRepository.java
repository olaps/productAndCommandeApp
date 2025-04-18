package fr.milleis.test.backend.repository;

import fr.milleis.test.backend.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Order entities
 */
@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    /**
     * Find all orders for a specific product
     * @param produitId the product ID
     * @return List of orders for the product
     */
    List<Commande> findByProduitId(Long produitId);
}