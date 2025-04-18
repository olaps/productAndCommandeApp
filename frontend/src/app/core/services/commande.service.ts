import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CommandeRequest, ModePaiement } from '../models/commande.model';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommandeService {
  private apiUrl = `${environment.apiUrl}/commandes`;

  constructor(private http: HttpClient) { }

  getAllCommandes(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  getCommandeById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  createCommande(commandeRequest: CommandeRequest): Observable<any> {
    return this.http.post<any>(this.apiUrl, commandeRequest);
  }

  formatDateForApi(date: Date): string {
    return date.toISOString().split('T')[0];
  }

  getModePaiementDisplayName(modePaiement: string): string {
    switch (modePaiement) {
      case 'COMPTANT':
        return 'Paiement comptant';
      case 'DIFFERE':
        return 'Paiement différé';
      default:
        return 'Inconnu';
    }
  }

  calculateEarliestDeliveryDate(delaiLivraison?: number): Date {
    const today = new Date();
    const daysToAdd = delaiLivraison || 2;
    
    const deliveryDate = new Date(today);
    deliveryDate.setDate(today.getDate() + daysToAdd);
    
    return deliveryDate;
  }
}