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

  getId(email:string): number{
    let id: number = 0;
    this.getUserByEmail(email).subscribe((p) => {
      if(p.id != null)
        id = p.id;
    });
    return id as number;
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

  checkRole(role: string): boolean {
    this.user = this.getAuth();
    if (this.user && this.user.authorities) {
      return (
        this.user.authorities?.filter((auth) => auth.authority == role).length >
        0
      );
      return false;
    } else {
      return false;
    }
  }

  isAdmin(): boolean {
    return this.checkRole('ADMIN');
  }

  isTeacher(): boolean {
    return this.checkRole('TEACHER');
  }

  isStudent(): boolean {
    return this.checkRole('STUDENT');
  }

  getUserByEmail(email:string): Observable<User> {
    let httpHeaders = new HttpHeaders();
    httpHeaders = httpHeaders.set('Content-Type', 'application/json');
    httpHeaders = httpHeaders.set(
      'Authorization',
      'Basic ' + this.user.base
    );
    const httpOptions = {
      headers: httpHeaders,
    };
    return this.http.get<User>(
      `${this.apiUrl}/byEmail/${email}`,
      httpOptions
    );
  }
}
