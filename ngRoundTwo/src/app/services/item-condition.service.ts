import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { environment } from '../../environments/environment';
import { ItemCondition } from '../models/item-condition';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ItemConditionService {
  private baseUrl = environment.baseUrl;

    private url = this.baseUrl + 'api/ItemConditions'

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

  index(): Observable<ItemCondition[]> {
      return this.http.get<ItemCondition[]>(this.url, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
          () => new Error('ItemConditionService.index(): error showing conditions: ' + err)
          );
        })
      );
    }
}
