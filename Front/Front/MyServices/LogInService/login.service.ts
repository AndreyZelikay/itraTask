import { Injectable } from '@angular/core';
import {LoginForm} from 'MyModules/login.module';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) { }

  private url="http://localhost:8080/users";

  public signIn(loginform:LoginForm){
  	this.http.post(this.url+"/sing-in",loginform).subscribe(
  		res=>{
  			 location.reload();
  		},
  		err=>{
			alert("an error whith post");
  		});
  }
  public signUp(loginform:LoginForm){
  	this.http.post(this.url+"/sign-up",loginform).subscribe(
  		res=>{
  			 location.reload();
  		},
  		err=>{
			alert("an error whith post");
  		});
  }
}
