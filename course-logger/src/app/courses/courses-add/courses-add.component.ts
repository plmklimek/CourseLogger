import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Course } from 'src/app/interfaces/Course';
import { UserCourseCreation } from 'src/app/interfaces/UserCourseCreation';
import { User } from 'src/app/interfaces/UserInterface';
import { NotificationsService } from 'src/app/notifications/notifications.service';
import { UsersService } from 'src/app/users/users.service';
import { CoursesService } from '../courses.service';

@Component({
  selector: 'app-courses-add',
  templateUrl: './courses-add.component.html',
  styleUrls: ['./courses-add.component.css'],
})
export class CoursesAddComponent implements OnInit {
  form!: FormGroup;
  users: User[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private usersService: UsersService,
    private coursesService: CoursesService,
    private notificationService:NotificationsService
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      name: '',
      teacherId: '',
    });
    this.usersService.getTeachers().subscribe((users) => {
      this.users = users;
    });
  }

  addCourse() {
    let course: Course = {
      name: this.form.value.name,
      teacherId: +this.form.value.teacherId,
      id: 0,
      students: [],
      teacher: undefined
    };
    this.coursesService.addCourse(course).subscribe(p => {
      this.notificationService.setText("Dodano przedmiot");
      setTimeout(() => window.location.reload(), 3 * 1500);
    });
  }
}
