import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../../MyServices/LogInService/login.service';
import {TransformableFormGroup} from '../../helpers';
import {form} from './payment.forms';

@Component({
  selector: 'app-payment-page',
  templateUrl: './payment-page.component.html',
  styleUrls: ['./payment-page.component.css']
})
export class PaymentPageComponent implements OnInit {

  public email: string;
  public status: string;
  public form: TransformableFormGroup = form;
  constructor(private loginservice: LoginService ) { }

  ngOnInit() {
  }

  public buy() {
    if (this.form.valid) {
    this.loginservice.buy(this.form).subscribe(
        (response) => {
          this.status = "success";
        },
        (error) => {
          this.status = "error";
        });
    }
  }
}
