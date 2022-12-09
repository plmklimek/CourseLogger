import { Injectable } from '@angular/core';
import { catchError, map, Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './interfaces/UserInterface';
import { Message } from './interfaces/MessageInterface';

@Injectable({
  providedIn: 'root',
})
export class AppService {
  private apiUrl: string = 'http://localhost:8080/users';
  constructor(private http: HttpClient) {}

  message: Message = {} as Message;

  loginIn(mail: string, password: string) {
    let httpHeaders = new HttpHeaders();
    httpHeaders = httpHeaders.set('Content-Type', 'application/json');
    httpHeaders = httpHeaders.set(
      'Authorization',
      'Basic ' + window.btoa(`${mail}:${password}`)
    );
    const httpOptions = {
      headers: httpHeaders,
    };
    return this.http.get(`${this.apiUrl}/login`, httpOptions);
  }
}
