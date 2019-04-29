import { Component, OnInit } from '@angular/core';
import {TShirt} from '../../../MyModules/TShirt.module';
import {TShirtService} from '../../../MyServices/TShirtService/tshirt-service.service';
import {LoginService} from '../../../MyServices/LogInService/login.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})

export class MainPageComponent implements OnInit {

    public TShirts: TShirt[];
    public role: string;
    public popularTshirts: TShirt[];

  constructor(private tshirtService: TShirtService, private loginService: LoginService) { }

  ngOnInit() {
      this.tshirtService.GetAllTShirt().subscribe(
          (res) => {
           this.TShirts = res;
           for (var i = 0; i < res.length; i++) {
               var result: number = 0;
               this.TShirts[i].rating = [true, true, true, true, true];
               for(var j = 0; j < res[i].ratings.length; j++)
                result += res[i].ratings[j].rating;
               for(var k=0; k<result/res[i].ratings.length; k++)
                this.TShirts[i].rating[k] = false;
           }
           this.popularTshirts = this.TShirts;
           this.popularTshirts.sort((a, b) => {return a.rating.length - b.rating.length}).slice(this.popularTshirts.length - 5);
           console.log(this.popularTshirts);
           },
          (err) => {
                console.log(err);
        });
      this.role = this.loginService.getRole();
  }
}
