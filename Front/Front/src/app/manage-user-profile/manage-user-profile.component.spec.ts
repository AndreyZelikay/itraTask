import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageUserProfileComponent } from './manage-user-profile.component';

describe('ManageUserProfileComponent', () => {
  let component: ManageUserProfileComponent;
  let fixture: ComponentFixture<ManageUserProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageUserProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageUserProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
