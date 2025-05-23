import { Component, Input, input } from '@angular/core';
import { Exercice } from '../models/exercice';
import { ActivatedRoute, Router} from '@angular/router';
import { ApiModule } from '../services/api.module';
import { User } from '../models/user';
import { AuthService } from '../services/auth-api.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ExercicesApiService } from '../services/exercies-api.service';

@Component({
  selector: 'app-ex-item',
  imports: [ApiModule, CommonModule, FormsModule],
  templateUrl: './ex-item.component.html',
  styleUrl: './ex-item.component.css'
})
export class ExItemComponent {


  @Input() ex!: Exercice;

  subjectId!: number;
  categoryId!: number;
  currentUser: User | null = null;

  constructor(private router: Router,private route: ActivatedRoute, private authService: AuthService, private exerciceService: ExercicesApiService){

  }

  ngOnInit(){
    this.route.paramMap.subscribe(params =>{
      this.categoryId = Number(params.get('categoryId'))
      this.subjectId = Number(params.get('subjectId'))
    });

    this.authService.getCurrentUserDataObservable().subscribe(user => {
      this.currentUser = user;
      console.log('Current User:', this.currentUser);
    });

    
  }

  openEx() {
    this.router.navigate(['/ex-list', this.categoryId, this.subjectId, this.ex.id])
  }

  deleteEx(id: number) {
    if (confirm('Are you sure you want to delete this exercise?')) {
      this.exerciceService.deleteExerciceById(id).subscribe(ex =>{
        alert("exercice deleted");
      })
      window.location.reload()
    }
  }

  editEx(){
    this.router.navigate(['/ex-list/update',this.categoryId, this.subjectId, this.ex.id])
  }
  
}
