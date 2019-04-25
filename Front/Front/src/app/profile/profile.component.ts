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

  constructor( private tshirtService: TShirtService) { }

  ngOnInit() {
    this.tshirtService.getUsersThirt().subscribe(
        (res) => {
          this.TShirts = res;
        },
        (err) => {
          console.log(err);
        });
  }
  public deleteTshirt(id: number) {
      this.tshirtService.deleteTshirt(id).subscribe(
          (response) => {
              location.reload();
          },
      (error) => {
              console.log(error);
          });
  }
}
