import {Component, EventEmitter, OnInit} from '@angular/core';
import {TShirt} from '../../../MyModules/TShirt.module';
import {TShirtService} from '../../../MyServices/TShirtService/tshirt-service.service';
import {ActivatedRoute} from '@angular/router';
import {form} from '../registration/registration.form';
import {FormBuilder, FormGroup} from '@angular/forms';
@Component({
  selector: 'app-tshirt-page',
  templateUrl: './tshirt-page.component.html',
  styleUrls: ['./tshirt-page.component.css']
})
export class TShirtPageComponent implements OnInit {

    public TShirt: TShirt;
    public ID: number;
    public form: FormGroup = form;
    public isUserCanAddComment: boolean = true;
    public starList: boolean[] = [true, true, true, true, true ];
    public starListRating: boolean[] = [true, true, true, true, true ];
    public  rating: number;
    public tShirtRating: number;
    public isUserCanSetRating: boolean = true;
    public isUserCanSetLike: boolean = true;

    constructor(private tshirtService: TShirtService, private route: ActivatedRoute, private fb: FormBuilder) {
    }

    ngOnInit() {
        this.ID = this.route.snapshot.params.id;
        this.tshirtService.GetOneTShirt(this.ID).subscribe(
            res => {
                this.TShirt = res;
                console.log(this.TShirt);
            },
            err => {
                this.isUserCanAddComment = false;
            });
        this.form = this.fb.group({
            comment: [null],
            tShirtId: this.ID
        });
        this.tshirtService.getRating(this.ID).subscribe(
            (response) => {
                this.tShirtRating = response;
                for (var i = 0; i < this.tShirtRating; i++)
                    this.starList[i] = false;
            },
            (error) => {
                console.log(error);
            });
    }

    public addComment() {
        if (this.form.valid) {
            this.tshirtService.addComment(this.form).subscribe(
                (response) => {
                },
                (error) => {
                    this.isUserCanAddComment = false;
                }
            );
        }
    }

    public deleteComment() {
        this.tshirtService.deleteComment(this.ID).subscribe(
            (response) => {
                this.isUserCanAddComment = true;
            },
            (error) => {
                this.isUserCanAddComment = false;
                console.log(error);
            }
        );
    }
    setStar(data: any) {
        this.rating = data + 1;
        for (var i = 0; i <= 4; i++) {
            if (i <= data) {
                this.starListRating[i] = false;
            } else {
                this.starListRating[i] = true;
            }
        }
    }
    public setRating() {
        this.tshirtService.setRating(this.ID, this.rating).subscribe(
            (response) => {},
            (error) => {
                console.log(error);
            }
        );
        this.isUserCanSetRating = false;
    }
    public setLike(commentId) {
        this.tshirtService.setLike(this.ID, commentId).subscribe(
            (response) => {
                this.isUserCanSetLike = true;
            },
            (error) => {
                this.isUserCanSetLike = false;
            }
        );
    }
}
