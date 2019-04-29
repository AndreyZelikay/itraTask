import { Injectable } from '@angular/core';
import  {TShirt} from '../../MyModules/TShirt.module';
import { HttpClient} from '@angular/common/http';
import {Tag} from '../../MyModules/TagModule';
import {Router} from '@angular/router';
import {FormGroup} from '@angular/forms';
import {Subject} from 'rxjs';
import {BasketModule} from '../../MyModules/Basket.module';

@Injectable({
  providedIn: 'root'
})
export class TShirtService {
    private tShirtUrl = 'http://localhost:8080/TShirts';
    private feedBackUrl = 'http://localhost:8080/feedback';
    private adminUrl = 'http://localhost:8080/admin';
    private searchUrl = 'http://localhost:8080/search';
    private basketUrl = 'http://localhost:8080/basket';
    private userUrl = 'http://localhost:8080/users';

  constructor(private http: HttpClient, private router: Router) { }

  public comment = new Subject<any>();

  public CreateTShirt(requestBody) {
      console.log(requestBody);
      return this.http.post(this.tShirtUrl + '/add', requestBody);
  }

  public GetAllTShirt() {
      return this.http.get<TShirt[]>(this.tShirtUrl + '/all');
  }

  public GetOneTShirt(id: number) {
    return this.http.get<TShirt>(this.tShirtUrl + '/TShirt/' + id);
  }

  public getAllTags() {
      return this.http.get<Tag[]>(this.feedBackUrl + '/tag/all');
  }

  public searchTshirt(searchParam: string) {
      return this.http.post<TShirt[]>(this.searchUrl + '/tshirt', searchParam);
  }

  public getUsersThirt() {
   return  this.http.get<TShirt[]>(this.tShirtUrl + '/user/tshirts');
  }

  public getUsersTshirtByAdmin(id: number) {
      return this.http.get<TShirt[]>(this.adminUrl + '/user/' + id + '/tshirts');
  }

  public deleteTshirt(id: number) {
   return this.http.delete(this.tShirtUrl + '/delete/' + id);
  }

  public addComment(form: FormGroup) {
      const requestBody: object = {};
      const fields = Object.keys(form.controls);
      for (const field of fields) {
          requestBody[field] = form.controls[field].value;
      }
      return this.http.post(this.feedBackUrl + '/comments/add', requestBody);
  }

  public deleteComment(ID: number) {
      return this.http.delete(this.feedBackUrl + '/comments/del/' + ID);
  }

  public setRating(id: number, rating) {
      return this.http.post(this.feedBackUrl + '/feedback/rating/set/' + id, rating);
  }

  public getRating(ID: number) {
        return this.http.get<number>(this.feedBackUrl + '/feedback/rating/get/' + ID);
    }

  public  setLike(commentId: number) {
      return this.http.post(this.feedBackUrl + '/feedback/likes/set', commentId);
  }

  public getBasket() {
      return this.http.get<BasketModule>(this.basketUrl  + '/get');
  }

  public deleteFromBasket(id: number) {
        return this.http.delete(this.basketUrl + '/delete/' + id);
  }

  public setBasket(form: FormGroup) {
      const requestBody: object = {};
      const fields = Object.keys(form.controls);
      for (const field of fields) {
          requestBody[field] = form.controls[field].value;
      }
      return this.http.post(this.basketUrl + '/put', requestBody);
  }

  public updateTshirt(requestBody: object, id: number) {
        return this.http.post(this.tShirtUrl + '/update/' + id, requestBody);
    }

  public getAchievements() {
      return this.http.get<string>(this.userUrl + '/achievements');
  }
}
