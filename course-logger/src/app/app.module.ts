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

const routers: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'users', component: UsersComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UsersComponent,
    NotifcationsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    ReactiveFormsModule,
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
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
