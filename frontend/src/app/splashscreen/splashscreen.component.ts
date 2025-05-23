import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { Router } from '@angular/router';
import { LoginComponent } from "../login/login.component";

@Component({
  selector: 'app-splashscreen',
  imports: [CommonModule, LoginComponent],
  templateUrl: './splashscreen.component.html',
  styleUrl: './splashscreen.component.css',
})
export class SplashscreenComponent {


}
