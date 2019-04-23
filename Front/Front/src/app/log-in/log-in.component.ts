import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../../MyServices/LogInService/login.service';
import {TransformableFormGroup} from '../../helpers';
import { form } from './log-in.form';
import {Router} from '@angular/router';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {

  public form: TransformableFormGroup = form;
  private result = true;
  constructor(private loginservice: LoginService, private router: Router) { }

  ngOnInit() {
  }

  SingIn() {
    this.result = false;
    this.loginservice.signIn(this.form).subscribe(
        (response) => {
          localStorage.setItem('token', response.token);
          this.result = true;
        });
    if (this.result) {
      this.router.navigate(['']);
    } else {
      this.form.markAsTouched();
    }
  }
}
