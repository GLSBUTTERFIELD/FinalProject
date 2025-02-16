import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private url = environment.baseUrl + 'api/users';

  constructor(private http: HttpClient, private auth: AuthService) {}

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  public show(userId: number) : Observable<User> {
    return this.http.get<User>(this.url + "/" + userId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserService.show(): error finding specified UserId: ' + err)
        );
      })
    );
  }

  update(user: User) : Observable<User> {
      // this.getHttpOptions() authenticates the users action. Make sure to add
    return this.http.put<User>(`${this.url}/${user.id}`, user, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('userService.update(): error updating user: ' + err)
        );
      })
    );
  }

  destroy(userId: number) : Observable<void> {
    return this.http.delete<void>(`${this.url}/${userId}`, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('userService.delete(): error deleting the user: ' + err)
        );
      })
    );
  }
}
