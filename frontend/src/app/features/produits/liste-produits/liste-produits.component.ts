import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ProduitService } from '../../../core/services/produit.service';
import { Produit, Categorie } from '../../../core/models/produit.model';
import { ErrorResponse } from '../../../core/models/error-response.model';
import { LoaderComponent } from '../../../shared/components/loader/loader.component';
import { ErrorMessageComponent } from '../../../shared/components/error-message/error-message.component';
import { CategoryFilterPipe } from '../../../shared/pipes/category-filter.pipe';

@Component({
  selector: 'app-liste-produits',
  standalone: true,
  imports: [CommonModule, LoaderComponent, ErrorMessageComponent, CategoryFilterPipe],
  templateUrl: './liste-produits.component.html'
})
export class ListeProduitsComponent implements OnInit {
  produitsByCategorie: any = { produitsByCategorie: {} };
  loading = true;
  error: ErrorResponse | null = null;
  categories = Categorie;

  constructor(
    private produitService: ProduitService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadProduits();
  }

  loadProduits(): void {
    this.loading = true;
    this.error = null;

    this.produitService.getProduitsByCategorie().subscribe({
      next: (data) => {
        this.produitsByCategorie = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = err;
        this.loading = false;
      }
    });
  }

  viewDetails(id: number): void {
    this.router.navigate(['/produits', id]);
  }

  commander(id: number): void {
    this.router.navigate(['/commander', id]);
  }

  getCategorieDisplayName(categorie: Categorie): string {
    return this.produitService.getCategorieDisplayName(categorie);
  }
}