package fr.milleis.test.backend.controller;

import fr.milleis.test.backend.model.Categorie;
import fr.milleis.test.backend.model.Produit;
import fr.milleis.test.backend.service.ProduitService;
import fr.milleis.test.backend.mapper.DTOMapper;
import fr.milleis.test.backend.dto.ProduitDTO;
import fr.milleis.test.backend.dto.ProduitsByCategorieDTO;
import fr.milleis.test.backend.model.Produit;
import fr.milleis.test.backend.service.ProduitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for product management
 */
@RestController
@RequestMapping("/api/produits")
@RequiredArgsConstructor
@Tag(name = "Produits", description = "API for product management")
public class ProduitController {

    private final ProduitService produitService;
    private final DTOMapper dtoMapper;

    /**
     * Get all products
     * @return List of all products as DTOs
     */
    @GetMapping
    @Operation(
            summary = "Get all products",
            description = "Returns a list of all products in the system"
    )
    public ResponseEntity<List<ProduitDTO>> getAllProduits() {
        List<Produit> produits = produitService.getAllProduits();
        return ResponseEntity.ok(dtoMapper.toDTOList(produits));
    }

    /**
     * Get product by ID
     * @param id Product ID
     * @return Product with the given ID as DTO
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Get product by ID",
            description = "Returns a product with the specified ID"
    )
    public ResponseEntity<ProduitDTO> getProduitById(@PathVariable Long id) {
        Produit produit = produitService.getProduitById(id);
        return ResponseEntity.ok(dtoMapper.toDTO(produit));
    }

    /**
     * Get products grouped by category
     * @return Map of categories to lists of products as DTO
     */
    @GetMapping("/grouped-by-categorie")
    @Operation(
            summary = "Get products grouped by category",
            description = "Returns products organized by their categories"
    )
    public ResponseEntity<ProduitsByCategorieDTO> getProduitsByCategorie() {
        return ResponseEntity.ok(dtoMapper.toCategoriesDTO(produitService.getProduitsByCategorie()));
    }

    /**
     * Create a new product
     * @param produitDTO Product DTO to create
     * @return Created product as DTO
     */
    @PostMapping
    @Operation(
            summary = "Create a new product",
            description = "Creates a new product in the system"
    )
    public ResponseEntity<ProduitDTO> createProduit(@Valid @RequestBody ProduitDTO produitDTO) {
        Produit produit = dtoMapper.toEntity(produitDTO);
        produit = produitService.createProduit(produit);
        return new ResponseEntity<>(dtoMapper.toDTO(produit), HttpStatus.CREATED);
    }

    /**
     * Update an existing product
     * @param id Product ID
     * @param produitDTO Updated product DTO
     * @return Updated product as DTO
     */
    @PutMapping("/{id}")
    @Operation(
            summary = "Update an existing product",
            description = "Updates the product with the specified ID"
    )
    public ResponseEntity<ProduitDTO> updateProduit(@PathVariable Long id, @Valid @RequestBody ProduitDTO produitDTO) {
        Produit produit = dtoMapper.toEntity(produitDTO);
        produit = produitService.updateProduit(id, produit);
        return ResponseEntity.ok(dtoMapper.toDTO(produit));
    }

    /**
     * Delete a product
     * @param id Product ID
     * @return No content response
     */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a product",
            description = "Deletes the product with the specified ID"
    )
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }
}