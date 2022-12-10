import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { autoLogin, autoLogout, loginStart } from './state/auth.actions';
import { AuthState } from './state/auth.state';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'course-logger';
  constructor(private store: Store<AuthState>) {}

  ngOnInit(): void {
    this.store.dispatch(autoLogin());
  }

  logout() {
    this.store.dispatch(autoLogout());
  }
}
