import { Component, OnInit } from '@angular/core';
import {TShirt} from '../../../MyModules/TShirt.module';
import {TShirtService} from '../../../MyServices/TShirtService/tshirt-service.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

TShirts: TShirt[];

  constructor(private tshirtService: TShirtService) { }

  ngOnInit() {
      this.tshirtService.GetAllTShirt().subscribe(
          (res) => {
           this.TShirts = res;
            },
          (err) => {
                console.log(err);
        });
  }
}
