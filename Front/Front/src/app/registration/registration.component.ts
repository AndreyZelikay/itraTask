import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../../MyServices/LogInService/login.service';
import {TransformableFormGroup} from '../../helpers';
import { form } from './registration.form';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  public form: TransformableFormGroup = form;
  public isFormValid = true;
  public isNameExist = false;
  private result: string;
  public activationCode: string;

  constructor(private loginservice: LoginService) { }

  ngOnInit() {}

  public signUp(): void {
    if (this.form.valid) {
      this.loginservice.signUp(this.form).subscribe(
        (response) => {
          this.result = response.status;
          this.activationCode = '/' + response.code;
        },
        (error) => {
          console.log(error);
        }
      );
      if (this.result == 'Error!') {
          this.isNameExist = true;
          this.isFormValid = false;
      } else {
      this.isFormValid = true;
      }
      this.form.markAsTouched();
    } else {
      this.isFormValid = false;
      this.form.markAsTouched();
    }
  }
}
