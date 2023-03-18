import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppService } from '../app.service';
import { User } from '../interfaces/UserInterface';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  private apiUrl: string = 'http://localhost:8080/users';
  user: User = {} as User;
  constructor(private http: HttpClient, private appService: AppService) {}

  getStudents(): Observable<User[]> {
    this.user = this.appService.getAuth();
    let httpHeaders = new HttpHeaders();
    httpHeaders = httpHeaders.set('Content-Type', 'application/json');
    httpHeaders = httpHeaders.set('Authorization', 'Basic ' + this.user.base);
    const httpOptions = {
      headers: httpHeaders,
    };
    return this.http.get<User[]>(`${this.apiUrl}/students`, httpOptions);
  }

  getTeachers(): Observable<User[]> {
    this.user = this.appService.getAuth();
    let httpHeaders = new HttpHeaders();
    httpHeaders = httpHeaders.set('Content-Type', 'application/json');
    httpHeaders = httpHeaders.set('Authorization', 'Basic ' + this.user.base);
    const httpOptions = {
      headers: httpHeaders,
    };
    return this.http.get<User[]>(`${this.apiUrl}/teachers`, httpOptions);
  }
}
