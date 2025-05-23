import { Component, EventEmitter, Output } from '@angular/core';
import { UserItemComponent } from '../user-item/user-item.component';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { ApiModule } from '../services/api.module';
import { User } from '../models/user';
import { UserApiService } from '../services/user-api.service';
import { AuthService } from '../services/auth-api.service';
import { Location } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-user-list',
  imports: [ApiModule, CommonModule, UserItemComponent, RouterLink, FormsModule],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent {

  userList!: User[];
  currentUser: User | null = null;
  searchTerm: string = '';
  filteredUsers: User[] = [];
  
  @Output() selectedUser = new EventEmitter<User>();
  

  constructor(private authService: AuthService, private router: ActivatedRoute, private userService: UserApiService, private location: Location){}

  ngOnInit(){

    this.authService.getCurrentUserDataObservable().subscribe(user =>{
      this.currentUser = user;
    });
    
    this.userService.getAllUsers().subscribe(list =>{
      this.userList = list;
      this.filteredUsers = this.userList
    })

  }

  selectUser(user: User): void {
      this.selectedUser.emit(user);
    }

  goBack(){
    this.location.back()
  }

  filterUsers() {
    if (this.searchTerm.trim() === '') {
      this.filteredUsers = this.userList;
    } else {
      this.filteredUsers = this.userList.filter(user =>
        user.firstname!.toLowerCase().includes(this.searchTerm.toLowerCase()) || 
        user.role!.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
    }
  }
}
