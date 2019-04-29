import { Component, OnInit } from '@angular/core';
import {TShirtService} from '../../../MyServices/TShirtService/tshirt-service.service';
import {Tag} from '../../../MyModules/TagModule';

@Component({
  selector: 'app-tag-cloud',
  templateUrl: './tag-cloud.component.html',
  styleUrls: ['./tag-cloud.component.css']
})
export class TagCloudComponent implements OnInit {

  public tags: Tag[];

  constructor( private tShirtService: TShirtService) { }

  ngOnInit() {

  }
}
