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

const appRoutes:Routes=[
{path: '',component:MainPageComponent},
{path: 'add',component:TShirtComponent},
{path: 'TShirtPage/:id',component:TShirtPageComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    TShirtComponent,
    MainPageComponent,
    TShirtPageComponent
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
