import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from '../app.service';
import { User } from '../interfaces/UserInterface';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
})
export class UsersComponent implements OnInit {
  user: User = {} as User;

  constructor(private appService: AppService, private router: Router) {}

  ngOnInit(): void {
    this.user = this.appService.getAuth();
    if (!this.user.base) this.router.navigate(['login']);
  }
}
