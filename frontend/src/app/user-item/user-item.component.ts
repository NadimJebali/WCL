import { Component, Input } from '@angular/core';
import { User } from '../models/user';
import { Router, ActivatedRoute } from '@angular/router';
import { UserApiService } from '../services/user-api.service';
import { ApiModule } from '../services/api.module';

@Component({
  selector: 'app-user-item',
  imports: [ApiModule],
  templateUrl: './user-item.component.html',
  styleUrl: './user-item.component.css'
})
export class UserItemComponent {

  @Input() user!: User;

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserApiService){}

  openProfile() {
    this.router.navigate(['/users-list', this.user.id])
  }

  deleteUser(){
    this.userService.deleteUsersById(this.user.id!).subscribe(user =>{
      alert("user deleted");
      window.location.reload()
    })
  }

}
