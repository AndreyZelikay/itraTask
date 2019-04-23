import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {RouterModule, Routes} from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TShirtComponent } from './tshirt/tshirt.component';
import { MainPageComponent} from './main-page/main-page.component';
import { TShirtPageComponent } from './tshirt-page/tshirt-page.component';
import { LogInComponent } from './log-in/log-in.component';
import { RegistrationComponent } from './registration/registration.component';
import { AuthGuardService as AuthGuard } from '../../MyServices/LogInService/auth-guard.service';
import {JwtModule, JwtModuleOptions} from '@auth0/angular-jwt';
import {AuthInterceptor} from '../../MyServices/LogInService/auth.interceptor';
import { AdminPageComponent } from './admin-page/admin-page.component';
import {ArrayPipe} from '../pipes/array.pipe';
import { TagCloudComponent } from './tag-cloud/tag-cloud.component';
import { ActivatePageComponent } from './activate-page/activate-page.component';
import { SuccessfulRegistrationComponent } from './successful-registration/successful-registration.component';
import { ConfirmPageComponent } from './confirm-page/confirm-page.component';

const appRoutes: Routes = [
    {path: '', component: MainPageComponent, canActivate: [AuthGuard]},
    {path: 'activate/:uid', component: ActivatePageComponent},
    {path: 'confirm', component : ConfirmPageComponent},
    {path: 'success', component: SuccessfulRegistrationComponent},
    {path: 'add', component: TShirtComponent, canActivate: [AuthGuard]},
    {path: 'TShirtPage/:id', component: TShirtPageComponent, canActivate: [AuthGuard]},
    {path: 'login', component: LogInComponent},
    {path: 'registration', component: RegistrationComponent},
    {path: 'AdminPage', component: AdminPageComponent, canActivate: [AuthGuard]}
];

export function tokenGetter() {
  return localStorage.getItem('token');
}

const JWT_Module_Options: JwtModuleOptions = {
    config: {
        tokenGetter
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
    ArrayPipe,
    TagCloudComponent,
    ActivatePageComponent,
    SuccessfulRegistrationComponent,
    ConfirmPageComponent
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
