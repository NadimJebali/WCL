import { Component, EventEmitter, Output } from '@angular/core';
import { ApiModule } from '../services/api.module';
import { AuthService } from '../services/auth-api.service';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { ExercicesApiService } from '../services/exercies-api.service';
import { User } from '../models/user';
import { Exercice } from '../models/exercice';
import { CommonModule, Location } from '@angular/common';
import { ExItemComponent } from '../ex-item/ex-item.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-teacher-ex-list',
  standalone: true,
  imports: [ApiModule, CommonModule, ExItemComponent, RouterLink, FormsModule],
  templateUrl: './teacher-ex-list.component.html',
  styleUrl: './teacher-ex-list.component.css'
})
export class TeacherExListComponent {

  exList: Exercice[] = [];
  filteredExList: Exercice[] = [];
  currentUser: User | null = null;
  
  filters = {
    searchTerm: ''
  };

  @Output() selectedExercice = new EventEmitter<Exercice>();

  constructor(
    private authService: AuthService,
    private router: ActivatedRoute,
    private exerciceService: ExercicesApiService,
    private location: Location
  ){}

  ngOnInit() {
    this.authService.getCurrentUserDataObservable().subscribe(user => {
      this.currentUser = user;

      // After currentUser is set, fetch exercises
      this.loadUserExercises();
    });
  }

  loadUserExercises() {
  this.exerciceService.getAllEx().subscribe(list => {
    if (this.currentUser?.role === 'ADMIN') {
      // Admin sees all exercises
      this.exList = list;
    } else {
      // Non-admin sees only their exercises
      this.exList = list.filter(item => item.userDto.id === this.currentUser?.id);
    }
    this.applyFilters();
  });
}

  applyFilters() {
    const term = this.filters.searchTerm.toLowerCase().trim();
  
    if (!term) {
      this.filteredExList = [...this.exList];
      return;
    }
  
    this.filteredExList = this.exList.filter(ex => {
      const nameMatch = ex.title.toLowerCase().includes(term);
      const idMatch = ex.id.toString().includes(term);
      const subjectMatch = ex.subjectDto.name.toLowerCase().includes(term);
      return nameMatch || idMatch || subjectMatch;
    });
  }


  selectExercice(exercice: Exercice): void {
    this.selectedExercice.emit(exercice);
  }

  goBack() {
    this.location.back();
  }
}
