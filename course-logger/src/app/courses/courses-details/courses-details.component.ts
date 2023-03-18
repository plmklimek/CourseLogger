import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Course } from 'src/app/interfaces/Course';
import { Mark } from 'src/app/interfaces/Mark';
import { UserCourse} from 'src/app/interfaces/UserCourse';
import { MarksService } from 'src/app/marks/marks.service';
import { UsersService } from 'src/app/users/users.service';
import { CoursesService } from '../courses.service';

@Component({
  selector: 'app-courses-details',
  templateUrl: './courses-details.component.html',
  styleUrls: ['./courses-details.component.css']
})
export class CoursesDetailsComponent implements OnInit {

  id: number = 0;
  course: Course = {} as Course;

  constructor(private route: ActivatedRoute, private objectService: CoursesService, private usersService: UsersService,
    private marksService: MarksService) { }

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('id') || '0';
    this.id = +id;
    this.objectService.getCoursesById(this.id).subscribe((object) => {
      object.students.forEach(student => {
        if(student.id){
          let userCourse: UserCourse = {
            userId: student.id,
            courseId: this.id
          }
          this.marksService.getMarksByCourseAndUser(userCourse).subscribe((mark) => {
            if(mark != null){
              let entries = Object.entries(mark);
              entries.forEach(element => {
                element[1].forEach(pred => {
                  if(student.marks == null)
                    student.marks = [];
                  student.marks?.push(pred.mark);
                });
              });
            }
            console.log('student');
            console.log(student);
          })
        }
        }
      );
      console.log('kurs');
      console.log(this.course);
      this.course = object;
    });
  }

}
