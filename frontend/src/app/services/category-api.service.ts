import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../models/category';

@Injectable({
  providedIn: 'root',
})
export class CategoryApiService {
  private apiUrl = 'http://localhost:8080/category/'; // Replace wif yer API URL

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
  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.apiUrl+'all', this.getHeaders());
  }

  // Send Data (POST request)
  sendData(data: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, data);
  }
  
}
