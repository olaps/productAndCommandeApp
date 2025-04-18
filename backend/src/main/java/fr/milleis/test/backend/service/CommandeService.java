package fr.milleis.test.backend.service;
import fr.milleis.test.backend.dto.CommandeRequest;
import fr.milleis.test.backend.dto.ErrorResponse;
import fr.milleis.test.backend.exception.BusinessException;
import fr.milleis.test.backend.model.Categorie;
import fr.milleis.test.backend.model.Commande;
import fr.milleis.test.backend.model.Produit;
import fr.milleis.test.backend.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Service for order management
 */
@Service
@RequiredArgsConstructor
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final ProduitService produitService;

    /**
     * Get all orders
     * @return List of all orders
     */
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    /**
     * Get order by ID
     * @param id Order ID
     * @return Order with the given ID
     */
    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        "Order not found with id: " + id,
                        ErrorResponse.generateErrorCode("CMD", 404001)
                ));
    }

    /**
     * Create a new order
     * @param commandeRequest Order request DTO
     * @return Created order
     */
    @Transactional
    public Commande createCommande(CommandeRequest commandeRequest) {
        // Get product by ID
        Produit produit = produitService.getProduitById(commandeRequest.getProduitId());

        // Validate order constraints based on product category
        validateOrderConstraints(produit, commandeRequest);

        // Create order entity
        Commande commande = new Commande();
        commande.setProduit(produit);
        commande.setQuantite(commandeRequest.getQuantite());
        commande.setDateLivraisonSouhaitee(commandeRequest.getDateLivraisonSouhaitee());
        commande.setAdresseLivraison(commandeRequest.getAdresseLivraison());
        commande.setModePaiement(commandeRequest.getModePaiement());
        commande.setDateCreation(LocalDate.now());

        // Update stock for standard products
        if (produit.getCategorie() == Categorie.STANDARD) {
            produitService.updateStock(produit.getId(), commandeRequest.getQuantite());
        }

        return commandeRepository.save(commande);
    }

    /**
     * Validate order constraints based on product category
     * @param produit Product being ordered
     * @param commandeRequest Order request details
     * @throws BusinessException if constraints are violated
     */
    private void validateOrderConstraints(Produit produit, CommandeRequest commandeRequest) {
        // Current date
        LocalDate now = LocalDate.now();

        // Common validation: Desired delivery date must be in the future
        if (commandeRequest.getDateLivraisonSouhaitee().isBefore(now)) {
            throw new BusinessException(
                    "Desired delivery date must be in the future",
                    ErrorResponse.generateErrorCode("CMD", 400001)
            );
        }

        // Category specific validations
        if (produit.getCategorie() == Categorie.STANDARD) {
            // For standard products, check stock availability
            if (produit.getStock() < commandeRequest.getQuantite()) {
                throw new BusinessException(
                        "Product unavailable: Insufficient stock for " + produit.getNom(),
                        ErrorResponse.generateErrorCode("CMD", 400002)
                );
            }

            // Standard products can be delivered in 2 business days
            LocalDate minDeliveryDate = now.plusDays(2);
            if (commandeRequest.getDateLivraisonSouhaitee().isBefore(minDeliveryDate)) {
                throw new BusinessException(
                        "Minimum delivery time for standard products is 2 days",
                        ErrorResponse.generateErrorCode("CMD", 400003)
                );
            }
        } else {
            // For special order products, check minimum delivery time
            int deliveryDays = produit.getDelaiLivraison();
            LocalDate minDeliveryDate = now.plusDays(deliveryDays);

            // Calculate days between now and desired delivery date
            long daysBetween = ChronoUnit.DAYS.between(now, commandeRequest.getDateLivraisonSouhaitee());

            if (daysBetween < deliveryDays) {
                throw new BusinessException(
                        "Impossible delivery time for product " + produit.getNom() + ". Minimum delivery time is " + deliveryDays + " days",
                        ErrorResponse.generateErrorCode("CMD", 400004)
                );
            }
        }
    }
}