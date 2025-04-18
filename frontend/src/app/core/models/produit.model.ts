/**
 * Enumeration representing product categories
 */
export enum Categorie {
    STANDARD = 'STANDARD',
    SUR_COMMANDE = 'SUR_COMMANDE'
  }
  
  /**
   * Interface for Product data structure
   */
  export interface Produit {
    id?: number;
    nom: string;
    reference: string;
    categorie: Categorie;
    prix: number;
    stock?: number;
    delaiLivraison?: number;
  }
  
  /**
   * Interface for products grouped by category
   */
  export interface ProduitsByCategorie {
    produitsByCategorie: {
      [key: string]: Produit[];
    }
  }