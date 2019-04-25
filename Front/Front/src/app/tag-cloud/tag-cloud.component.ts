import { Component, OnInit } from '@angular/core';
import { CloudData, CloudOptions } from 'angular-tag-cloud-module';
import {TShirtService} from '../../../MyServices/TShirtService/tshirt-service.service';
import {Tag} from '../../../MyModules/TagModule';

@Component({
  selector: 'app-tag-cloud',
  templateUrl: './tag-cloud.component.html',
  styleUrls: ['./tag-cloud.component.css']
})
export class TagCloudComponent implements OnInit {

  options: CloudOptions = {
    // if width is between 0 and 1 it will be set to the size of the upper element multiplied by the value
    width : 1000,
    height : 400,
    overflow: false,
  };

  data: CloudData[];

  public tags: Tag[];

  constructor( private tShirtService: TShirtService) { }

  ngOnInit() {
    this.data = [
        {text: 'Weight-8-link-color', weight: 30, link: 'https://google.com', color: '#ffaaee'}
    ];
    this.tShirtService.getAllTags().subscribe(
    (response) => {
      this.tags = response;
      for ( let i = 0; i < this.tags.length; i++) {
        this.data.push({text: this.tags[i].body, weight: this.tags[i].number, link: '', color: '#ffaaee'});
      }
    },
    (error) => {
     console.log(error);
    });
    //console.log(this.data);
   /* for ( let i = 0; i < this.tags.length; i++) {
      this.data.push({text: this.tags[i].body, weight: this.tags[i].numberOfUses, link: '', color: '#ffaaee'});
    }*/
  }
}
