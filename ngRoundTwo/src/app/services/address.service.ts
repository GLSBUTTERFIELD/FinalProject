import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { catchError, Observable, throwError } from 'rxjs';
import { Address } from '../models/address';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AddressService {
  private url = environment.baseUrl + 'api/addresses';

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
  public showAllAddresses() : Observable<Address[]> {
      return this.http.get<Address[]>(this.url, this.getHttpOptions()).pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError(
                () => new Error('addressService.showAllAddresses(): error showing all addresses: ' + err)
              );
            })
          );
        }

  create(address:Address) : Observable<Address> {
    return this.http.post<Address>(this.url, address, this.getHttpOptions()).pipe(
      catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('addressService.create(): error adding new address: ' + err)
       );
      })
    );
  }
}
