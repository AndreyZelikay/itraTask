import { Component, OnInit } from '@angular/core';
import {TShirtService} from '../../../MyServices/TShirtService/tshirt-service.service';
import {BasketModule} from '../../../MyModules/Basket.module';

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css']
})
export class BasketComponent implements OnInit {

  public basket: BasketModule;
  public number: number = 1;
  public isBasketEmpty: boolean = true;
  constructor(private tShirtService: TShirtService) {}

  ngOnInit() {
    this.tShirtService.getBasket().subscribe(
        (response) => {
          this.basket = response;
          console.log(response);
          if(this.basket != null)
            this.isBasketEmpty = false;
        });
  }

  public deleteFromBasket(id: number) {
    this.tShirtService.deleteFromBasket(id).subscribe(
        (res) => {
          this.tShirtService.getBasket().subscribe(
              (response) => {
                this.basket = response;
              },
              (error) => {
                console.log(error);
              });
        },
        (error) => {
          console.log(error);
        });
  }
}
