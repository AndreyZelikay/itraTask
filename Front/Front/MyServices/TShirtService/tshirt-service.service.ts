import { Injectable } from '@angular/core';
import  {TShirt} from '../../MyModules/TShirt.module';
import { HttpClient} from '@angular/common/http';
import {Tag} from '../../MyModules/TagModule';
import {Router} from '@angular/router';
import {FormGroup} from '@angular/forms';
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TShirtService {
    private URL = 'http://localhost:8080/TShirts';

  constructor(private http: HttpClient, private router: Router) { }

  public comment = new Subject<any>();
  public onCommentChanged$ = this.comment.asObservable();

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

  public addComment(form: FormGroup) {
      const requestBody: object = {};
      const fields = Object.keys(form.controls);
      for (const field of fields) {
          requestBody[field] = form.controls[field].value;
      }
      return this.http.post(this.URL + '/comments/add', requestBody);
  }

  public deleteComment(ID: number) {
      return this.http.delete(this.URL + '/comments/del/' + ID);
  }

  public setRating(id: number, rating) {
      return this.http.post(this.URL + '/feedback/rating/set/' + id, rating);
  }

  public getRating(ID: number) {
        return this.http.get<number>(this.URL + '/feedback/rating/get/' + ID);
    }

  public  setLike(ID: number, commentId: number) {
      return this.http.post(this.URL + '/feedback/likes/set/' + ID, commentId);
  }
}
