import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import {LoginService} from './login.service';

@Injectable({
  providedIn: 'root'
})
export class AuthserviceService {
  constructor(public jwtHelper: JwtHelperService, private loginservice: LoginService) {}
  public isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    return !this.jwtHelper.isTokenExpired(token);
  }
  public isActive(): boolean {
    this.loginservice.GetActive().subscribe(
        (response) => {
          console.log(response['activity']);
          return response['activity'];
        },
        (error) => {
          console.log(error);
        });
    return true;
  }
}
