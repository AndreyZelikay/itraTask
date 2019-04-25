import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import {LoginService} from './login.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  constructor(
      public jwtHelper: JwtHelperService,
      private loginservice: LoginService,
      private router: Router
  ) {}

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    return !this.jwtHelper.isTokenExpired(token);
  }

  public isActive(): boolean {
    if (localStorage.getItem('token')) {
      this.loginservice.GetActive().subscribe(
          (response) => {
            this.loginservice.role.next({
              isRoleChanged: !response
            });
          });
      return true;
    }
    return false;
  }
  public canActivate(): boolean {
    if (!this.isAuthenticated()) {
      this.router.navigate(['login']);
      return false;
    }
    return this.isActive();
  }
}
