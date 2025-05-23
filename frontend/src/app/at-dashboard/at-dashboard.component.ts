import { Component } from '@angular/core';
import { ApiModule } from '../services/api.module';
import { AuthService } from '../services/auth-api.service';
import { User } from '../models/user';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-at-dashboard',
  imports: [ApiModule, CommonModule, FormsModule, RouterLink],
  templateUrl: './at-dashboard.component.html',
  styleUrl: './at-dashboard.component.css'
})
export class ATDashboardComponent {

  currentUser: User | null = null;

constructor(private authService: AuthService){}

ngOnInit(){

  this.authService.getCurrentUserDataObservable().subscribe(user =>{
    this.currentUser=user;
    console.log(this.currentUser)
  });

}

}

