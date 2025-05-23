import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RegisterService } from '../services/register-api.service';
import { User } from '../models/user';
import { ApiModule } from '../services/api.module';
import { RouterLink, Router } from '@angular/router';
import { AuthService } from '../services/auth-api.service';
import { UserApiService } from '../services/user-api.service';

@Component({
  selector: 'app-register',
  imports: [RouterLink, FormsModule, ApiModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']  // Corrected styleUrl to styleUrls
})
export class RegisterComponent {
  firstname: string = "";
  lastname: string = "";
  email: string = "";
  password: string = "";
  role: string = "";

  constructor(
    private registerService: RegisterService,
    private router: Router,
    private authService: AuthService,
  ) {}

  register() {
    const user: User = {
      firstname: this.firstname,
      lastname: this.lastname,
      email: this.email,
      password: this.password,
      role: this.role
    };

    this.registerService.register(user).subscribe(
      (response: { token: string }) => {
        localStorage.setItem('token', response.token);
        this.authService.setLoginStatus(true);
        this.authService.setCurrentUserData(user);
        this.router.navigate(['/dashboard']);
      }
    );
  }
}
