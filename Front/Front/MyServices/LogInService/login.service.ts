import { Injectable } from '@angular/core';
import {LoginForm} from 'MyModules/login.module';
import {RoleForm} from 'MyModules/RoleForm';
import { HttpClient} from '@angular/common/http';
import { Observable, of, Subject } from 'rxjs';
import { TransformableFormGroup} from 'src/helpers';
import {JwtHelperService} from '@auth0/angular-jwt';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public role = new Subject<any>();
  public onRoleChanged$ = this.role.asObservable();
  public activity = new Subject<any>();
  public onActivityChanged$ = this.activity.asObservable();

  constructor(private http: HttpClient, private jwtHelper: JwtHelperService, private router: Router) {}

  private url = 'http://localhost:8080';
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
   this.activity.next({
      isActive: false,
      role: ''
    });
   this.router.navigate(['login']);
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
    return this.http.get<LoginForm[]>(this.url + '/admin/all');
  }
  public DeleteUser(id: number) {
    return this.http.delete(this.url + '/admin/' + id);
  }
  public SetRole(role: RoleForm) {
    return this.http.post(this.url + '/admin/role', role);
  }
  public getRole() {
    if (localStorage.getItem('token')) {
      return  JSON.parse(this.jwtHelper.decodeToken(localStorage.getItem('token')).sub)['role'].replace('[', '').replace(']', '');
    } else {
      return '';
    }
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
  public buy(form: TransformableFormGroup) {
    const requestBody: object = {};
    const fields = Object.keys(form.controls);
    for (const field of fields) {
      requestBody[field] = form.controls[field].value;
    }
    console.log(requestBody);
    return this.http.post(this.url + '/users/email', requestBody['email']);
  }
}
