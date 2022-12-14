import { Injectable } from '@angular/core';
import { CanActivate, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AppService } from '../app.service';

@Injectable({
  providedIn: 'root',
})
export class AdminAuthGuard implements CanActivate {
  constructor(private appService: AppService) {}

  canActivate():
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    console.log(this.appService.isAdmin());
    return this.appService.isAdmin();
  }
}
