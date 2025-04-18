import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ProduitService } from '../../../core/services/produit.service';
import { Produit } from '../../../core/models/produit.model';
import { ErrorResponse } from '../../../core/models/error-response.model';
import { LoaderComponent } from '../../../shared/components/loader/loader.component';
import { ErrorMessageComponent } from '../../../shared/components/error-message/error-message.component';

@Component({
  selector: 'app-detail-produit',
  standalone: true,
  imports: [CommonModule, RouterLink, LoaderComponent, ErrorMessageComponent],
  templateUrl: './detail-produit.component.html'
})
export class DetailProduitComponent implements OnInit {
  produit: Produit | null = null;
  loading = true;
  error: ErrorResponse | null = null;
  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private produitService: ProduitService
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const produitId = params.get('id');
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
      },
      error: (err) => {
        this.error = err;
        this.loading = false;
      }
    });
  }

  commander(): void {
    if (this.produit) {
      this.router.navigate(['/commander', this.produit.id]);
    }
  }

  retourListe(): void {
    this.router.navigate(['/produits']);
  }

  getCategorieDisplayName(categorie: string): string {
    return this.produitService.getCategorieDisplayName(categorie as any);
  }
}