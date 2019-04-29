import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../../MyServices/LogInService/login.service';
import {TransformableFormGroup} from '../../helpers';
import {form} from './payment.forms';
import {Router} from "@angular/router";

@Component({
  selector: 'app-payment-page',
  templateUrl: './payment-page.component.html',
  styleUrls: ['./payment-page.component.css']
})
export class PaymentPageComponent implements OnInit {

  public email: string;
  public status: string;
  public cardNumber: string;
  public cvc: string;
  public form: TransformableFormGroup = form;
  constructor(private loginservice: LoginService,
              private router: Router) { }

  ngOnInit() {
  }

  chargeCreditCard() {
    var year = document.getElementById("year");
    var selectedYear = year.options[year.selectedIndex].value;
    var month = document.getElementById("month");
    var selectedMonth = month.options[month.selectedIndex].value;

    if (this.form.valid) {
      this.loginservice.buy(this.form).subscribe(
          (response) => {
            this.status = "success";
          },
          (error) => {
            this.status = "error";
          });
      (<any>window).Stripe.card.createToken({
        number: this.cardNumber,
        exp_month: selectedMonth,
        exp_year: '20' + selectedYear,
        cvc: this.cvc
      }, (status: number, response: any) => {
        if (status === 200) {
          let token = response.id;
          this.loginservice.chargeCard(token).subscribe(
              (response) => {
                this.router.navigate(['/success-pay']);
              },
                    (error) => {
                  alert("an error with pay");
          });
        } else {
          alert(response.error.message);
        }
      });
    }
  }
}
