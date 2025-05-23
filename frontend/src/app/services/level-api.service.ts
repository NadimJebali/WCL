import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Level } from '../models/level';

@Injectable({
  providedIn: 'root',
})
export class LevelApiService {
  private apiUrl = 'http://localhost:8080/level/'; // Replace wif yer API URL

  constructor(private http: HttpClient) {}

  private getHeaders() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}` // Add your token here
      })
    };
  }

  // Fetch Data (GET request)
  getLevels(): Observable<Level[]> {
    return this.http.get<Level[]>(this.apiUrl+'all', this.getHeaders());
  }

  // Send Data (POST request)
  sendData(data: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, data);
  }
}
