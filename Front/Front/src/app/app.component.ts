import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../MyServices/LogInService/login.service';
import {AuthserviceService} from '../../MyServices/LogInService/authservice.service'
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  
  public title = 'Front';
  public isAuthorized: boolean = false;
  private authService:AuthserviceService;
  
  constructor(private loginService: LoginService)
  {}

  public ngOnInit()
  {
  	this.loginService.onAuthorizationChanged$.subscribe(
  		(response) => {
  			this.isAuthorized = response['onAuthorized'];
  		}
  	);
    this.isAuthorized=this.authService.isAuthenticated();
  }
}
