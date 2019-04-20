import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../../MyServices/LogInService/login.service';
import {LoginForm} from '../../../MyModules/login.module';
 
@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  public Users:LoginForm[]; 
  constructor(private loginService:LoginService) { }

  ngOnInit() {
  	this.loginService.GetAll().subscribe(
     (response)=>{
         this.Users=response;
     },
     (error)=>{
       alert("an error with get");
     });
  }
  delete(id){
  	this.loginService.DeleteUser(id);
  }

}
