import { Component } from '@angular/core';
import { ApiModule } from '../services/api.module';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { User } from '../models/user';
import { RegisterService } from '../services/register-api.service';
import { Location } from '@angular/common';


@Component({
  selector: 'app-add-user',
  imports: [ApiModule, FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.css'
})
export class AddUserComponent {

  userForm: FormGroup;
  firstname: string | undefined;
  lastname: string | undefined;
  email: string | undefined;
  password: string | undefined;
  role: string | undefined;
  router: any;

  constructor(private fb: FormBuilder, private registerService: RegisterService, private location: Location ) {
    this.userForm = this.fb.group({
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      role: ['STUDENT', Validators.required]
    });
  }

  submitForm() {
    if (this.userForm.invalid) {
      alert('Please fill in all fields correctly!');
      return;
    }
  
    const user: User = this.userForm.value;
  
    this.registerService.register(user).subscribe(
      (response: { token: string }) => {
        if (response.token) {
          alert('User added!');
        }
      }
    );
  }

  goBack(){
    this.location.back();
  }
  

}
