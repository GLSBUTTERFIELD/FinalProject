import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { AuthService } from './auth.service';
import { catchError, Observable, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Trade } from '../models/trade';

@Injectable({
  providedIn: 'root'
})
export class TradeService {
  private url = environment.baseUrl + 'api/trades';

  constructor(private http: HttpClient, private auth: AuthService) { }

  index(): Observable<Trade[]> {
    return this.http.get<Trade[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
        () => new Error('tradeService.index(): error showing trades: ' + err)
        );
      })
    );
  }
}
