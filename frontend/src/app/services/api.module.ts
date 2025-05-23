import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { ExercicesApiService } from './exercies-api.service';
import { interceptor } from './intercepter-api.service';
import { AuthService } from './auth-api.service';
import { RegisterService } from './register-api.service';
import { CategoryApiService } from './category-api.service';
import { LevelApiService } from './level-api.service';
import { SubjectApiService } from './subject-api.service';
import { UserApiService } from './user-api.service';

@NgModule({
  providers: [
    provideHttpClient(withInterceptors([interceptor])),
    ExercicesApiService,
    AuthService,
    RegisterService,
    CategoryApiService,
    LevelApiService,
    SubjectApiService,
    UserApiService,
    
  ]
})
export class ApiModule { }
