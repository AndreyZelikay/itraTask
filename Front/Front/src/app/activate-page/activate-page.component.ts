import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {LoginService} from '../../../MyServices/LogInService/login.service';

@Component({
  selector: 'app-activate-page',
  templateUrl: './activate-page.component.html',
  styleUrls: ['./activate-page.component.css']
})
export class ActivatePageComponent implements OnInit {

  private isActivated: boolean = false;
  private loading: boolean = true;
  private uid: string;

  constructor(
      private activatedRoute: ActivatedRoute,
      private loginService: LoginService
  ) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
        (response) => {
          this.uid = response['uid'];
          console.log(this.uid);
          this.loginService.activateUser(this.uid).subscribe(
              (response) => {
                this.isActivated  = true;
                this.loading = false;
              },
              (error) => {
                this.loading = false;
                console.log(error);
              }
          );
        }
    );
  }
}
