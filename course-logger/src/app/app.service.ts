import { Injectable } from '@angular/core';
import { Observable, take } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Message } from './interfaces/MessageInterface';
import { Store } from '@ngrx/store';
import { AuthState } from './state/auth.state';
import { User } from './interfaces/UserInterface';

@Injectable({
  providedIn: 'root',
})
export class AppService {
  private apiUrl: string = 'http://localhost:8080/users';
  constructor(private http: HttpClient, private store: Store<AuthState>) {}

  message: Message = {} as Message;
  user: User = {} as User;

  getAuth(): User {
    this.store.pipe(take(1)).subscribe((s: any) => (this.user = s.auth.user));
    return this.user;
  }

  loginIn(mail: string, password: string): Observable<User> {
    let httpHeaders = new HttpHeaders();
    httpHeaders = httpHeaders.set('Content-Type', 'application/json');
    httpHeaders = httpHeaders.set(
      'Authorization',
      'Basic ' + window.btoa(`${mail}:${password}`)
    );
    const httpOptions = {
      headers: httpHeaders,
    };
    return this.http.get(
      `${this.apiUrl}/login`,
      httpOptions
    ) as Observable<User>;
  }

  logout() {
    localStorage.removeItem('user');
  }
}
