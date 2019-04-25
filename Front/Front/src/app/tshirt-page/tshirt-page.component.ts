import { Component, OnInit } from '@angular/core';
import {TShirt} from '../../../MyModules/TShirt.module';
import {TShirtService} from '../../../MyServices/TShirtService/tshirt-service.service';
import {RouterModule, Routes, ActivatedRoute} from '@angular/router';
import {CommentModule} from '../../../MyModules/Comment.module';
import {TransformableFormGroup} from '../../helpers';
import {form} from '../registration/registration.form';
@Component({
  selector: 'app-tshirt-page',
  templateUrl: './tshirt-page.component.html',
  styleUrls: ['./tshirt-page.component.css']
})
export class TShirtPageComponent implements OnInit {

    public TShirt: TShirt;
    public ID: number;
    public form: TransformableFormGroup = form;
    public comments: CommentModule[];

  constructor(private tshirtService: TShirtService, private route: ActivatedRoute) {}

  ngOnInit() {
      this.ID = this.route.snapshot.params.id;
      this.tshirtService.GetOneTShirt(this.ID).subscribe(
        res => {
           this.TShirt = res;
        },
        err => {
           alert('an error whith get');
        }
    );
      this.tshirtService.getComments(this.ID).subscribe(
          (response) => {
              this.comments = response;
              },
          (error) => {
              console.log(error);
          });
  }
  public addComment() {
      if ( this.form.valid ) {
       this.tshirtService.addComment(this.form).subscribe();
      }
  }
}
