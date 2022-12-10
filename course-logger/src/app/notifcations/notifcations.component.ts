import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-notifcations',
  templateUrl: './notifcations.component.html',
  styleUrls: ['./notifcations.component.css'],
})
export class NotifcationsComponent implements OnInit {
  message: string = 'Test';

  constructor() {}

  ngOnInit(): void {}
}
