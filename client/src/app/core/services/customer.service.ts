import {Injectable} from '@angular/core';
import {Observable, of, switchMap} from 'rxjs';
import {environment} from '@env/environment.development';
import {HttpClient} from '@angular/common/http';

const baseUrl = `${environment.baseUrl}/api/v1/customer`;

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) {
  }

  getCustomer(): Observable<any> {
    return this.http.get(`${baseUrl}/`)
      .pipe(
        switchMap((response: any) => {
          return of(response);
        })
      );
  }
}
