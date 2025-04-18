import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-loader',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './loader.component.html'
})
export class LoaderComponent {
  @Input() message: string = 'Chargement en cours...';
}