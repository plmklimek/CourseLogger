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
  private filesUrl: string = 'http://localhost:8080/files';
  user: User = {} as User;
  constructor(private http: HttpClient, private appService: AppService) {}

  getImage(name: string): Observable<Blob> {
    let headers = new HttpHeaders().append('Content-Type', 'image/jpeg');
    headers = headers.set('Authorization', 'Basic ' + this.user.base);
    return this.http.get(this.filesUrl + `/${name}`, {
      headers: headers,
      responseType: 'blob',
    });
  }

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

  getStudentsByCourse(id: number): Observable<User[]> {
    this.user = this.appService.getAuth();
    let httpHeaders = new HttpHeaders();
    httpHeaders = httpHeaders.set('Content-Type', 'application/json');
    httpHeaders = httpHeaders.set('Authorization', 'Basic ' + this.user.base);
    const httpOptions = {
      headers: httpHeaders,
    };
    return this.http.get<User[]>(
      `${this.apiUrl}/students/byCourse/${id}`,
      httpOptions
    );
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

  createUser(formData: FormData) {
    let type = formData.get('type') as unknown as number;
    let finalUrl = '';
    if (type == 0) {
      finalUrl = `${this.apiUrl}/teachers`;
    } else {
      finalUrl = `${this.apiUrl}/students`;
    }

    this.user = this.appService.getAuth();

    const boundary = this.generateBoundary();
    let httpHeaders = new HttpHeaders();
    //httpHeaders = httpHeaders.set('Content-Type', `multipart/form-data; boundary=${boundary}`);
    httpHeaders = httpHeaders.set('Authorization', 'Basic ' + this.user.base);
    const httpOptions = {
      headers: httpHeaders,
    };
    formData.append('test', 'test');
    return this.http.post(`${finalUrl}`, formData, httpOptions);
  }
  private generateBoundary(): string {
    let boundary = '';
    const possibleChars =
      'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';

    for (let i = 0; i < 32; i++) {
      boundary += possibleChars.charAt(
        Math.floor(Math.random() * possibleChars.length)
      );
    }

    return boundary;
  }
}
