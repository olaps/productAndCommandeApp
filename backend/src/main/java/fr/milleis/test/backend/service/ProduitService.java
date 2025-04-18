package fr.milleis.test.backend.service;


import fr.milleis.test.backend.dto.ErrorResponse;
import fr.milleis.test.backend.exception.BusinessException;
import fr.milleis.test.backend.model.Categorie;
import fr.milleis.test.backend.model.Produit;
import fr.milleis.test.backend.repository.ProduitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service for product management
 */
@Service
@RequiredArgsConstructor
public class ProduitService {

    private final ProduitRepository produitRepository;

    /**
     * Get all products
     * @return List of all products
     */
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    /**
     * Get product by ID
     * @param id Product ID
     * @return Product with the given ID
     * @throws EntityNotFoundException if product not found
     */
    public Produit getProduitById(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    /**
     * Get products grouped by category
     * @return Map of categories to lists of products
     */
    public Map<Categorie, List<Produit>> getProduitsByCategorie() {
        List<Produit> produits = produitRepository.findAllOrderByCategorie();
        return produits.stream()
                .collect(Collectors.groupingBy(Produit::getCategorie));
    }

    /**
     * Create a new product
     * @param produit Product to create
     * @return Created product
     * @throws BusinessException if product reference already exists
     */
    @Transactional
    public Produit createProduit(Produit produit) {
        // Check if reference exists
        if (produitRepository.findByReference(produit.getReference()).isPresent()) {
            throw new BusinessException(
                    "Product with reference " + produit.getReference() + " already exists",
                    ErrorResponse.generateErrorCode("PROD", 400001)
            );
        }

        // Validate fields based on category
        validateProduitFields(produit);

        return produitRepository.save(produit);
    }

    /**
     * Update an existing product
     * @param id Product ID
     * @param produitDetails Updated product details
     * @return Updated product
     * @throws EntityNotFoundException if product not found
     * @throws BusinessException if validation fails
     */
    @Transactional
    public Produit updateProduit(Long id, Produit produitDetails) {
        Produit produit = getProduitById(id);

        // Update fields
        produit.setNom(produitDetails.getNom());
        produit.setPrix(produitDetails.getPrix());

        // If category changed, validate fields
        if (produit.getCategorie() != produitDetails.getCategorie()) {
            produit.setCategorie(produitDetails.getCategorie());
            if (produit.getCategorie() == Categorie.STANDARD) {
                produit.setStock(produitDetails.getStock());
                produit.setDelaiLivraison(null);
            } else {
                produit.setDelaiLivraison(produitDetails.getDelaiLivraison());
                produit.setStock(null);
            }
        } else {
            // Update category specific fields
            if (produit.getCategorie() == Categorie.STANDARD) {
                produit.setStock(produitDetails.getStock());
            } else {
                produit.setDelaiLivraison(produitDetails.getDelaiLivraison());
            }
        }

        // Re-validate all fields
        validateProduitFields(produit);

        return produitRepository.save(produit);
    }

    /**
     * Delete a product
     * @param id Product ID
     * @throws EntityNotFoundException if product not found
     */
    @Transactional
    public void deleteProduit(Long id) {
        if (!produitRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
        produitRepository.deleteById(id);
    }

    /**
     * Update product stock
     * @param produitId Product ID
     * @param quantity Quantity to remove from stock
     * @throws BusinessException if insufficient stock
     */
    @Transactional
    public void updateStock(Long produitId, int quantity) {
        Produit produit = getProduitById(produitId);

        if (produit.getCategorie() != Categorie.STANDARD) {
            throw new BusinessException(
                    "Cannot update stock for a special order product",
                    ErrorResponse.generateErrorCode("PROD", 400002)
            );
        }

        if (produit.getStock() < quantity) {
            throw new BusinessException(
                    "Insufficient stock for product: " + produit.getNom(),
                    ErrorResponse.generateErrorCode("PROD", 400003)
            );
        }

        produit.setStock(produit.getStock() - quantity);
        produitRepository.save(produit);
    }

    /**
     * Validate product fields based on category
     * @param produit Product to validate
     * @throws BusinessException if validation fails
     */
    private void validateProduitFields(Produit produit) {
        if (produit.getCategorie() == Categorie.STANDARD) {
            if (produit.getStock() == null) {
                throw new BusinessException(
                        "Stock must be specified for standard products",
                        ErrorResponse.generateErrorCode("PROD", 400004)
                );
            }
            // For standard products, delivery delay should be null
            produit.setDelaiLivraison(null);
        } else {
            if (produit.getDelaiLivraison() == null) {
                throw new BusinessException(
                        "Delivery delay must be specified for special order products",
                        ErrorResponse.generateErrorCode("PROD", 400005)
                );
            }
            // For special order products, stock should be null
            produit.setStock(null);
        }
    }
}