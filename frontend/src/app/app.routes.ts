import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SplashscreenComponent } from './splashscreen/splashscreen.component';
import { ProfileUpdateComponent } from './profile-update/profile-update.component';
import { ExListComponent } from './ex-list/ex-list.component';
import { DisplayExFormComponent } from './display-ex-form/display-ex-form.component';
import { ExFormComponent } from './ex-form/ex-form.component';
import { ATDashboardComponent } from './at-dashboard/at-dashboard.component';
import { TeacherExListComponent } from './teacher-ex-list/teacher-ex-list.component';
import { UserListComponent } from './user-list/user-list.component';
import { WeirdProfileComponent } from './weird-profile/weird-profile.component';
import { AddUserComponent } from './add-user/add-user.component';

export const routes: Routes = [
    {
        path: '',
        component: SplashscreenComponent
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'register',
        component: RegisterComponent
    },
    {
        path: 'profile',
        component: ProfileComponent
    },
    {
        path: 'profile-update',
        component: ProfileUpdateComponent
    },
    {
        path: 'dashboard',
        component: DashboardComponent
    },
    {
        path: 'ex-list/:categoryId/:subjectId',
        component: ExListComponent
    },
    {
        path: 'ex-list/:categoryId/:subjectId/:exId',
        component: DisplayExFormComponent
    },
    {
        path: 'ex-form',
        component: ExFormComponent
    },
    {
        path: 'AT-dashboard',
        component: ATDashboardComponent
    },
    {
        path: 'teacher-ex-list',
        component: TeacherExListComponent
    },
    {
        path: 'users-list',
        component: UserListComponent
    },
    {
        path: 'users-list/:userId',
        component: WeirdProfileComponent
    },
    {
        path: 'add-user',
        component: AddUserComponent
    },
    {
        path: 'ex-list/update/:categoryId/:subjectId/:exId',
        component: ExFormComponent
    },
    
];
