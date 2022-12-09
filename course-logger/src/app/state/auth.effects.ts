import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { Action } from '@ngrx/store';
import { exhaustMap, map, Observable } from 'rxjs';
import { AppService } from '../app.service';
import { loginStart, loginSuccess } from './auth.actions';

@Injectable()
export class AuthEffects {
  constructor(private actions$: Actions, private appService: AppService) {}

  login$ = createEffect((): Observable<Action> => {
    return this.actions$.pipe(
      ofType(loginStart),
      exhaustMap((action) => {
        return this.appService.loginIn(action.email, action.password).pipe(
          map((data) => {
            let base: string = window.btoa(
              `${action.email}:${action.password}`
            );
            return loginSuccess({ base });
          })
        );
      })
    );
  });
}
