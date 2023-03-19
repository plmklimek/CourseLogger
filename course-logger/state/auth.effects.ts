import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { Action } from '@ngrx/store';
import { exhaustMap, map, Observable } from 'rxjs';
import { AppService } from 'src/app/app.service';
import { User } from 'src/app/interfaces/UserInterface';

import { loginStart, loginSuccess } from './auth.actions';

@Injectable()
export class AuthEffects {
  constructor(private actions$: Actions, private appService: AppService) {}

  user: User = {} as User;

  login$ = createEffect((): Observable<Action> => {
    return this.actions$.pipe(
      ofType(loginStart),
      exhaustMap((action) => {
        return this.appService.loginIn(action.email, action.password).pipe(
          map((data: User) => {
            this.setData(data, action);
            const user = this.user;
            localStorage.setItem('user', JSON.stringify(user));
            return loginSuccess({ user });
          })
        );
      })
    );
  });
  setData(data: User, action: { email: string; password: string }): void {
    if (this.user.id == undefined) {
      this.user.id = data.id;
    }
    if (this.user.username == undefined) {
      this.user.username = data.username;
    }
    if (this.user.password == undefined) {
      this.user.password = data.password;
    }
    if (this.user.authorities == undefined) {
      this.user.authorities = data.authorities;
    }
    if (this.user.base == undefined) {
      this.user.base = window.btoa(`${action.email}:${action.password}`);
    }
  }
}
