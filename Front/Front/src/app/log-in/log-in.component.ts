import { Component, OnInit } from '@angular/core';
import {LoginForm} from '../../../MyModules/login.module';
import {LoginService} from '../../../MyServices/LogInService/login.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {

  constructor(private loginservice:LoginService) { }

  loginform:LoginForm;

  ngOnInit() {
  }

  logInForm(email,password){
  	this.loginform={
  		email:email,
  		password:password,
  		username:"",
  		id:0
  	};
  	this.loginservice.signIn(this.loginform);
  }
}