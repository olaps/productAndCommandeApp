import { Pipe, PipeTransform } from '@angular/core';
import { Produit, Categorie } from '../../core/models/produit.model';

@Pipe({
  name: 'categoryFilter',
  standalone: true
})
export class CategoryFilterPipe implements PipeTransform {

  transform(produits: Produit[], categorie: Categorie | null = null): Produit[] {
    if (!produits || produits.length === 0) {
      return [];
    }
    
    if (!categorie) {
      return produits;
    }
    
    return produits.filter(produit => produit.categorie === categorie);
  }

}