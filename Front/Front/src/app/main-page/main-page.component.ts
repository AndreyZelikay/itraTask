import { Component, OnInit } from '@angular/core';
import {TShirt} from '../../../MyModules/TShirt.module';
import {TShirtService} from '../../../MyServices/TShirtService/tshirt-service.service';
import {LoginService} from '../../../MyServices/LogInService/login.service';
import {Tag} from '../../../MyModules/TagModule';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})

export class MainPageComponent implements OnInit {

    public TShirts: TShirt[];
    public role: string;
    public popularTshirts: TShirt[];
    public lastTshirts: TShirt[];
    public isSearchNow: boolean = false;
    public searchedTshirts: TShirt[];
    public tags: Tag[];
    public tag: string;

  constructor(private tshirtService: TShirtService, private loginService: LoginService) { }

  ngOnInit() {
      this.tshirtService.getAllTags().subscribe(
          (response) => {
              this.tags = response;
          },
          (error) => {
              console.log(error);
          });
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
               this.TShirts[i].ratingNumber = result/res[i].ratings.length;
           }
           this.popularTshirts = this.TShirts;
           this.popularTshirts = this.popularTshirts.sort((a, b) => a.ratingNumber - b.ratingNumber).slice(this.popularTshirts.length - 5);
           if(this.TShirts.length > 5)
            this.lastTshirts = this.TShirts.slice(this.TShirts.length - 5);
           else
               this.lastTshirts = this.TShirts;
           },
          (err) => {
                console.log(err);
        });
      this.role = this.loginService.getRole();
  }

  public searchTag(body: string) {
      this.tag = body;
      this.isSearchNow = true;
      this.tshirtService.searchTag(body).subscribe(
          (response) => {
              this.searchedTshirts = response;
          },
          (error) => {
                this.isSearchNow = false;
          });
  }

  public changeState() {
      this.isSearchNow = false;
  }
}
