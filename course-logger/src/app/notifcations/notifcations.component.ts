import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { User } from '../interfaces/UserInterface';
import { MessageServiceService } from '../message-service.service';
import { NotificationsService } from '../notifications/notifications.service';

@Component({
  selector: 'app-notifcations',
  templateUrl: './notifcations.component.html',
  styleUrls: ['./notifcations.component.css'],
})
export class NotifcationsComponent implements OnInit {
  user: User = {} as User;
  text: any = { from: '', to: '', text: '' };
  stompClient: any;
  constructor(
    private appService: AppService,
    private webSocketService: MessageServiceService,
    private notificationService: NotificationsService
  ) {}

  ngOnInit(): void {
    this.user = this.appService.getAuth();
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
          // Update notifications attribute with the recent messsage sent from the server
          this.text = JSON.parse(notifications.body);
        });
      });
    }
    this.notificationService.getText().subscribe((text) => {
      this.text = {text: text}
    });

    setTimeout(() => this.notificationService.setText(''), 3 * 1500);
  }
}
