import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {RouterModule, Routes} from '@angular/router';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
    MatAutocompleteModule,
    MatButtonModule,
    MatCheckboxModule,
    MatFormFieldModule, MatIconModule,
    MatInputModule, MatSelectModule,
} from '@angular/material';
import { TagCloudModule } from 'angular-tag-cloud-module';
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
import { ProfileComponent } from './profile/profile.component';
import {MatChipsModule} from '@angular/material';
import { BasketComponent } from './basket/basket.component';
import { PaymentPageComponent } from './payment-page/payment-page.component';
import { ManageUserProfileComponent } from './manage-user-profile/manage-user-profile.component';
import { RefactorTshirtComponent } from './refactor-tshirt/refactor-tshirt.component';

const appRoutes: Routes = [
    {path: '', component: MainPageComponent},
    {path: 'activate/:uid', component: ActivatePageComponent},
    {path: 'confirm', component : ConfirmPageComponent},
    {path: 'success', component: SuccessfulRegistrationComponent},
    {path: 'add', component: TShirtComponent, canActivate: [AuthGuard]},
    {path: 'TShirtPage/:id', component: TShirtPageComponent, canActivate: [AuthGuard]},
    {path: 'login', component: LogInComponent},
    {path: 'registration', component: RegistrationComponent},
    {path: 'admin', component: AdminPageComponent, canActivate: [AuthGuard]},
    {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]},
    {path: 'basket', component: BasketComponent, canActivate: [AuthGuard]},
    {path: 'pay', component: PaymentPageComponent},
    {path: 'profile/:id', component: ManageUserProfileComponent, canActivate: [AuthGuard]},
    {path: 'refactor/:id', component: RefactorTshirtComponent, canActivate: [AuthGuard]}
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
    ConfirmPageComponent,
    ProfileComponent,
    BasketComponent,
    PaymentPageComponent,
    ManageUserProfileComponent,
    RefactorTshirtComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        RouterModule.forRoot(appRoutes),
        JwtModule.forRoot(JWT_Module_Options),
        BrowserAnimationsModule,
        MatButtonModule,
        MatCheckboxModule,
        MatFormFieldModule,
        MatAutocompleteModule,
        MatInputModule,
        TagCloudModule,
        MatChipsModule,
        MatIconModule,
        MatSelectModule,
    ],
  providers: [{
      provide : HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi   : true,
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
