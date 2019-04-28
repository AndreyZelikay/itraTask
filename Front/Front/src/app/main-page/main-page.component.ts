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
    public starList: boolean[][] = [];

  constructor(private tshirtService: TShirtService, private loginService: LoginService) { }

  ngOnInit() {
      this.tshirtService.GetAllTShirt().subscribe(
          (res) => {
           this.TShirts = res;
           for (var i = 0; i < this.TShirts.length; i++) {
               this.tshirtService.getRating(this.TShirts[i].id).subscribe(
                   (response) => {
                       for (var j = 0; j < response; j++)
                           this.starList[i][j] = false;
                   },
                   (error) => {
                       console.log(error);
                   });
            }
            },
          (err) => {
                console.log(err);
        });
      this.role = this.loginService.getRole();
  }
}
