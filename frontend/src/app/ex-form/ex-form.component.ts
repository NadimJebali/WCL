import { Component } from '@angular/core';
import { AuthService } from '../services/auth-api.service';
import { CategoryApiService } from '../services/category-api.service';
import { LevelApiService } from '../services/level-api.service';
import { SubjectApiService } from '../services/subject-api.service';
import { Subject } from '../models/subject';
import { Category } from '../models/category';
import { Level } from '../models/level';
import { User } from '../models/user';
import { CommonModule, Location } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterLink, ActivatedRoute } from '@angular/router';
import { ApiModule } from '../services/api.module';
import { ExercicesApiService } from '../services/exercies-api.service';
import { Solution } from '../models/solution';
import { CreateExerice } from '../models/createExercice.dto';
import { Exercice } from '../models/exercice';

@Component({
  selector: 'app-ex-form',
  standalone: true,
  imports: [ApiModule, CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './ex-form.component.html',
  styleUrls: ['./ex-form.component.css']
})
export class ExFormComponent {
  categoryList: Category[] = [];
  levelList: Level[] = [];
  subjectList: Subject[] = [];
  isLoggedIn = false;
  currentUser: User | null = null;

  solution: Solution = { id: 0, title: '', content: '' };
  backupSolution: Solution = { id: 0, title: '', content: '' };

  exercice: CreateExerice = {
    id: 0,
    title: '',
    description: '',
    content: '',
    image: '',
    levelDtoId: 0,
    subjectDtoId: 0,
    solutionDtoId: 0,
    userDtoId: 0
  };

  formData = {
    title: '',
    description: '',
    content: '',
    levelDtoId: 0,
    subjectDtoId: 0,
    answer: ''
  };

  imagePreview: string | null = null;
  answerImagePreview: string | null = null;

  constructor(
    private categoryApiService: CategoryApiService,
    private levelApiService: LevelApiService,
    private subjectApiService: SubjectApiService,
    private authService: AuthService,
    private location: Location,
    private exerciceService: ExercicesApiService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.authService.getCurrentUserDataObservable().subscribe((user) => {
      this.currentUser = user;
    });

    this.authService.isLoggedInStatusObservable.subscribe((status: boolean) => {
      this.isLoggedIn = status;
    });

    this.categoryApiService.getCategories().subscribe((categories) => {
      this.categoryList = categories;
    });

    this.levelApiService.getLevels().subscribe((levels: Level[]) => {
      this.levelList = levels;
    });

    this.subjectApiService.getSubjects().subscribe((subjects: Subject[]) => {
      this.subjectList = subjects;
    });

    this.route.params.subscribe((params) => {
      const exId = +params['exId'];
      if (exId) {
        this.loadExerciseData(exId);
      }
    });
  }

  loadExerciseData(exId: number) {
    this.exerciceService.getOneEx(exId).subscribe({
      next: (ex: Exercice) => {
        this.formData.title = ex.title;
        this.formData.description = ex.description;
        this.formData.content = ex.content;
        this.formData.levelDtoId = ex.levelDto.id;
        this.formData.subjectDtoId = ex.subjectDto.id;
        this.formData.answer = ex.solutionDto.content;

        this.solution = {
          id: ex.solutionDto.id,
          title: ex.solutionDto.title,
          content: ex.solutionDto.content
        };
        this.backupSolution = { ...this.solution };

        this.imagePreview = ex.image || null;
        this.exercice.id = ex.id;
      },
      error: (err) => {
        console.error('Failed to load exercise data', err);
        alert('Error loading exercise data.');
      }
    });
  }

  goBack() {
    this.location.back();
  }

  submitSolution() {
    if (!this.formData.answer || !this.formData.title) {
      alert('Please fill the title and answer fields for the solution.');
      return;
    }
  
    this.solution.title = this.formData.title;
    this.solution.content = this.formData.answer;
  
    this.exercice.title = this.formData.title;
    this.exercice.description = this.formData.description;
    this.exercice.levelDtoId = this.formData.levelDtoId;
    this.exercice.subjectDtoId = this.formData.subjectDtoId;
    this.exercice.content = this.formData.content;
    this.exercice.userDtoId = this.currentUser?.id || 0;
    this.exercice.image = this.imagePreview || '';
  
    // If it's an update
    if (this.exercice.id !== 0) {
      this.solution.id = this.backupSolution.id;
  
      this.exerciceService.updateSolution(this.solution).subscribe({
        next: (updatedSol) => {
          this.exercice.solutionDtoId = updatedSol.id;
  
          this.exerciceService.updateExercice(this.exercice.id, this.exercice).subscribe({
            next: (updatedEx) => {
              alert('Exercise has been updated successfully.');
            },
            error: (err) => {
              console.error('Error updating exercise:', err);
              alert('An error occurred while updating the exercise.');
            }
          });
        },
        error: (err) => {
          console.error('Error updating solution:', err);
          alert('An error occurred while updating the solution.');
        }
      });
    } else {
      // Create new
      this.exerciceService.createSolution(this.solution).subscribe({
        next: (solutionResp) => {
          if (solutionResp.id) {
            alert('Solution has been added');
            this.backupSolution = { ...solutionResp };
            this.exercice.solutionDtoId = solutionResp.id;
  
            this.exerciceService.createExercice(this.exercice).subscribe({
              next: (exResp) => {
                if (exResp?.title != null) {
                  alert('Exercise has been added');
                  this.resetForm();
                } else {
                  alert('Exercise creation failed: No title returned.');
                }
              },
              error: (err) => {
                console.error('Error creating exercise:', err);
                alert('An error occurred while creating the exercise.');
              }
            });
          } else {
            alert('Failed to create solution');
          }
        },
        error: (err) => {
          console.error('Error creating solution:', err);
          alert('An error occurred while creating the solution.');
        }
      });
    }
  }

  resetForm() {
    this.formData = {
      title: '',
      description: '',
      content: '',
      levelDtoId: 0,
      subjectDtoId: 0,
      answer: ''
    };
    this.imagePreview = null;
    this.answerImagePreview = null;
    this.backupSolution = { id: 0, title: '', content: '' };
  }

  onImageChange(event: any) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.imagePreview = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }

  onAnswerImageChange(event: any) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.answerImagePreview = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }
}
