import { Component, OnInit } from '@angular/core';
import {TShirt} from '../../../MyModules/TShirt.module';
import {TShirtService} from '../../../MyServices/TShirtService/tshirt-service.service';
declare var $: any;
@Component({
  selector: 'app-tshirt',
  templateUrl: './tshirt.component.html',
  styleUrls: ['./tshirt.component.css']
})
export class TShirtComponent implements OnInit {

    public	TShirt: TShirt;
    public  ImgUrl: string;

  constructor(private tshirtService: TShirtService) {
  }

  ngOnInit() {
     $(document).ready(function() {
            $('#meme').memeGenerator({
      useBootstrap: true,
      layout: 'vertical',
      defaultTextStyle: {
        font: '\'Comic Sans\', Arial',
        lineHeight: 2
      },
      captions: [

      ],
      dragResizeEnabled: true
    });
    });
  }

  createTShirt(Description, Name, Tags) {
    this.ImgUrl = $('#meme').memeGenerator('save');
    this.TShirt = {
        id: 0,
        description: Description,
        url: this.ImgUrl,
        tags: Tags,
        name: Name
    };
    this.tshirtService.CreateTShirt(this.TShirt);
  }
}
