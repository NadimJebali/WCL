import { Component } from '@angular/core';
import { ApiModule } from '../services/api.module';
import { AuthService } from '../services/auth-api.service';
import { FormsModule } from '@angular/forms';
import { User } from '../models/user';
import { RouterLink, Router } from '@angular/router';
import { UserApiService } from '../services/user-api.service';

@Component({
  selector: 'app-login',
  imports: [ApiModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  email:string="";
  password:string="";

  constructor(private authService:AuthService, private router: Router, private userService: UserApiService){
    
  }

  login() {
    const user : User = {
      email: this.email,
      password: this.password
    }

    this.authService.login(user).subscribe(
      (response:{token:string})=>{
        localStorage.setItem('token', response.token);
        this.authService.setLoginStatus(true);
        this.userService.getOneUser(this.email).subscribe((userData)=>{
          this.authService.setCurrentUserData(userData); 
          this.router.navigate(['/dashboard']);
        });
        
      }
    )
  }
  

}
