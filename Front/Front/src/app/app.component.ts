import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../MyServices/LogInService/login.service';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {TShirtService} from '../../MyServices/TShirtService/tshirt-service.service';
import {TShirt} from '../../MyModules/TShirt.module';
import {Router} from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  public title = 'Front';
  public isAuthorized = false;
  public role: string;
  public myControl = new FormControl();
  public options: string[] = ['One', 'Two', 'Three'];
  public filteredOptions: Observable<string[]>;
  public searchedTshirt: TShirt[];
  public isSearchNow: boolean = false;

  constructor(
      private loginService: LoginService,
      private tshirtService: TShirtService,
      private router: Router
  ) {}

  public ngOnInit() {
      this.loginService.onRoleChanged$.subscribe(
          (response) => {
              if (response['isRoleChanged']) {
                  this.loginService.signOut();
                  this.router.navigate(['/confirm']);
              }
          }
      );
      this.loginService.onActivityChanged$.subscribe(
          (response) => {
              this.isAuthorized = response['isActive'];
              this.role = response['role'];
          });
      this.role = this.loginService.getRole();
      if (localStorage.getItem('token')) {
          this.isAuthorized = true;
      }
      this.filteredOptions = this.myControl.valueChanges
          .pipe(
              startWith(''),
              map(value => this._filter(value))
          );
      this.myControl.valueChanges.subscribe(
          (result) => {
              if (result.length > 0) {
                  this.isSearchNow = true;
                  this.tshirtService.searchTshirt(result).subscribe(
                      (response) => {
                          this.searchedTshirt = response;
                          console.log(response);
                      });
              } else {
                  this.isSearchNow = false;
              }
            });
  }
  private _filter(value: string): string[] {
      const filterValue = value.toLowerCase();
      return this.options.filter(option => option.toLowerCase().includes(filterValue));
    }

  singOut() {
      this.loginService.signOut();
  }
}
