import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { SplashscreenComponent } from "./splashscreen/splashscreen.component";
import { ExFormComponent } from "./ex-form/ex-form.component";
import { DisplayExFormComponent } from "./display-ex-form/display-ex-form.component";
import { ApiModule } from './services/api.module';
import { ExercicesApiService } from './services/exercies-api.service';
import { AuthService } from './services/auth-api.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [CommonModule ,ApiModule ,RouterOutlet, ProfileComponent, SidebarComponent, LoginComponent, RegisterComponent, SplashscreenComponent, ExFormComponent, DisplayExFormComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  providers: [AuthService]
})
export class AppComponent implements OnInit {
  title = 'frontend';
  isLoggedIn: boolean;
  isSidebarOpen: boolean = true;
  isSidebarVisible: boolean = true;


  constructor(private exercicesApiService: ExercicesApiService, private authService: AuthService){
    this.isLoggedIn = !!localStorage.getItem('token');
    authService.setLoginStatus(this.isLoggedIn);
    this.authService.isLoggedInStatusObservable.subscribe((status: boolean) => {
      this.isLoggedIn = status;
      console.log("Sidebar: Login Status: ", this.isLoggedIn);
    });
  }

  toggleSidebar() {
    this.isSidebarVisible = !this.isSidebarVisible;
  }

  ngOnInit(){
    
  }

}
