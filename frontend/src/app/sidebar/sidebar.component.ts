import { Component } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { CategoryApiService } from '../services/category-api.service';
import { Category } from '../models/category';
import { ApiModule } from '../services/api.module';
import { Level } from '../models/level';
import { LevelApiService } from '../services/level-api.service';
import { Subject } from '../models/subject';
import { SubjectApiService } from '../services/subject-api.service';
import { CommonModule } from '@angular/common';
import { AuthService } from '../services/auth-api.service';
import { User } from '../models/user';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-sidebar',
  imports: [RouterLink, ApiModule,CommonModule, FormsModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {


  categoryList:Category[] = [];
  levelList:Level[]=[]
  subjectList:Subject[]=[]
  isLoggedIn = false;
  currentUser: User | null = null;
  
  constructor(private router: Router,
     private categoryApiService: CategoryApiService,
      private levelApiService: LevelApiService,
       private subjectApiService: SubjectApiService,
        private authService: AuthService,
      ){}
  
  ngOnInit(){

    this.authService.getCurrentUserDataObservable().subscribe(user => {
      this.currentUser = user;
      console.log('Current User:', this.currentUser);
    });

    this.authService.isLoggedInStatusObservable.subscribe((status: boolean) => {
      this.isLoggedIn = status;
      console.log("Sidebar: Login Status: ", this.isLoggedIn);
    });

    this.categoryApiService.getCategories().subscribe(categories => {
      this.categoryList = categories;
      console.log(this.categoryList)
    });
    this.levelApiService.getLevels().subscribe(levels =>{
      this.levelList = levels;
      console.log(this.levelList)
    })
    this.subjectApiService.getSubjects().subscribe(subjects =>{
      this.subjectList = subjects;
      console.log(this.subjectList)
    })

  }


   getLevelsForCategory(categoryId?: number) {
    return this.levelList.filter(level => level.categoryDto.id === categoryId);
  }

  getSubjectForLevel(levelID: number){
    return this.subjectList.filter(subject => subject.levelDto.id === levelID);
  }
 


  logout() {
    this.authService.setLoginStatus(false);
    this.authService.logout();
    localStorage.clear();
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate(['']);
    })
  }
  showExList(categoryid?: number, subjectid?: number) {
    this.router.navigate(['/ex-list', categoryid, subjectid])
  }
}
