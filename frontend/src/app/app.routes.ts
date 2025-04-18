import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: '/produits', pathMatch: 'full' },
  { 
    path: 'produits', 
    loadComponent: () => import('./features/produits/liste-produits/liste-produits.component').then(m => m.ListeProduitsComponent) 
  },
  { 
    path: 'produits/:id', 
    loadComponent: () => import('./features/produits/detail-produit/detail-produit.component').then(m => m.DetailProduitComponent) 
  },
  { 
    path: 'commander/:produitId', 
    loadComponent: () => import('./features/commandes/nouvelle-commande/nouvelle-commande.component').then(m => m.NouvelleCommandeComponent) 
  },
  { 
    path: 'confirmation', 
    loadComponent: () => import('./features/commandes/confirmation-commande/confirmation-commande.component').then(m => m.ConfirmationCommandeComponent) 
  },
  { path: '**', redirectTo: '/produits' }
];