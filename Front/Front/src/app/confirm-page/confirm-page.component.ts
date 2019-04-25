import { Component, OnInit } from '@angular/core';
import {TransformableFormGroup} from '../../helpers';
import {form} from '../registration/registration.form';
import {LoginService} from '../../../MyServices/LogInService/login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-confirm-page',
  templateUrl: './confirm-page.component.html',
  styleUrls: ['./confirm-page.component.css']
})
export class ConfirmPageComponent implements OnInit {

  public form: TransformableFormGroup = form;
  public error = false;
  constructor(private loginservice: LoginService, private router: Router) { }

  ngOnInit() {
  }

  singIn() {
    this.loginservice.signIn(this.form).subscribe(
        (response) => {
          localStorage.setItem('token', response.token);
          this.loginservice.reSetActive().subscribe(
          (response) => {
              this.router.navigate(['']);
              this.loginservice.activity.next({
                  isActive: true,
                  role: this.loginservice.getRole()
              });
          },
          (error) => {
            console.log(error);
          });
        },
        (error) => {
          this.form.markAsTouched();
          this.error = true;
        });
  }
}
