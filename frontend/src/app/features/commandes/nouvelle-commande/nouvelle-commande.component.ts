import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ProduitService } from '../../../core/services/produit.service';
import { CommandeService } from '../../../core/services/commande.service';
import { Produit } from '../../../core/models/produit.model';
import { ModePaiement, CommandeRequest } from '../../../core/models/commande.model';
import { ErrorResponse } from '../../../core/models/error-response.model';
import { LoaderComponent } from '../../../shared/components/loader/loader.component';
import { ErrorMessageComponent } from '../../../shared/components/error-message/error-message.component';

@Component({
  selector: 'app-nouvelle-commande',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink, LoaderComponent, ErrorMessageComponent],
  templateUrl: './nouvelle-commande.component.html'
})
export class NouvelleCommandeComponent implements OnInit {
  produit: Produit | null = null;
  commandeForm: FormGroup;
  loading = true;
  submitting = false;
  error: ErrorResponse | null = null;
  modesPaiement = Object.values(ModePaiement);
  minDeliveryDate: Date = new Date();
  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private produitService: ProduitService,
    private commandeService: CommandeService
  ) {
    this.commandeForm = this.formBuilder.group({
      quantite: [1, [Validators.required, Validators.min(1)]],
      dateLivraisonSouhaitee: ['', Validators.required],
      adresseLivraison: ['', Validators.required],
      modePaiement: [ModePaiement.COMPTANT, Validators.required]
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const produitId = params.get('produitId');
      if (produitId) {
        this.loadProduit(Number(produitId));
      } else {
        this.error = {
          code: 'NAV001',
          message: 'Identifiant de produit non trouvé'
        };
        this.loading = false;
      }
    });
  }

  loadProduit(produitId: number): void {
    this.loading = true;
    this.error = null;

    this.produitService.getProduitById(produitId).subscribe({
      next: (produit) => {
        this.produit = produit;
        this.loading = false;
        
        // Calculer la date minimale de livraison basée sur la catégorie du produit
        const delai = produit.categorie === 'SUR_COMMANDE' && produit.delaiLivraison !== undefined 
          ? produit.delaiLivraison 
          : 2;
        
        this.minDeliveryDate = this.commandeService.calculateEarliestDeliveryDate(delai);
        
        // Mettre à jour le champ de date avec la date minimale
        this.commandeForm.patchValue({
          dateLivraisonSouhaitee: this.formatDate(this.minDeliveryDate)
        });
        
        // Si c'est un produit standard, limiter la quantité au stock disponible
        if (produit.categorie === 'STANDARD' && produit.stock !== undefined) {
          const quantiteControl = this.commandeForm.get('quantite');
          if (quantiteControl && produit.stock > 0) {
            quantiteControl.setValidators([
              Validators.required, 
              Validators.min(1), 
              Validators.max(produit.stock)
            ]);
            quantiteControl.updateValueAndValidity();
          }
        }
      },
      error: (err) => {
        this.error = err;
        this.loading = false;
      }
    });
  }

  onSubmit(): void {
    if (this.commandeForm.invalid || !this.produit || !this.produit.id) {
      return;
    }

    this.submitting = true;
    this.error = null;

    const commandeRequest: CommandeRequest = {
      produitId: this.produit.id,
      quantite: this.commandeForm.value.quantite,
      dateLivraisonSouhaitee: this.commandeForm.value.dateLivraisonSouhaitee,
      adresseLivraison: this.commandeForm.value.adresseLivraison,
      modePaiement: this.commandeForm.value.modePaiement
    };

    this.commandeService.createCommande(commandeRequest).subscribe({
      next: (commande) => {
        this.submitting = false;
        // Naviguer vers la page de confirmation avec les données de la commande
        this.router.navigate(['/confirmation'], { 
          state: { commande: commande }
        });
      },
      error: (err) => {
        this.error = err;
        this.submitting = false;
      }
    });
  }

  getModePaiementDisplayName(modePaiement: string): string {
    return this.commandeService.getModePaiementDisplayName(modePaiement);
  }

  // Formater la date au format YYYY-MM-DD pour l'input date HTML
  formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  }
}