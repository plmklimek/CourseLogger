import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/interfaces/UserInterface';
import { NotificationsService } from 'src/app/notifications/notifications.service';
import { UsersService } from '../users.service';

@Component({
  selector: 'app-users-add',
  templateUrl: './users-add.component.html',
  styleUrls: ['./users-add.component.css'],
})
export class UsersAddComponent implements OnInit {
  form: FormGroup = {} as FormGroup;
  selectedFile: File = {} as File;

  constructor(
    private formBuilder: FormBuilder,
    private usersService: UsersService,
    private notificationService: NotificationsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      name: '',
      surname: '',
      photo:[''],
      email:'',
      password:'',
      type: ['0', Validators.required]
    });
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.form.get('photo')?.setValue(this.selectedFile);
  }

  createUser(){

    let type = this.form.value.type as unknown as string;
    console.log('type');
    console.log(type);
    const formData = new FormData();
    formData.append('name', this.form.value.name);
    formData.append('surname', this.form.value.surname);
    formData.append('password', this.form.value.password);
    formData.append('email', this.form.value.email);
    formData.append('photo', this.selectedFile as File);
    formData.append('type', this.form.value.type)

    this.usersService.createUser(formData).subscribe(p => {
      this.notificationService.setText("Dodano użytkownika");
      setTimeout(() => this.router.navigate(['/users']), 3 * 1500);
    });
  }
  
}
