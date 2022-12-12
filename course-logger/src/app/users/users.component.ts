import { HttpHeaders } from '@angular/common/http';
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
      let headers = new HttpHeaders();
      headers = headers.append('Authorization', 'Basic ' + this.user.base);
      headers = headers.append(
        'Content-Type',
        'application/x-www-form-urlencoded'
      );
      const httpOptions = {
        email: this.user.username,
        password: this.user.password,
      };
      this.stompClient.connect(httpOptions, (frame: any) => {
        // Subscribe to notification topic
        console.log(frame);
        console.log('connected');
        this.stompClient.subscribe('/user/specific', (notifications: any) => {
          console.log(';;');
          console.log(notifications);
          // Update notifications attribute with the recent messsage sent from the server
          this.text = JSON.parse(notifications.body);
        });
      });
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
