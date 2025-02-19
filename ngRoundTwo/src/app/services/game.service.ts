import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { catchError, Observable, throwError } from 'rxjs';
import { Game } from '../models/game';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private url = environment.baseUrl + 'api/games';

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

  showAll(): Observable<Game[]>{
    return this.http.get<Game[]>(this.url).pipe(
      catchError((err: any) => {
              console.log(err);
              return throwError(
                () => new Error('UserService.show(): error finding specified UserId: ' + err)
              );
            })
          );
  }

  create(game:Game) : Observable<Game> {
    return this.http.post<Game>(this.url, game, this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('gameService.create(): error creating game: ' + err)
       );
      })
    );
  }

}
