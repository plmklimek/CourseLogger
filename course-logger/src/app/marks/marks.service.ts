import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppService } from '../app.service';
import { Mark } from '../interfaces/Mark';
import { UserCourse } from '../interfaces/UserCourse';
import { User } from '../interfaces/UserInterface';

@Injectable({
  providedIn: 'root'
})
export class MarksService {
  user: User = {} as User;
  private apiUrl: string = 'http://localhost:8080/marks';
  
  constructor(private http: HttpClient, private appService: AppService) { }

  getMarksByCourseAndUser(userCourse: UserCourse): Observable<{ [key: string]: Mark[] }> {
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
    return this.http.post<{ [key: string]: Mark[] }>(
      `${this.apiUrl}/byCourseAndUser`,
      userCourse,
      httpOptions
    );
  }

  addMark(mark:Mark){
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
    return this.http.post<{ [key: string]: Mark[] }>(
      `${this.apiUrl}/create`,
      mark,
      httpOptions
    );
  }
}
