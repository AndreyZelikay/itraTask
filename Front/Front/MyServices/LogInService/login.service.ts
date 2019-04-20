import { Injectable } from '@angular/core';
import {LoginForm} from 'MyModules/login.module';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

    public authorization = new Subject<any>();
    public onAuthorizationChanged$ = this.authorization.asObservable();

  constructor(private http:HttpClient) {}

  private url="http://localhost:8080";
  private Users:LoginForm[];

  public signIn(loginform:LoginForm){
  	this.http.post(this.url+"/login",loginform).subscribe(
  		(response) => {
        localStorage.setItem('token', response['token']);
        this.authorization.next({
          'onAuthorized': true
        });
      },
      (error) => {
        console.log(error)
      }
  )}
  public signOut(){
   localStorage.removeItem('token');
        this.authorization.next({
          'onAuthorized': false
        });  
  }
  public signUp(loginform:LoginForm){
  	this.http.post(this.url+"/users/sign-up",loginform).subscribe(
  		res=>{
  			 location.reload();
  		},
  		err=>{
			alert("an error whith post");
  		});
  }
  public GetAll(){
    return this.http.get<LoginForm[]>(this.url+"/users/admin/all")
  }
  public DeleteUser(id:Number){
    this.http.delete(this.url+"/users/admin/"+id).subscribe(
      res=>{
         location.reload();
      },
      err=>{
      alert("an error whith delete");
      });
  }
  //public SetRole(id:Number,role:String){
    //this.http.post(this.url+"/users/admin/",role)
  //}
}
