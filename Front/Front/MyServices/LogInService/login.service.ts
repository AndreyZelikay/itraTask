import { Injectable } from '@angular/core';
import {LoginForm} from 'MyModules/login.module';
import {RoleForm} from 'MyModules/RoleForm';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, Subject } from 'rxjs';
import { TransformableFormGroup, TransformableFormControl } from 'src/helpers';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

    public authorization = new Subject<any>();
    public onAuthorizationChanged$ = this.authorization.asObservable();

  constructor(private http: HttpClient) {}

  private url = 'http://localhost:8080';
  private Users: LoginForm[];

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
  public SetActive(id: number) {
      return this.http.post(this.url + '/users/activity/set', id);
  }
  public GetActive(id: number) {
      return this.http.get(this.url + '/users/activity/' + id);
  }
}
