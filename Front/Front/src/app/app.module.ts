import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule} from '@angular/forms';
import {HttpClientModule} from "@angular/common/http";
import {RouterModule,Routes} from "@angular/router";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TShirtComponent } from './tshirt/tshirt.component';
import {TShirtService} from '../../MyServices/TShirtService/tshirt-service.service';
import { MainPageComponent} from './main-page/main-page.component';
import { TShirtPageComponent } from './tshirt-page/tshirt-page.component';
import { LogInComponent } from './log-in/log-in.component';
import { RegistrationComponent } from './registration/registration.component';

const appRoutes:Routes=[
{path: '',component:MainPageComponent},
{path: 'add',component:TShirtComponent},
{path: 'TShirtPage/:id',component:TShirtPageComponent},
{path: 'login',component:LogInComponent},
{path: 'registration',component:RegistrationComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    TShirtComponent,
    MainPageComponent,
    TShirtPageComponent,
    LogInComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [TShirtService],
  bootstrap: [AppComponent]
})
export class AppModule { }
