import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { AppService } from '../app.service';
import { Course } from '../interfaces/Course';
import { User } from '../interfaces/UserInterface';
import { AuthState } from '../state/auth.state';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {
  private apiUrl: string = 'http://localhost:8080/courses';
  user: User = {} as User;

  constructor(private http: HttpClient, private appService: AppService) { }
  getCourses(): Observable<Course[]> {
    this.user = this.appService.getAuth();
    let httpHeaders = new HttpHeaders();
    httpHeaders = httpHeaders.set('Content-Type', 'application/json');
    httpHeaders = httpHeaders.set(
      'Authorization',
      'Basic ' + this.user.base
    );
    const httpOptions = {
      headers: httpHeaders,
    };
    return this.http.get<Course[]>(
      `${this.apiUrl}/getAll`,
      httpOptions
    );
  }

  getCoursesById(id:number): Observable<Course> {
    this.user = this.appService.getAuth();
    let httpHeaders = new HttpHeaders();
    httpHeaders = httpHeaders.set('Content-Type', 'application/json');
    httpHeaders = httpHeaders.set(
      'Authorization',
      'Basic ' + this.user.base
    );
    const httpOptions = {
      headers: httpHeaders,
    };
    return this.http.get<Course>(
      `${this.apiUrl}/${id}`,
      httpOptions
    );
  }
}
