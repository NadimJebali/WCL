import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { TokenResponse } from '../models/token';

@Injectable({
  providedIn: 'root',
})
export class RegisterService {
  private apiUrl = 'http://localhost:8080/auth/'; // Replace wif yer API URL

  constructor(private http: HttpClient) {}

  // Fetch Data (GET request)
  getData(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }

  // Send Data (POST request)
  register(data: User): Observable<TokenResponse> {
    return this.http.post<TokenResponse>(this.apiUrl+'register', data);
  }
}
