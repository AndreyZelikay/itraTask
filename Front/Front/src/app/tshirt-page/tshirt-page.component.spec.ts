import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TShirtPageComponent } from './tshirt-page.component';

describe('TShirtPageComponent', () => {
  let component: TShirtPageComponent;
  let fixture: ComponentFixture<TShirtPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TShirtPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TShirtPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
