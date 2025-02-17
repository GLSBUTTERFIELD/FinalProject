import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { AuthService } from './auth.service';
import { catchError, Observable, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { InventoryItem } from '../models/inventory-item';

@Injectable({
  providedIn: 'root'
})
export class TradeService {
  private baseUrl = environment.baseUrl;

  private inventoryItemUrl = this.baseUrl + 'api/inventoryItems'

  constructor(private http: HttpClient, private auth: AuthService) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  index(): Observable<InventoryItem[]> {
    return this.http.get<InventoryItem[]>(this.inventoryItemUrl).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
        () => new Error('tradeService.index(): error showing trades: ' + err)
        );
      })
    );
  }
}
