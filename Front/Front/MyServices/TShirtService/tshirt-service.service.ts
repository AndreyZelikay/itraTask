import { Injectable } from '@angular/core';
import  {TShirt} from '../../MyModules/TShirt.module';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class TShirtService {

	private URL="http://localhost:8080/api/TShirts";

  constructor(private http:HttpClient) { }

  public CreateTShirt(TShirt:TShirt){
  	this.http.post(this.URL+"/add",TShirt).subscribe(
  		res=>{
          location.reload();
  			},
        err=>{
          alert("an error whith post");
        	}
     );
  }

  public GetAllTShirt(){
  	return this.http.get<TShirt[]>(this.URL+"/all");
  }

  public GetOneTShirt(id:Number){
    return this.http.get<TShirt>(this.URL+"/"+id);
  }

  public GetLastTShirt(){
    return this.http.get<TShirt>(this.URL+"/last");
  }
}
