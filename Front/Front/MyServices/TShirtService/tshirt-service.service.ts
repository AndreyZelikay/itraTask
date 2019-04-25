import { Injectable } from '@angular/core';
import  {TShirt} from '../../MyModules/TShirt.module';
import { HttpClient} from '@angular/common/http';
import {Tag} from '../../MyModules/TagModule';

@Injectable({
  providedIn: 'root'
})
export class TShirtService {
    private URL = 'http://localhost:8080/TShirts';

  constructor(private http: HttpClient) { }

  public CreateTShirt(TShirt: TShirt) {
      this.http.post(this.URL + '/add', TShirt).subscribe(
          res => {
          location.reload();
          },
        err => {
          alert('an error whith post');
          }
     );
  }

  public GetAllTShirt() {
      return this.http.get<TShirt[]>(this.URL + '/all');
  }

  public GetOneTShirt(id: number) {
    return this.http.get<TShirt>(this.URL + '/TShirt/' + id);
  }

  public getAllTags() {
      return this.http.get<Tag[]>(this.URL + '/tag/all');
  }

  public searchTshirt(searchParam: string) {
      return this.http.post<TShirt[]>('http://localhost:8080/search/tshirt', searchParam);
  }

  public getUsersThirt() {
   return  this.http.get<TShirt[]>(this.URL + '/user/tshirts');
  }

  public deleteTshirt(id: number) {
   return this.http.delete(this.URL + '/delete/' + id);
  }
}
