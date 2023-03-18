import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/interfaces/Course';
import { CoursesService } from '../courses.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css'],
})
export class CoursesComponent implements OnInit {
  courses: Course[] = {} as Course[];
  constructor(private courseService: CoursesService) {}

  ngOnInit(): void {
    this.courseService.getCourses().subscribe((objects) => {
      this.courses = objects;
    });
    console.log('koniec');
  }
}
