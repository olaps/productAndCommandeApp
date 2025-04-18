import { Produit } from './produit.model';

/**
 * Enumeration representing payment modes
 */
export enum ModePaiement {
  COMPTANT = 'COMPTANT',
  DIFFERE = 'DIFFERE'
}

/**
 * Interface for Order data structure
 */
export interface Commande {
  id?: number;
  produit: Produit;
  quantite: number;
  dateLivraisonSouhaitee: Date;
  adresseLivraison: string;
  modePaiement: ModePaiement;
  dateCreation?: Date;
}

/**
 * Interface for Order DTO from API
 */
export interface CommandeDTO {
  id?: number;
  produit: Produit;
  quantite: number;
  dateLivraisonSouhaitee: string; // Format ISO (YYYY-MM-DD)
  adresseLivraison: string;
  modePaiement: ModePaiement;
  dateCreation?: string; // Format ISO (YYYY-MM-DD)
}

/**
 * Interface for Order Request DTO
 */
export interface CommandeRequest {
  produitId: number;
  quantite: number;
  dateLivraisonSouhaitee: string; // Format YYYY-MM-DD
  adresseLivraison: string;
  modePaiement: ModePaiement;
}