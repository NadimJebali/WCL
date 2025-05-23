import { Component, EventEmitter, Output } from '@angular/core';
import { ExercicesApiService } from '../services/exercies-api.service';
import { ActivatedRoute } from '@angular/router';
import { Exercice } from '../models/exercice';
import { ApiModule } from '../services/api.module';
import { CommonModule, Location } from '@angular/common';
import { ExItemComponent } from '../ex-item/ex-item.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-ex-list',
  standalone: true,
  imports: [ApiModule, CommonModule, ExItemComponent, FormsModule],
  templateUrl: './ex-list.component.html',
  styleUrl: './ex-list.component.css'
})
export class ExListComponent {

  categoryId!: number;
  subjectId!: number;
  exList: Exercice[] = [];
  filteredExList: Exercice[] = [];

  filters = {
    searchTerm: ''
  };

  @Output() selectedExercice = new EventEmitter<Exercice>();

  constructor(private exService: ExercicesApiService, private route: ActivatedRoute, private location: Location){}

  ngOnInit(){
    this.route.paramMap.subscribe(params =>{
      this.categoryId = Number(params.get('categoryId'));
      this.subjectId = Number(params.get('subjectId'));
      this.fetchExercices();
    });
  }

  fetchExercices(){
    this.exService.getExList(this.categoryId, this.subjectId).subscribe(list =>{
      this.exList = list;
      this.filteredExList = [...this.exList]; // initialize filtered list
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

  goBack(){
    this.location.back();
  }
}
