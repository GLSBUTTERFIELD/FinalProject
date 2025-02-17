import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Gathering } from '../models/gathering';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GatheringService {
private url = environment.baseUrl + 'api/gatherings';

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

  index(): Observable<Gathering[]> {
    return this.http.get<Gathering[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('gatheringService.index(): error showing posts: ' + err)
        );
      })
    );
  }

 getGatheringById(gatheringId: number): Observable<Gathering> {
  return this.http.get<Gathering>(this.url + '/' + gatheringId, this.getHttpOptions()).pipe(
    catchError((err:any) =>{
      console.log(err);
      return throwError(
        () => new Error('gatheringService.getGatheringById(): error getting gathering: ' + err)
    );
    })
  );
 }

 create(gathering:Gathering) : Observable<Gathering> {
  return this.http.post<Gathering>(this.url, gathering, this.getHttpOptions()).pipe(
    catchError((err: any) => {
    console.log(err);
    return throwError(
      () => new Error('TodoService.create(): error retrieving todo: ' + err)
     );
    })
  );
}


}
