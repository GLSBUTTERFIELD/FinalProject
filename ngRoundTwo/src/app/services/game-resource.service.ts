import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';
import { Game } from '../models/game';
import { GameResource } from '../models/game-resource';

@Injectable({
  providedIn: 'root'
})
export class GameResourceService {
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

  showAll(): Observable<GameResource[]>{
    return this.http.get<GameResource[]>(this.url).pipe(
      catchError((err: any) => {
              console.log(err);
              return throwError(
                () => new Error('GameResourceService.show(): error finding Game Resources ' + err)
              );
            })
          );
  }

  create(gameResource:GameResource, gameId: number) : Observable<GameResource> {
    return this.http.post<GameResource>(this.url + "/" + gameId + "/resources",  gameResource, this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('gameResourceService.create(): error creating game resource ' + err)
       );
      })
    );
  }

  update(gameResource: GameResource) : Observable<GameResource> {
        // this.getHttpOptions() authenticates the users action. Make sure to add
      return this.http.put<GameResource>(`${this.url}/`, gameResource, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('Game Resource Service.update(): error updating game resource: ' + err)
          );
        })
      );
    }


}
