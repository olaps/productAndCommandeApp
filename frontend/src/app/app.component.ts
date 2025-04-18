import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './shared/components/header/header.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, HeaderComponent],
  template: `
    <div class="min-h-screen bg-gray-50">
      <app-header></app-header>
      
      <main class="container mx-auto px-4 py-8">
        <router-outlet></router-outlet>
      </main>
      
      <footer class="bg-gray-800 text-white py-4 mt-8">
        <div class="container mx-auto px-4 text-center">
          <p>Application de Gestion de Produits et Commandes &copy; 2025</p>
        </div>
      </footer>
    </div>
  `
})
export class AppComponent {
  title = 'Gestion de Produits et Commandes';
}