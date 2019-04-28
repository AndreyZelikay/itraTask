import { Component, OnInit } from '@angular/core';
import {TShirtService} from '../../../MyServices/TShirtService/tshirt-service.service';
import {ActivatedRoute} from '@angular/router';
import {FormBuilder} from '@angular/forms';
import {TShirt} from '../../../MyModules/TShirt.module';

@Component({
  selector: 'app-manage-user-profile',
  templateUrl: './manage-user-profile.component.html',
  styleUrls: ['./manage-user-profile.component.css']
})
export class ManageUserProfileComponent implements OnInit {

  public userId: number;
  public usersTshirt: TShirt[];
  constructor( private tshirtService: TShirtService, private route: ActivatedRoute, private fb: FormBuilder) { }

  ngOnInit() {
    this.userId = this.route.snapshot.params.id;
    this.tshirtService.getUsersTshirtByAdmin(this.userId).subscribe(
        (response) => {
          this.usersTshirt = response;
        },
        (error) => {
          console.log(error);
        });
  }
  public deleteTshirt(id: number) {
    this.tshirtService.deleteTshirt(id).subscribe(
        (res) => {
          this.tshirtService.getUsersTshirtByAdmin(this.userId).subscribe(
              (response) => {
                this.usersTshirt = response;
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
