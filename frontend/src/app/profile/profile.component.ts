import { Component } from '@angular/core';
import { UserApiService } from '../services/user-api.service';
import { AuthService } from '../services/auth-api.service';
import { User } from '../models/user';
import { CommonModule } from '@angular/common';
import { ApiModule } from '../services/api.module';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-profile',
  imports: [CommonModule, ApiModule, RouterLink],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {

  currentUser: User | null = null;

  constructor(private userService: UserApiService, private authService: AuthService){

  }

  ngOnInit(){
    this.authService.getCurrentUserDataObservable().subscribe(user=>{
      this.currentUser = user;
    })
  }

}
