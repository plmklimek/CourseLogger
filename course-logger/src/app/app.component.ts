import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { AppService } from './app.service';
import { User } from './interfaces/UserInterface';
import { autoLogin, autoLogout, loginStart } from './state/auth.actions';
import { AuthState } from './state/auth.state';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'course-logger';
  user: User = {} as User;
  constructor(
    private store: Store<AuthState>,
    private appService: AppService
  ) {}

  ngOnInit(): void {
    this.store.dispatch(autoLogin());
    this.user = this.appService.getAuth();
    console.log(this.user);
  }

  logout() {
    this.store.dispatch(autoLogout());
  }

  isAdmin(): boolean {
    return this.appService.isAdmin();
  }

  isTeacher(): boolean {
    return this.appService.isTeacher();
  }

  isStudent(): boolean {
    return this.appService.isStudent();
  }
}
