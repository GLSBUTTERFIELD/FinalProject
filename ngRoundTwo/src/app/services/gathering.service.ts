import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Gathering } from '../models/gathering';
import { catchError, Observable, throwError } from 'rxjs';
import { GatheringParticipant } from '../models/gathering-participant';

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

 showHostedGatherings(): Observable<Gathering[]> {
  return this.http.get<Gathering[]>(this.url + '/hosted', this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('gatheringService.showHostedGatherings(): error showing gatherings Hosted: ' + err)
       );
      })
  )
 }

 loadFutureGatherings(): Observable<Gathering[]>{
  return this.http.get<Gathering[]>(this.url + '/attending', this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('gatheringService.loadFutureGatherings(): error loading future gatherings: ' + err)
       );
      })
  )
 }

 loadPastGatherings(): Observable<Gathering[]>{
  return this.http.get<Gathering[]>(this.url + '/attended', this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('gatheringService.loadPastGatherings(): error loading past gatherings: ' + err)
       );
      })
  )
 }

 create(gathering:Gathering) : Observable<Gathering> {
  return this.http.post<Gathering>(this.url, gathering, this.getHttpOptions()).pipe(
    catchError((err: any) => {
    console.log(err);
    return throwError(
      () => new Error('gatheringService.create(): error creating gathering: ' + err)
     );
    })
  );
}

update(gathering: Gathering) : Observable<Gathering> {
      // this.getHttpOptions() authenticates the users action. Make sure to add
    return this.http.put<Gathering>(`${this.url}/${gathering.id}`, gathering, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('gatheringService.update(): error updating gathering: ' + err)
        );
      })
    );
  }

  destroy(gatheringId: number) : Observable<void> {
    return this.http.delete<void>(`${this.url}/${gatheringId}`, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('gatheringService.delete(): error deleting the gathering: ' + err)
        );
      })
    );
  }

  addAttendee(gatheringId: number): Observable <GatheringParticipant> {
    return this.http.post<GatheringParticipant>(`${this.url}/${gatheringId}/participants`, null, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('gatheringService.addAttendee(): error adding gatheringParticipant ' + err)
        );
      })
    )
  }

}
