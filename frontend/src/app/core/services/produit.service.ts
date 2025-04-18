import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Produit, ProduitsByCategorie, Categorie } from '../models/produit.model';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProduitService {
  private apiUrl = `${environment.apiUrl}/produits`;

  constructor(private http: HttpClient) { }

  /**
   * Get all products
   * @returns Observable of product list
   */
  getAllProduits(): Observable<Produit[]> {
    return this.http.get<Produit[]>(this.apiUrl);
  }

  /**
   * Get product by ID
   * @param id Product ID
   * @returns Observable of product
   */
  getProduitById(id: number): Observable<Produit> {
    return this.http.get<Produit>(`${this.apiUrl}/${id}`);
  }

  /**
   * Get products grouped by category
   * @returns Observable of products by category
   */
  getProduitsByCategorie(): Observable<ProduitsByCategorie> {
    return this.http.get<ProduitsByCategorie>(`${this.apiUrl}/grouped-by-categorie`);
  }

  /**
   * Create a new product
   * @param produit Product data
   * @returns Observable of created product
   */
  createProduit(produit: Produit): Observable<Produit> {
    return this.http.post<Produit>(this.apiUrl, produit);
  }

  /**
   * Update an existing product
   * @param id Product ID
   * @param produit Updated product data
   * @returns Observable of updated product
   */
  updateProduit(id: number, produit: Produit): Observable<Produit> {
    return this.http.put<Produit>(`${this.apiUrl}/${id}`, produit);
  }

  /**
   * Delete a product
   * @param id Product ID
   * @returns Observable of void
   */
  deleteProduit(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  /**
   * Get category display name
   * @param categorie Category enum value
   * @returns Display name in French
   */
  getCategorieDisplayName(categorie: string): string {
    switch (categorie) {
      case 'STANDARD':
        return 'Produit standard';
      case 'SUR_COMMANDE':
        return 'Produit sur commande';
      default:
        return 'Inconnu';
    }
  }
}