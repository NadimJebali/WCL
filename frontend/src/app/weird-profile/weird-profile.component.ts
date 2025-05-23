import { Component } from '@angular/core';
import { User } from '../models/user';
import { AuthService } from '../services/auth-api.service';
import { UserApiService } from '../services/user-api.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule, Location } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiModule } from '../services/api.module';

@Component({
  selector: 'app-weird-profile',
  imports: [ApiModule, CommonModule, FormsModule],
  templateUrl: './weird-profile.component.html',
  styleUrl: './weird-profile.component.css'
})
export class WeirdProfileComponent {

  usertId!: number;
  currentProfile: User | null = null;

  constructor(private authService: AuthService, private userService: UserApiService, private route: ActivatedRoute, private location: Location){}

  ngOnInit(){
    this.route.paramMap.subscribe(params => {
      this.usertId = Number(params.get('userId'));
    });

    this.userService.getOneUserById(this.usertId).subscribe(user =>{
      this.currentProfile = user;
      console.log(this.currentProfile)
    })
  }

  goBack(){
    this.location.back();
  }

}
