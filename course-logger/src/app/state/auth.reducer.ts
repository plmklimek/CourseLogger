import { Action, createReducer, on } from '@ngrx/store';
import { User } from '../interfaces/UserInterface';
import { autoLogout, loginSuccess } from './auth.actions';
import { AuthState, initialState } from './auth.state';

const _authReducer = createReducer(
  initialState,
  on(loginSuccess, (state, action) => {
    return {
      ...state,
      user: action.user,
    };
  }),
  on(autoLogout, (state, action) => {
    return {
      ...state,
      user: {} as User,
    };
  })
);

export const AuthReducer = (state: AuthState, action: Action) => {
  return _authReducer(state, action);
};
