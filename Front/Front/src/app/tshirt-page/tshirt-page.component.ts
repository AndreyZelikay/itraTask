import {Component, EventEmitter, OnInit} from '@angular/core';
import {TShirt} from '../../../MyModules/TShirt.module';
import {TShirtService} from '../../../MyServices/TShirtService/tshirt-service.service';
import {ActivatedRoute} from '@angular/router';
import {form} from '../registration/registration.form';
import {FormBuilder, FormGroup} from '@angular/forms';
import {LoginService} from "../../../MyServices/LogInService/login.service";
@Component({
  selector: 'app-tshirt-page',
  templateUrl: './tshirt-page.component.html',
  styleUrls: ['./tshirt-page.component.css']
})
export class TShirtPageComponent implements OnInit {

    public TShirt: TShirt;
    public ID: number;
    public isAuthorized: boolean = false;
    public form: FormGroup = form;
    public basketform: FormGroup;
    public isUserCanAddComment: boolean = true;
    public starList: boolean[] = [true, true, true, true, true ];
    public starListRating: boolean[] = [true, true, true, true, true ];
    public  rating: number;
    public tShirtRating: number;
    public isUserCanSetRating: boolean = true;
    public isUserCanSetLike: boolean = true;
    public sizes1: string[] = [ 'XS', 'S', 'M', 'L'];
    public sizes2: string[] = ['XL', 'XXl', 'XXXL', '4XL'];
    public size: string;

    constructor(private tshirtService: TShirtService, private route: ActivatedRoute, private fb: FormBuilder, private loginService: LoginService) {
    }

    ngOnInit() {
        if (localStorage.getItem('token')) {
            this.isAuthorized = true;
        }
        this.ID = this.route.snapshot.params.id;
        this.tshirtService.GetOneTShirt(this.ID).subscribe(
            res => {
                this.TShirt = res;
            },
            err => {
               console.log(err);
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
                    this.tshirtService.GetOneTShirt(this.ID).subscribe(
                        res => {
                            this.TShirt = res;
                        },
                        err => {
                            console.log(err);
                        });
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
                this.tshirtService.GetOneTShirt(this.ID).subscribe(
                    res => {
                        this.TShirt = res;
                    },
                    err => {
                        console.log(err);
                    });
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
    public setSize(size: string) {
       this.size = size;
    }
    public setRating() {
        this.tshirtService.setRating(this.ID, this.rating).subscribe(
            (res) => {
                this.tshirtService.getRating(this.ID).subscribe(
                    (response) => {
                        this.tShirtRating = response;
                        for (var i = 0; i < this.tShirtRating; i++)
                            this.starList[i] = false;
                    },
                    (error) => {
                        console.log(error);
                    });
            },
            (error) => {
                console.log(error);
                this.isUserCanSetRating = false;
            }
        );
        this.isUserCanSetRating = false;
    }
    public setLike(commentId) {
        this.tshirtService.setLike(commentId).subscribe(
            (response) => {
                this.isUserCanSetLike = true;
                this.tshirtService.GetOneTShirt(this.ID).subscribe(
                    res => {
                        this.TShirt = res;
                    },
                    err => {
                        console.log(err);
                    });
            },
            (error) => {
                this.isUserCanSetLike = false;
            }
        );
    }
    public toBasket() {
        this.basketform = this.fb.group({
            sizes: this.size,
            tShirtID: this.ID
            });
        this.tshirtService.setBasket(this.basketform).subscribe();
    }
}
