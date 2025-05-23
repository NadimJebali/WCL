import { Component, ElementRef, ViewChild } from '@angular/core';
import { UserApiService } from '../services/user-api.service';
import { AuthService } from '../services/auth-api.service';
import { User } from '../models/user';
import { CommonModule } from '@angular/common';
import { ApiModule } from '../services/api.module';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-profile-update',
  imports: [CommonModule, ApiModule, RouterLink],
  templateUrl: './profile-update.component.html',
  styleUrl: './profile-update.component.css'
})
export class ProfileUpdateComponent {

  currentUser: User | null = null;
  newUser: User| null = null;
  @ViewChild('fullname') fullname!: ElementRef;
  
  constructor(private userService: UserApiService, private authService: AuthService, private router: Router){
    
  }
  ngOnInit(){
    this.authService.getCurrentUserDataObservable().subscribe(user=>{
      this.currentUser = user;
    })
  }

  updateProfile(){
    const fullname = this.fullname.nativeElement.value.trim();
    const nameParts = fullname.split(' ');
    this.newUser={
      id: this.currentUser?.id,
      firstname: nameParts[0],
      lastname: nameParts[1],
      password: this.currentUser?.password,
      email: this.currentUser?.email
    }
    this.userService.updateUser(this.newUser).subscribe(response =>{
      window.location.reload()
    });
   


  }

}
