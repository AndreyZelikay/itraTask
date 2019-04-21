import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule,HTTP_INTERCEPTORS} from "@angular/common/http";
import {RouterModule,Routes} from "@angular/router";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TShirtComponent } from './tshirt/tshirt.component';
import {TShirtService} from '../../MyServices/TShirtService/tshirt-service.service';
import { MainPageComponent} from './main-page/main-page.component';
import { TShirtPageComponent } from './tshirt-page/tshirt-page.component';
import { LogInComponent } from './log-in/log-in.component';
import { RegistrationComponent } from './registration/registration.component';
import { AuthGuardService as AuthGuard } from '../../MyServices/LogInService/auth-guard.service';
import {JwtModule,JwtModuleOptions} from '@auth0/angular-jwt';
import {AuthInterceptor} from '../../MyServices/LogInService/auth.interceptor';
import { AdminPageComponent } from './admin-page/admin-page.component';
import {ArrayPipe} from '../pipes/array.pipe'

const appRoutes:Routes=[
{path: '',component:MainPageComponent},
{path: 'add',component:TShirtComponent,canActivate: [AuthGuard]},
{path: 'TShirtPage/:id',component:TShirtPageComponent},
{path: 'login',component:LogInComponent},
{path: 'registration',component:RegistrationComponent},
{path: 'AdminPage',component:AdminPageComponent}
]

export function tokenGetter() {
  return localStorage.getItem('access_token');
}

const JWT_Module_Options: JwtModuleOptions = {
    config: {
        tokenGetter: tokenGetter
    }
};

@NgModule({
  declarations: [
    AppComponent,
    TShirtComponent,
    MainPageComponent,
    TShirtPageComponent,
    LogInComponent,
    RegistrationComponent,
    AdminPageComponent,
    ArrayPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    JwtModule.forRoot(JWT_Module_Options)
  ],
  providers: [{
      provide : HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi   : true,
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
