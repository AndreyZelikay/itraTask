import { Component, OnInit } from '@angular/core';
import {LoginService} from 'MyServices/LogInService/login.service';
import {LoginForm} from 'MyModules/login.module';
import {RoleForm} from 'MyModules/RoleForm';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  public Users: LoginForm[];
  private Role: RoleForm;
  public ChosenRole: string;
  constructor(private loginService: LoginService) { }

  ngOnInit() {
      this.loginService.GetAll().subscribe(
     (response) => {
         this.Users = response;
     },
     (error) => {
         console.log(error);
     });
  }
  delete(id) {
      this.loginService.DeleteUser(id).subscribe(
          (res) => {
              this.loginService.GetAll().subscribe(
                  (response) => {
                      this.Users = response;
                  },
                  (error) => {
                      console.log(error);
                  });
          },
          (error) => {
              console.log(error);
          });
  }
  changeRole(event: any) {
      this.ChosenRole = event.target.value;
  }
  setRole(Id) {
      this.Role = {
          id: Id,
          role: this.ChosenRole
      };
      this.loginService.SetRole(this.Role).subscribe(
          (res) => {
              this.loginService.GetAll().subscribe(
                  (response) => {
                      this.Users = response;
                  },
                  (error) => {
                      console.log(error);
                  });
          },
          (error) => {
              alert('an error with post');
          });
      this.loginService.SetActive(Id).subscribe(
          (response) => {
          },
          (error1) => {
              alert('an error with post');
          }
      );
  }
}
