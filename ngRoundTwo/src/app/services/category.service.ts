import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';
import { Category } from '../models/category';
import { ItemCondition } from '../models/item-condition';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private baseUrl = environment.baseUrl;
    private url = this.baseUrl + 'api/categories'

  constructor(private auth: AuthService, private http: HttpClient) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  listCategories(): Observable<Category[]> {
        return this.http.get<Category[]>(this.url, this.getHttpOptions()).pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError(
            () => new Error('Category.index(): error showing categories: ' + err)
            );
          })
        );
      }
}
