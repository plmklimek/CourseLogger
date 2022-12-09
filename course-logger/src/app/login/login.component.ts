import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { AppService } from '../app.service';
import { User } from '../interfaces/UserInterface';
import { loginStart } from '../state/auth.actions';
import { AuthState } from '../state/auth.state';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  userForm: FormGroup = {} as FormGroup;
  user: User = {} as User;

  constructor(
    private formBuilder: FormBuilder,
    private appService: AppService,
    private route: ActivatedRoute,
    private router: Router,
    private store: Store<AuthState>
  ) {}

  ngOnInit(): void {
    this.userForm = this.buildUserForm();
  }

  buildUserForm() {
    return this.formBuilder.group({
      email: this.user.email,
      password: this.user.password,
    });
  }

  login() {
    const email = this.userForm.value.email;
    const password = this.userForm.value.password;
    this.store.dispatch(loginStart({ email, password }));
  }
}
