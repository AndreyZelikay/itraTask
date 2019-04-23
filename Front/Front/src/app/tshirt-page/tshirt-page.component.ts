import { Component, OnInit } from '@angular/core';
import {TShirt} from '../../../MyModules/TShirt.module';
import {TShirtService} from '../../../MyServices/TShirtService/tshirt-service.service';
import {RouterModule, Routes, ActivatedRoute} from '@angular/router';
@Component({
  selector: 'app-tshirt-page',
  templateUrl: './tshirt-page.component.html',
  styleUrls: ['./tshirt-page.component.css']
})
export class TShirtPageComponent implements OnInit {

  TShirt: TShirt;
  ID: number;

  constructor(private tshirtService: TShirtService, private route: ActivatedRoute) {
    this.ID = this.route.snapshot.params.id;
  }

  ngOnInit() {
  	this.tshirtService.GetOneTShirt(this.ID).subscribe(
        res => {
           this.TShirt = res;
        },
        err => {
           alert('an error whith get');
        }
    );
  }
}
