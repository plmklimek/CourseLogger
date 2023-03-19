import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from './app.service';
import { Message } from './interfaces/Message';
import { User } from './interfaces/UserInterface';
import { MessageServiceService } from './message-service.service';

@Injectable({
  providedIn: 'root',
})
export class CommunicationService {
  user: User = {} as User;
  stompClient: any;
  text: any = { from: '', to: '', text: '' };

  constructor(
    private appService: AppService,
    private router: Router,
    private webSocketService: MessageServiceService,
    private http: HttpClient
  ) {
    this.user = this.appService.getAuth();
    if (!this.user.base) this.router.navigate(['login']);
    if (this.user.base) {
      this.stompClient = this.webSocketService.connect();
    }
  }

  sendMessage(message:Message) {
    console.log(message);
    this.stompClient.send(
      '/app/private',
      {},
      JSON.stringify({
        text: message.text,
        to: message.to
      })
    );
  }
}
