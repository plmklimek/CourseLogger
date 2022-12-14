import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from '../app.service';
import { User } from '../interfaces/UserInterface';
import { MessageServiceService } from '../message-service.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
})
export class UsersComponent implements OnInit {
  user: User = {} as User;
  stompClient: any;
  text: any = { from: '', to: '', text: '' };

  constructor(
    private appService: AppService,
    private router: Router,
    private webSocketService: MessageServiceService
  ) {
    // Open connection with server socket
  }

  ngOnInit(): void {
    this.user = this.appService.getAuth();
    if (!this.user.base) this.router.navigate(['login']);
    if (this.user.base) {
      this.stompClient = this.webSocketService.connect();
    }
  }

  sendMessage() {
    this.stompClient.send(
      '/app/private',
      {},
      JSON.stringify({
        text: 'Test231321321312321312',
        to: 'mail1@mail.com',
      })
    );
  }
}
