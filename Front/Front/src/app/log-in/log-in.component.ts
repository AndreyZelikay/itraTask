import { Component, OnInit } from '@angular/core';
import {LoginForm} from '../../../MyModules/login.module';
import {LoginService} from '../../../MyServices/LogInService/login.service';
import {TransformableFormGroup} from '../../helpers';
import { form } from './log-in.form';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {

  public form: TransformableFormGroup = form;
  public isFormValid: boolean = true;
  public isNameExist: boolean = false;
  private result:String;


  constructor(private loginservice:LoginService) { }

  ngOnInit() {
  }

  logInForm(){
    if(this.form.valid){
        this.loginservice.signIn(this.form).subscribe(
      (response) => {
        localStorage.setItem('token', response['token']);
      },
      (error) => {
        console.log(error)
      });
      if(this.result=="Error!"){
          this.isNameExist=true;
          this.isFormValid=false;
      }
      else{
      this.isFormValid=true;
      }
      this.form.markAsTouched();
    }
    else{
      this.isFormValid = false;
      this.form.markAsTouched();
    }
  }
}
