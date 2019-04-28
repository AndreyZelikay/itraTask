import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RefactorTshirtComponent } from './refactor-tshirt.component';

describe('RefactorTshirtComponent', () => {
  let component: RefactorTshirtComponent;
  let fixture: ComponentFixture<RefactorTshirtComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RefactorTshirtComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RefactorTshirtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
