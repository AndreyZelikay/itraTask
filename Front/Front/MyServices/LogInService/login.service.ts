import { Injectable } from '@angular/core';
import {LoginForm} from 'MyModules/login.module';
import {RoleForm} from 'MyModules/RoleForm';
import { HttpClient} from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { TransformableFormGroup} from 'src/helpers';
import {JwtHelperService} from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient, private jwtHelper: JwtHelperService) {}

    public authorization = new Subject<any>();
    public onAuthorizationChanged$ = this.authorization.asObservable();

  private url = 'http://localhost:8080';
  private Users: LoginForm[];
  public Activate;
  public signIn(form: TransformableFormGroup): Observable<any> {
    const requestBody: object = {};
    const fields = Object.keys(form.controls);
    for (const field of fields) {
      requestBody[field] = form.controls[field].value;
    }
    return this.http.post(this.url + '/login', requestBody);
  }
  public signOut() {
   localStorage.removeItem('token');
   this.authorization.next({
          onAuthorized: false
        });
  }
  public signUp(form: TransformableFormGroup): Observable<any> {
    const requestBody: object = {};
    const fields = Object.keys(form.controls);
    for (const field of fields) {
      requestBody[field] = form.controls[field].value;
    }
    return this.http.post(this.url + '/users/sign-up', requestBody);
  }
  public GetAll() {
    return this.http.get<LoginForm[]>(this.url + '/users/admin/all');
  }
  public DeleteUser(id: number) {
    this.http.delete(this.url + '/users/admin/' + id).subscribe(
        (res) => {
         location.reload();
      },
        (error) => {
          console.log(error);
      });
    localStorage.removeItem('token');
  }
  public SetRole(role: RoleForm) {
    this.http.post(this.url + '/users/admin/role', role).subscribe(
      (response) => {
          location.reload();
      },
      (error) => {
           alert('an error with post');
      });
  }
  public getRole() {
  const jd = this.jwtHelper.decodeToken(localStorage.getItem('token'));
  return jd.sub;
  }
  public SetActive(id: number) {
      return this.http.post(this.url + '/users/activity/set', id);
  }
  public GetActive() {
      return this.http.get(this.url + '/users/activity');
  }
  public activateUser(code: string): Observable<any> {
      return this.http.post(this.url + '/users/activate', code);
  }
  public reSetActive() {
    return this.http.get(this.url + '/users/activity/reset');
  }
}
