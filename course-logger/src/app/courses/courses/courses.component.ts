import { Component, OnInit } from '@angular/core';
import { AppService } from 'src/app/app.service';
import { Course } from 'src/app/interfaces/Course';
import { User } from 'src/app/interfaces/UserInterface';
import { CoursesService } from '../courses.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css'],
})
export class CoursesComponent implements OnInit {
  courses: Course[] = {} as Course[];
  user: User = {} as User;
  constructor(
    private courseService: CoursesService,
    private appService: AppService
  ) {
    this.user = this.appService.getAuth();
  }

  ngOnInit(): void {
    if (this.appService.isAdmin()) {
      this.courseService.getCourses().subscribe((objects) => {
        this.courses = objects;
      });
    } else if (this.user.id != null) {
      this.courseService.getCoursesByUser(this.user.id).subscribe((objects) => {
        this.courses = objects;
      });
    }
  }
}
