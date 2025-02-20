import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { InventoryItemComment } from '../models/inventory-item-comment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class InventoryItemCommentService {
  private url = environment.baseUrl + 'api/inventoryItems/comments';

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

    showAll(): Observable<InventoryItemComment[]>{
      return this.http.get<InventoryItemComment[]>(this.url).pipe(
        catchError((err: any) => {
                console.log(err);
                return throwError(
                  () => new Error('InventoryItemCommentService.show(): error finding InventoryItemComments ' + err)
                );
              })
            );
    }

    create(newComment:InventoryItemComment): Observable<InventoryItemComment> {
      return this.http.post<InventoryItemComment>(this.url, newComment, this.getHttpOptions()).pipe(
        catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('InventoryItemCommentService.create(): error creating InventoryItemComment ' + err)
         );
        })
      );
    }
}
