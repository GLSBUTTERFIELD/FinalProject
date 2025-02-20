import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { AuthService } from './auth.service';
import { catchError, Observable, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { InventoryItem } from '../models/inventory-item';
import { Game } from '../models/game';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class TradeService {
  private baseUrl = environment.baseUrl;

  private inventoryItemUrl = this.baseUrl + 'api/inventoryItems'

  constructor(
    private http: HttpClient,
    private auth: AuthService)
    { }

  currentUser: User | null = null;
  isLoggedIn: boolean = false;

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

getInventoryItemById(inventoryItemId: number): Observable<InventoryItem> {
  return this.http.get<InventoryItem>(this.inventoryItemUrl + '/' + inventoryItemId, this.getHttpOptions()).pipe(
    catchError((err:any) =>{
      console.log(err);
      return throwError(
        () => new Error('inventoryItemService.getInventoryItemById(): error getting inventoryItem: ' + err)
    );
    })
  );
 }

 update(inventoryItem: InventoryItem) : Observable<InventoryItem> {
       // this.getHttpOptions() authenticates the users action. Make sure to add
     return this.http.put<InventoryItem>(this.inventoryItemUrl + '/' + inventoryItem.id, inventoryItem, this.getHttpOptions()).pipe(
       catchError((err: any) => {
         console.log(err);
         return throwError(
           () => new Error('inventoryItemService.update(): error updating InventoryItem: ' + err)
         );
       })
     );
   }

   create(inventoryItem: InventoryItem): Observable<InventoryItem>{
    return this.http.post<InventoryItem>(this.inventoryItemUrl, inventoryItem, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('inventoryItemService.update(): error updating InventoryItem: ' + err)
        );
      })
    );
   }



}
