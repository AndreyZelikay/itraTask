import { Component, OnInit } from '@angular/core';
import {TShirt} from '../../../MyModules/TShirt.module';
import {TShirtService} from '../../../MyServices/TShirtService/tshirt-service.service';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {ElementRef, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {MatAutocompleteSelectedEvent, MatChipInputEvent, MatAutocomplete} from '@angular/material';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {ActivatedRoute, Router} from '@angular/router';
import {LoginService} from "../../../MyServices/LogInService/login.service";

declare var $: any;

@Component({
  selector: 'app-refactor-tshirt',
  templateUrl: './refactor-tshirt.component.html',
  styleUrls: ['./refactor-tshirt.component.css']
})
export class RefactorTshirtComponent implements OnInit {
  constructor(private tshirtService: TShirtService, private fb: FormBuilder, private router: Router, private  route: ActivatedRoute, private loginService: LoginService) {
    this.filteredTags = this.tagCtrl.valueChanges.pipe(
        startWith(null),
        map((tag: string | null) => tag ? this._filter(tag) : this.alltags.slice()));
  }
  public name: string;
  public form: FormGroup;
  public descr: string;
  public  TShirt: TShirt;
  public tShirt: TShirt;
  public  ImgUrl: string;
  public separatorKeysCodes: number[] = [ENTER, COMMA];
  public tagCtrl = new FormControl();
  public filteredTags: Observable<string[]>;
  public tags: string[] = [];
  public alltags: string[] = [];
  public selectedTheme: string;
  public themes: string[] = ['hipster', 'science', 'sport', 'history', 'premium'];

  @ViewChild('tagInput') tagInput: ElementRef<HTMLInputElement>;
  @ViewChild('auto') matAutocomplete: MatAutocomplete;
  ngOnInit() {
    this.tshirtService.GetOneTShirt(this.route.snapshot.params.id).subscribe(
        (response) => {
          this.tShirt = response;
          for (let i = 0; i < response.tags.length; i++) {
            this.tags[i] = response.tags[i].body;
          }
          $(document).ready( function() {
            $('#meme').memeGenerator('deserialize', response.json);
          });
        },
        (error) => {
          console.log(error);
        });
    this.tshirtService.getAllTags().subscribe(
        (response) => {
          for (let i = 0 ; i < response.length; i++) {
            this.alltags.push(response[i].body);
          }
          console.log(this.alltags);
        },
        (error) => {
          console.log(error);
        });
    $(document).ready( function() {
      $('#meme').memeGenerator({
        useBootstrap: true,
        layout: 'vertical',
        defaultTextStyle: {
          font: '\'Comic Sans\', Arial',
          lineHeight: 2
        },
        captions: [

        ],
        dragResizeEnabled: true,
        showAdvancedSettings: false
      });
    });
  }

  updateTShirt() {
    this.ImgUrl = $('#meme').memeGenerator('save');
    const requestBody: object = {
      description: this.descr,
      name: this.name,
      url: this.ImgUrl,
      tags: this.tags,
      theme: this.selectedTheme
    };
    this.tshirtService.updateTshirt(requestBody, this.route.snapshot.params.id).subscribe(
        res => {
          if(this.loginService.getRole() === 'ADMIN')
            this.router.navigate(['admin']);
          else
            this.router.navigate(['profile']);
        },
        err => {
          alert('an error whith create');
        }
    );
  }
  public add(event: MatChipInputEvent): void {
    if (!this.matAutocomplete.isOpen) {
      const input = event.input;
      const value = event.value;

      if ((value || '').trim()) {
        this.tags.push(value.trim());
      }

      if (input) {
        input.value = '';
      }

      this.tagCtrl.setValue(null);
    }
  }

  public remove(tag: string): void {
    const index = this.tags.indexOf(tag);

    if (index >= 0) {
      this.tags.splice(index, 1);
    }
  }

  public selected(event: MatAutocompleteSelectedEvent): void {
    this.tags.push(event.option.viewValue);
    this.tagInput.nativeElement.value = '';
    this.tagCtrl.setValue(null);
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.alltags.filter(tag => tag.toLowerCase().indexOf(filterValue) === 0);
  }
}
