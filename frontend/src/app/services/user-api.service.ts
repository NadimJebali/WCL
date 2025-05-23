import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { User } from '../models/user';
import { AuthService } from './auth-api.service';
import { Form } from '@angular/forms';

@Injectable({
  providedIn: 'root',
})
export class UserApiService {
  private apiUrl = 'http://localhost:8080/user/'; // Replace wif yer API URL

  currentUser: User | null = null;

  constructor(private http: HttpClient, private authService: AuthService) {
    this.authService.getCurrentUserDataObservable().subscribe(user=>{
      this.currentUser=user;
    })
  }

  private getHeaders() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}` // Add your token here
      })
    };
  }

  updateUser(user: User): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}update/${user.id}`, user, this.getHeaders())
      .pipe(
        tap(response => {
    
          localStorage.setItem('currentUser', JSON.stringify(response));
  
          this.currentUser = response;
          this.authService.setCurrentUserData(response);
        })
      );
  }

  // Fetch Data (GET request)
  getOneUser(email: string): Observable<User> {
    return this.http.get<User>(this.apiUrl+'findbyemail?email='+email, this.getHeaders());
  }

  getOneUserById(id: number): Observable<User> {
    return this.http.get<User>(this.apiUrl+'find/'+id, this.getHeaders());
  }

  // Send Data (POST request)
  sendData(data: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, data);
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl+'alluser', this.getHeaders());
  }

  deleteUsersById(id: number): Observable<void> {
    return this.http.delete<void>(this.apiUrl+'delete/'+id, this.getHeaders());
  }

  
}
