import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ProduitService } from '../../../core/services/produit.service';
import { CommandeService } from '../../../core/services/commande.service';

@Component({
  selector: 'app-confirmation-commande',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './confirmation-commande.component.html'
})
export class ConfirmationCommandeComponent implements OnInit {
  commande: any = null;
  
  constructor(
    private produitService: ProduitService,
    private commandeService: CommandeService
  ) { }

  ngOnInit(): void {
    const navigation = window.history.state;
    if (navigation && navigation.commande) {
      this.commande = navigation.commande;
    }
  }

  getCategorieDisplayName(categorie: string): string {
    if (!categorie) return 'Inconnu';
    return this.produitService.getCategorieDisplayName(categorie);
  }

  getModePaiementDisplayName(modePaiement: string): string {
    if (!modePaiement) return 'Inconnu';
    return this.commandeService.getModePaiementDisplayName(modePaiement);
  }
  
  calculateTotal(): number {
    if (!this.commande || !this.commande.produit || !this.commande.produit.prix || !this.commande.quantite) {
      return 0;
    }
    return this.commande.produit.prix * this.commande.quantite;
  }
}