import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../../MyServices/LogInService/login.service';
import {TransformableFormGroup} from '../../helpers';
import { form } from './registration.form';
import {Router} from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  public form: TransformableFormGroup = form;
  public isFormValid = true;
  public registrationErrors: string;

  constructor(private loginservice: LoginService, private router: Router) {
  }

  ngOnInit() {
  }

  public signUp(): void {
    if (this.form.valid) {
      this.loginservice.signUp(this.form).subscribe(
          (response) => {
            this.router.navigate(['success']);
          },
          (error) => {
            this.registrationErrors = error.error['cause'];
            console.log(error);
            this.form.markAsTouched();
            this.isFormValid = false;
          }
      );
    }
  }
}
