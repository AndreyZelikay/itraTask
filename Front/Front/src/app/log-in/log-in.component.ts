import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../../MyServices/LogInService/login.service';
import {TransformableFormGroup} from '../../helpers';
import { form } from './log-in.form';
import {Route, Router} from '@angular/router';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {

  public form: TransformableFormGroup = form;
  public error = false;
  constructor(private loginservice: LoginService, private router: Router) { }

  ngOnInit() {
  }

  singIn() {
    this.loginservice.signIn(this.form).subscribe(
        (response) => {
          localStorage.setItem('token', response.token);
          this.router.navigate(['']);
          this.loginservice.activity.next({
              isActive: true,
              role: this.loginservice.getRole()
          });
          this.loginservice.reSetActive().subscribe(
                (response) => {this.router.navigate(['']);
                               this.loginservice.activity.next({
                                isActive: true,
                                role: this.loginservice.getRole()
                    }); });
        },
        (error) => {
          this.form.markAsTouched();
          this.error = true;
        });
  }
}
