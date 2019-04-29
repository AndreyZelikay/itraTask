import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccessfulPayComponent } from './successful-pay.component';

describe('SuccessfulPayComponent', () => {
  let component: SuccessfulPayComponent;
  let fixture: ComponentFixture<SuccessfulPayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SuccessfulPayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuccessfulPayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
