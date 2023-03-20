import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AppService } from 'src/app/app.service';
import { CommunicationService } from 'src/app/communication.service';
import { Course } from 'src/app/interfaces/Course';
import { Mark } from 'src/app/interfaces/Mark';
import { Message } from 'src/app/interfaces/Message';
import { UserCourse } from 'src/app/interfaces/UserCourse';
import { UserCourseCreation } from 'src/app/interfaces/UserCourseCreation';
import { User } from 'src/app/interfaces/UserInterface';
import { MarksService } from 'src/app/marks/marks.service';
import { NotificationsService } from 'src/app/notifications/notifications.service';
import { UsersService } from 'src/app/users/users.service';
import { CoursesService } from '../courses.service';

@Component({
  selector: 'app-courses-details',
  templateUrl: './courses-details.component.html',
  styleUrls: ['./courses-details.component.css'],
})
export class CoursesDetailsComponent implements OnInit {
  id: number = 0;
  course: Course = {} as Course;
  users: User[] = [];
  usersInCourse: User[] = [];
  student = {
    id: '',
  };
  mark = {
    studentId: '',
    mark: '1.0',
  };

  constructor(
    private route: ActivatedRoute,
    private objectService: CoursesService,
    private usersService: UsersService,
    private marksService: MarksService,
    private coursesService: CoursesService,
    private notificationService: NotificationsService,
    private appService: AppService,
    private communicationService: CommunicationService
  ) {}

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('id') || '0';
    this.id = +id;
    this.objectService.getCoursesById(this.id).subscribe((object) => {
      if (object.students) {
        object.students.forEach((student) => {
          if (student.id) {
            if (this.appService.isTeacher() || this.appService.isAdmin()) {
              let userCourse: UserCourse = {
                userId: student.id,
                courseId: this.id,
              };
              this.marksService
                .getMarksByCourseAndUser(userCourse)
                .subscribe((mark) => {
                  if (mark != null) {
                    let entries = Object.entries(mark);
                    entries.forEach((element) => {
                      element[1].forEach((pred) => {
                        if (student.marks == null) student.marks = [];
                        student.marks?.push(pred.mark);
                      });
                    });
                  }
                  console.log('student');
                  console.log(student);
                });
            } else if (this.appService.getAuth().id === student.id) {
              let userCourse: UserCourse = {
                userId: student.id,
                courseId: this.id,
              };
              this.marksService
                .getMarksByCourseAndUser(userCourse)
                .subscribe((mark) => {
                  if (mark != null) {
                    let entries = Object.entries(mark);
                    entries.forEach((element) => {
                      element[1].forEach((pred) => {
                        if (student.marks == null) student.marks = [];
                        student.marks?.push(pred.mark);
                      });
                    });
                  }
                  console.log('student');
                  console.log(student);
                });
            }
          }
        });
      }
      console.log('kurs');
      console.log(this.course);
      this.course = object;
    });
    this.usersService.getStudents().subscribe((users) => {
      this.users = users;
    });
    this.usersService.getStudentsByCourse(this.id).subscribe((users) => {
      this.usersInCourse = users;
    });
  }

  addStudentToCourse() {
    let userCourse: UserCourseCreation = {
      id: this.id,
      studentId: +this.student.id,
    };
    this.coursesService.addStudentToCourse(userCourse).subscribe((p) => {
      this.notificationService.setText('Dodano użytkownika do przedmiotu');
      setTimeout(() => window.location.reload(), 3 * 1500);
    });
  }
  addMark() {
    let mark: Mark = {
      mark: +this.mark.mark,
      studentId: +this.mark.studentId,
      teacherId: this.appService.getAuth().id,
      courseId: this.id,
    };
    console.log('mark');
    console.log(mark);
    this.marksService.addMark(mark).subscribe((p) => {
      this.notificationService.setText('Dodano ocenę');
      let message: Message = {
        text: `Otrzymałeś ocenę ${mark.mark} z przedmiotu ${this.course.name}`,
        to: this.usersInCourse
          .filter((user) => user.id === +this.mark.studentId)
          .map((pred) => pred.email)[0],
      };
      this.communicationService.sendMessage(message);
      setTimeout(() => window.location.reload(), 3 * 1500);
    });
  }
  isAdmin(): boolean {
    return this.appService.isAdmin();
  }

  isTeacher(): boolean {
    return this.appService.isTeacher();
  }

  isStudent(): boolean {
    return this.appService.isStudent();
  }
}
