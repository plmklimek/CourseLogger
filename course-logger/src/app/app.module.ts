import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { UsersComponent } from './users/users.component';
import { NotifcationsComponent } from './notifcations/notifcations.component';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { AUTH_STATE_NAME } from './state/auth.selector';
import { AuthReducer } from './state/auth.reducer';
import { AuthEffects } from './state/auth.effects';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { environment } from '../environments/environment';
import { MessageServiceService } from './message-service.service';
import { AdminAuthGuard } from './services/admin.auth.guard';
import { TeacherAuthGuard } from './services/teacher.auth.guard';
import { StudentAuthGuard } from './services/student.auth.guard';
import { CoursesComponent } from './courses/courses/courses.component';
import { CoursesDetailsComponent } from './courses/courses-details/courses-details.component';
import { FormsModule } from '@angular/forms';
import { CoursesAddComponent } from './courses/courses-add/courses-add.component';
import { HomeComponent } from './home/home/home.component';
import { UsersAddComponent } from './users/users-add/users-add.component';
const routers: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'courses', component: CoursesComponent },
  { path: 'courses-details/:id', component: CoursesDetailsComponent },
  { path: 'courses-add', component: CoursesAddComponent },
  { path: 'users-add', component: UsersAddComponent },
  { path: 'users', component: UsersComponent },
  { path: 'home', component: HomeComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'admin', component: UsersComponent, canActivate: [AdminAuthGuard] },
  {
    path: 'teacher',
    component: UsersComponent,
    canActivate: [TeacherAuthGuard],
  },
  {
    path: 'student',
    component: UsersComponent,
    canActivate: [StudentAuthGuard],
  },
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UsersComponent,
    NotifcationsComponent,
    CoursesComponent,
    CoursesDetailsComponent,
    CoursesAddComponent,
    HomeComponent,
    UsersAddComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule.forRoot(routers),
    HttpClientModule,
    EffectsModule.forRoot([AuthEffects]),
    StoreModule.forRoot(AuthReducer),
    StoreModule.forFeature(AUTH_STATE_NAME, AuthReducer),
    StoreDevtoolsModule.instrument({
      maxAge: 25,
      logOnly: environment.production,
    }),
  ],
  providers: [MessageServiceService],
  bootstrap: [AppComponent],
})
export class AppModule {}
