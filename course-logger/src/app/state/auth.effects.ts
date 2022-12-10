import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { Action } from '@ngrx/store';
import { exhaustMap, map, mergeMap, Observable, of } from 'rxjs';
import { AppService } from '../app.service';
import { User } from '../interfaces/UserInterface';
import {
  autoLogin,
  autoLogout,
  loginStart,
  loginSuccess,
} from './auth.actions';

@Injectable()
export class AuthEffects {
  constructor(
    private actions$: Actions,
    private appService: AppService,
    private router: Router
  ) {}

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

  autoLogin$ = createEffect((): Observable<Action> => {
    return this.actions$.pipe(
      ofType(autoLogin),
      mergeMap((action) => {
        const userData = localStorage.getItem('user');
        let user;
        if (userData != null) {
          let parsed = JSON.parse(userData);
          user = parsed;
        } else {
          user = { email: '', password: '' };
        }
        return of(loginSuccess({ user }));
      })
    );
  });

  logout$ = createEffect(
    () => {
      return this.actions$.pipe(
        ofType(autoLogout),
        map((action) => {
          this.appService.logout();
          this.router.navigate(['login']);
        })
      );
    },
    { dispatch: false }
  );

  setData(data: User, action: { email: string; password: string }): void {
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
