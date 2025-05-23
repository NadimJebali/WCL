import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Exercice } from '../models/exercice';
import { AIResponse } from '../models/airesponse';
import { CreateExerice } from '../models/createExercice.dto';
import { Solution } from '../models/solution';

@Injectable({
  providedIn: 'root',
})
export class ExercicesApiService {
  private apiUrl = 'http://localhost:8080/exercice/'; // Replace with your API URL

  constructor(private http: HttpClient) {}

  // Helper function to get headers with Authorization token
  private getHeaders() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}` // Add your token here if required
      })
    };
  }
  
  // Fetch a single exercise by its ID
  getOneEx(exId: number): Observable<Exercice> {
    return this.http.get<Exercice>(`${this.apiUrl}find/${exId}`, this.getHeaders());
  }

  // Fetch exercises by category and subject
  getExList(catId: number, subId: number): Observable<Exercice[]> {
    return this.http.get<Exercice[]>(`${this.apiUrl}find/bylevelandsubject/${catId}/${subId}`, this.getHeaders());
  }

  getAllEx(): Observable<Exercice[]> {
    return this.http.get<Exercice[]>(`${this.apiUrl}all`, this.getHeaders());
  }

  // Send a solution to get an AI response
  AiResponse(solution: string): Observable<AIResponse> {
    const body = { query: solution };  // Wrap the solution in a query object
    return this.http.post<AIResponse>('http://localhost:8080/gemini/ask', body, this.getHeaders());
  }

  createExercice(exercise: CreateExerice): Observable<CreateExerice> {
    return this.http.post<CreateExerice>(`${this.apiUrl}addnew`, exercise, this.getHeaders());
  }
  
  createSolution(solution: Solution): Observable<Solution> {
    return this.http.post<Solution>(`http://localhost:8080/solution/add`, solution, this.getHeaders());
  }

  deleteExerciceById(id: number): Observable<void> {
    return this.http.delete<void>(this.apiUrl+'delete/'+id, this.getHeaders());
  }

  getOneSol(solId: number): Observable<Solution> {
    return this.http.get<Solution>(`${this.apiUrl}find/${solId}`, this.getHeaders());
  }
  
  updateExercice(id: number, exercice: CreateExerice): Observable<Exercice> {
    return this.http.put<Exercice>(`/api/exercices/update/${id}`, exercice);
  }
  
  updateSolution(solution: Solution): Observable<Solution> {
    return this.http.put<Solution>(`/api/solutions/update/${solution.id}`, solution);
  }

}
