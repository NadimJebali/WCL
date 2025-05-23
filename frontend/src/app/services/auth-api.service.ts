import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, ReplaySubject } from 'rxjs';
import { TokenResponse } from '../models/token';
import { User } from '../models/user';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/auth/'; // Replace wif yer API URL
  isLoggedIn: boolean = false;
  isLoggedInStatus: ReplaySubject<boolean> = new ReplaySubject(1)

  private currentUserData!: User;
  private currentUser: BehaviorSubject<User | null> = new BehaviorSubject<User | null>(null);

  constructor(private http: HttpClient, private router: Router) {
    const storedUser = localStorage.getItem('currentUser');
    if (storedUser) {
      this.currentUserData = JSON.parse(storedUser);
      this.currentUser.next(this.currentUserData);
      this.isLoggedIn = true;
      this.isLoggedInStatus.next(this.isLoggedIn);
    }
  }

  // Fetch Data (GET request)
  getData(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }

  // Send Data (POST request)
  login(data: User): Observable<TokenResponse> {
    return this.http.post<TokenResponse>(this.apiUrl+'authentication', data);
  }

  logout(): void {
    const token = localStorage.getItem('token');
     this.http.put(this.apiUrl+'logout', {token}).subscribe();
  }

  setLoginStatus(status:boolean){
    this.isLoggedIn = status;
    this.isLoggedInStatus.next(this.isLoggedIn);
  }

  get isLoggedInStatusObservable(): Observable<boolean> {
    return this.isLoggedInStatus.asObservable();
  }

  setCurrentUserData(userData: User): void{
    this.currentUserData = userData;
    this.currentUser.next(this.currentUserData);
    localStorage.setItem('currentUser', JSON.stringify(userData));
  }

  getCurrentUserDataObservable(): Observable<User | null> {
    return this.currentUser.asObservable();
  }
}
