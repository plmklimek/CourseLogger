import { Injectable } from '@angular/core';
import { CanActivate, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AppService } from '../app.service';

@Injectable({
  providedIn: 'root',
})
export class TeacherAuthGuard implements CanActivate {
  constructor(private appService: AppService) {}

  canActivate():
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    return this.appService.isTeacher();
  }
}
