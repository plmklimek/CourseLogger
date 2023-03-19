import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { User } from '../interfaces/UserInterface';
import { UsersService } from './users.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
})
export class UsersComponent implements OnInit {
  // user: User = {} as User;
  // stompClient: any;
  // text: any = { from: '', to: '', text: '' };
  users: User[] = [];

  constructor(
    private userService: UsersService,
    private sanitizer: DomSanitizer
  ) {
    // Open connection with server socket
  }

  ngOnInit(): void {
    // this.user = this.appService.getAuth();
    // if (!this.user.base) this.router.navigate(['login']);
    // if (this.user.base) {
    //   this.stompClient = this.webSocketService.connect();
    // }
  }

  loadStudents() {
    this.userService.getStudents().subscribe((users) => {
      users.forEach((user) => {
        if (user.image != null) {
          this.userService.getImage(user.image).subscribe((img) => {
            console.log(img);
            user.imageBlob = this.sanitizer.bypassSecurityTrustUrl(
              URL.createObjectURL(img)
            );
            console.log(user.imageBlob);
          });
        }
      });
      this.users = users;
    });
  }

  loadTeachers() {
    this.userService.getTeachers().subscribe((users) => {
      users.forEach((user) => {
        if (user.image != null) {
          this.userService.getImage(user.image).subscribe((img) => {
            console.log(img);
            user.imageBlob = this.sanitizer.bypassSecurityTrustUrl(
              URL.createObjectURL(img)
            );
            console.log(user.imageBlob);
          });
        }
      });
      this.users = users;
    });
  }
  // sendMessage() {
  //   this.stompClient.send(
  //     '/app/private',
  //     {},
  //     JSON.stringify({
  //       text: 'Test231321321312321312',
  //       to: 'mail1@mail.com',
  //     })
  //   );
  // }
}
