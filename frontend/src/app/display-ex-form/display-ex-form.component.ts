import { Component, ViewChild, ElementRef } from '@angular/core';
import { Exercice } from '../models/exercice';
import { AIResponse } from '../models/airesponse';
import { ApiModule } from '../services/api.module';
import { CommonModule, Location } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ExercicesApiService } from '../services/exercies-api.service';
import { AuthService } from '../services/auth-api.service';
import { User } from '../models/user';

@Component({
  selector: 'app-display-ex-form',
  standalone: true,
  imports: [ApiModule, CommonModule, FormsModule],
  templateUrl: './display-ex-form.component.html',
  styleUrls: ['./display-ex-form.component.css'] // note: 'styleUrls' plural here
})
export class DisplayExFormComponent {
  exId!: number;
  exercice!: Exercice;
  userSolution!: string;
  aiResponse!: AIResponse;
  currentUser: User | null = null;
  selectedLanguage: string = 'en'; // default language

  @ViewChild('feedbackCard') feedbackCard!: ElementRef;
  @ViewChild('feedbackText') feedbackText!: ElementRef;
  categoryId: number | undefined;
  subjectId: number | undefined;

  constructor(
    private exService: ExercicesApiService,
    private route: ActivatedRoute,
    private authService: AuthService,
    private location: Location,
    private router: Router
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.exId = Number(params.get('exId'));
      this.categoryId = Number(params.get('categoryId'));
      this.subjectId = Number(params.get('subjectId'));

      // Load the exercise once we have the ID
      if (this.exId) {
        this.exService.getOneEx(this.exId).subscribe(ex => {
          this.exercice = ex;
          console.log(this.exercice);
        });
      }
    });

    this.authService.getCurrentUserDataObservable().subscribe(user => {
      this.currentUser = user;
    });
  }

  getLanguageName(code: string): string {
    switch (code) {
      case 'fr': return 'French';
      case 'ar': return 'Arabic';
      default: return 'English';
    }
  }

  submitAnswer() {
    const prompt = `
You are an educational chatbot designed to assist students with their homework.

You are currently helping with the following homework: ${this.exercice.content}.

The student prefers responses in: ${this.getLanguageName(this.selectedLanguage)}.

**The student provided the following answer:** ${this.userSolution}

1. **If the student's answer is correct**:
  - Start your response with: "${this.getCorrectPhrase(this.selectedLanguage)}"
  - Acknowledge that the answer is correct in a positive and encouraging way.
  - Optionally, provide a brief explanation or validation of the correct answer to reinforce learning.

2. **If the student's answer is incorrect**:
  - Start your response with: "${this.getWrongPhrase(this.selectedLanguage)}"
  - Politely tell the student their answer is incorrect.
  - Provide a detailed, but simple explanation of why the answer is wrong and guide them through the correct solution.
  - Break down complex concepts into smaller, easy-to-understand steps and encourage them to think through the problem again.
  - Avoid being overly technical or using jargon that might confuse them.

3. **Tone**:
  - Always maintain a friendly, patient, and supportive tone.
  - Encourage the student to ask follow-up questions if they need further clarification.
  - Avoid monologues and make sure to engage in a conversation, so the student feels comfortable and involved.

Your goal is to help the student understand the material better and improve their skills.
Do not just give the answer—explain the reasoning behind it in a way that will help them learn.
`;

    this.exService.AiResponse(prompt).subscribe(response => {
      this.aiResponse = response;
      console.log(this.aiResponse);

      const card = this.feedbackCard.nativeElement as HTMLElement;
      const text = this.feedbackText.nativeElement as HTMLElement;

      card.style.display = 'block';

      const aiText = this.aiResponse?.candidates?.[0]?.content?.parts?.[0]?.text?.trim();

      if (!aiText) {
        text.innerText = this.getErrorPhrase(this.selectedLanguage);
        this.setCardStyles(card, 'neutral');
      } else {
        text.innerText = aiText;

        const correctPhrase = this.getCorrectPhrase(this.selectedLanguage).toLowerCase();
        const wrongPhrase = this.getWrongPhrase(this.selectedLanguage).toLowerCase();
        const lowerText = aiText.toLowerCase();

        if (lowerText.includes(correctPhrase)) {
          this.setCardStyles(card, 'correct');
        } else if (lowerText.includes(wrongPhrase)) {
          this.setCardStyles(card, 'wrong');
        } else {
          this.setCardStyles(card, 'neutral');
        }
      }
    });
  }

  // Navigation
  editExercice() {
    this.router.navigate(['/ex-list/update', this.categoryId, this.subjectId, this.exId]);
  }

  deleteExercice() {
    if (confirm('Are you sure you want to delete this exercise?')) {
      this.exService.deleteExerciceById(this.exId).subscribe(() => {
        alert('Exercise deleted');
        this.goBack();
      });
    }
  }

  goBack() {
    this.location.back();
  }

  // Localization helpers:

  getCorrectPhrase(lang: string): string {
    switch (lang) {
      case 'fr': return 'Vous avez raison'; // French
      case 'ar': return 'أنت محق';          // Arabic
      default: return 'You are correct';    // English
    }
  }

  getWrongPhrase(lang: string): string {
    switch (lang) {
      case 'fr': return 'Vous avez tort';   // French
      case 'ar': return 'أنت مخطئ';          // Arabic
      default: return 'You are wrong';      // English
    }
  }

  getErrorPhrase(lang: string): string {
    switch (lang) {
      case 'fr': return 'Erreur lors de la génération de la réponse.';
      case 'ar': return 'حدث خطأ أثناء إنشاء الرد.';
      default: return 'Error generating response.';
    }
  }

  setCardStyles(card: HTMLElement, status: 'correct' | 'wrong' | 'neutral') {
    card.classList.remove('border-success', 'border-danger', 'border-secondary');
    card.querySelector('.card-header')?.classList.remove('bg-success', 'bg-danger', 'bg-secondary');

    if (status === 'correct') {
      card.classList.add('border-success');
      card.querySelector('.card-header')?.classList.add('bg-success');
    } else if (status === 'wrong') {
      card.classList.add('border-danger');
      card.querySelector('.card-header')?.classList.add('bg-danger');
    } else {
      card.classList.add('border-secondary');
      card.querySelector('.card-header')?.classList.add('bg-secondary');
    }
  }
}
