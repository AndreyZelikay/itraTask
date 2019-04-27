import { Component, OnInit } from '@angular/core';
import {TShirt} from '../../../MyModules/TShirt.module';
import {TShirtService} from '../../../MyServices/TShirtService/tshirt-service.service';
declare var $: any;
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {ElementRef, ViewChild} from '@angular/core';
import {FormControl} from '@angular/forms';
import {MatAutocompleteSelectedEvent, MatChipInputEvent, MatAutocomplete} from '@angular/material';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';

@Component({
  selector: 'app-tshirt',
  templateUrl: './tshirt.component.html',
  styleUrls: ['./tshirt.component.css']
})
export class TShirtComponent implements OnInit {

  constructor(private tshirtService: TShirtService) {
      this.filteredTags = this.tagCtrl.valueChanges.pipe(
          startWith(null),
          map((tag: string | null) => tag ? this._filter(tag) : this.alltags.slice()));
  }

    public	TShirt: TShirt;
    public  ImgUrl: string;
    separatorKeysCodes: number[] = [ENTER, COMMA];
    tagCtrl = new FormControl();
    filteredTags: Observable<string[]>;
    tags: string[] = [];
    alltags: string[] = [];

    @ViewChild('tagInput') tagInput: ElementRef<HTMLInputElement>;
    @ViewChild('auto') matAutocomplete: MatAutocomplete;

  ngOnInit() {
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
     this.tshirtService.getAllTags().subscribe(
         (response) => {
             for (var i = 0 ; i < response.length; i++) {
                 this.alltags.push(response[i].body);
             }
             console.log(this.alltags);
         },
         (error) => {
            console.log(error);
         });
  }

  createTShirt(Description, Name, Tags) {
    this.ImgUrl = $('#meme').memeGenerator('save');
    this.TShirt = {
        id: 0,
        description: Description,
        url: this.ImgUrl,
        tags: this.tags,
        name: Name,
        comments: []
    };
    this.tshirtService.CreateTShirt(this.TShirt);
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
