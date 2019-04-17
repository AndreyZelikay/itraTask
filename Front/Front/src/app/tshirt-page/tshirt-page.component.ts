import { Component, OnInit } from '@angular/core';
import {TShirt} from '../../../MyModules/TShirt.module';
import {TShirtService} from '../../../MyServices/TShirtService/tshirt-service.service';
@Component({
  selector: 'app-tshirt-page',
  templateUrl: './tshirt-page.component.html',
  styleUrls: ['./tshirt-page.component.css']
})
export class TShirtPageComponent implements OnInit {

  TShirts:TShirt[];

  constructor(private tshirtService: TShirtService) { }

  ngOnInit() {
  	this.tshirtService.GetAllTShirt().subscribe(res =>{
           this.TShirts=res;
        },
        err =>{
           alert("an error whith get");
        });
  }
}
