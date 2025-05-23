import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Subject } from '../models/subject';

@Injectable({
  providedIn: 'root',
})
export class SubjectApiService {
  private apiUrl = 'http://localhost:8080/subject/'; // Replace wif yer API URL

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
  getSubjects(): Observable<Subject[]> {
    return this.http.get<Subject[]>(this.apiUrl+'all', this.getHeaders());
  }

  // Send Data (POST request)
  sendData(data: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, data);
  }
}
