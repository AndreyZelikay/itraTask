import { Component, OnInit } from '@angular/core';
import {LoginForm} from '../../../MyModules/login.module';
import {LoginService} from '../../../MyServices/LogInService/login.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private loginservice:LoginService) { }

  loginform:LoginForm;

  ngOnInit() {
  }
  signUp(Username,Email,Password){
  	this.loginform={
  		email:Email,
  		password:Password,
  		username:Username,
  		id:0
  	};
  	this.loginservice.signUp(this.loginform);
  }
}
