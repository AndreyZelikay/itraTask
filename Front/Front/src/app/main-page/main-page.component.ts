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
    public starList: boolean[];

  constructor(private tshirtService: TShirtService, private loginService: LoginService) { }

  ngOnInit() {
      this.tshirtService.GetAllTShirt().subscribe(
          (res) => {
           this.TShirts = res;
           for(var i = 0; i < res.length; i++) {
               var result: number = 0;
               for(var j = 0; j < res[i].ratings.length; j++)
                result += res[i].ratings[j].rating;
               this.TShirts[i].rating = result/res[i].ratings.length;
               console.log(this.TShirts[i].rating);
           }
              console.log(this.TShirts[0].rating);
           },
          (err) => {
                console.log(err);
        });
      this.role = this.loginService.getRole();
  }
}
