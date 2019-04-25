import { Injectable } from '@angular/core';
import  {TShirt} from '../../MyModules/TShirt.module';
import { HttpClient} from '@angular/common/http';
import {Tag} from '../../MyModules/TagModule';
import {Router} from '@angular/router';
import {CommentModule} from '../../MyModules/Comment.module';
import {TransformableFormGroup} from '../../src/helpers';

@Injectable({
  providedIn: 'root'
})
export class TShirtService {
    private URL = 'http://localhost:8080/TShirts';

  constructor(private http: HttpClient, private router: Router) { }

  public CreateTShirt(TShirt: TShirt) {
      this.http.post(this.URL + '/add', TShirt).subscribe(
          res => {
          location.reload();
          },
        err => {
          alert('an error whith create');
          }
     );
      this.router.navigate(['profile']);
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

  public getComments(id: number) {
        return this.http.get<CommentModule[]>(this.URL + '/comments/get/' + id);
    }

  public addComment(form: TransformableFormGroup){
      const requestBody: object = {};
      const fields = Object.keys(form.controls);
      for (const field of fields) {
          requestBody[field] = form.controls[field].value;
      }
      return this.http.post(this.URL + '/comments/add', requestBody);
  }
}
