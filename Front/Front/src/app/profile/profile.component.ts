import { Component, OnInit } from '@angular/core';
import {TShirt} from '../../../MyModules/TShirt.module';
import {TShirtService} from '../../../MyServices/TShirtService/tshirt-service.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  public TShirts: TShirt[];
  public achievements: string;

  constructor( private tshirtService: TShirtService) { }

  ngOnInit() {
    this.tshirtService.getUsersThirt().subscribe(
        (res) => {
          this.TShirts = res;
        },
        (err) => {
          console.log(err);
        });
    this.tshirtService.getAchievements().subscribe(
        (response) => {
            this.achievements = response['cause'];
            console.log(response);
        });
  }
  public deleteTshirt(id: number) {
      this.tshirtService.deleteTshirt(id).subscribe(
          (response) => {
              this.tshirtService.getUsersThirt().subscribe(
                  (res) => {
                      this.TShirts = res;
                  },
                  (err) => {
                      console.log(err);
                  });
          },
      (error) => {
              console.log(error);
          });
  }
}
