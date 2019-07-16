import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerNextCodeComponent } from './owner-next-code.component';

describe('OwnerNextCodeComponent', () => {
  let component: OwnerNextCodeComponent;
  let fixture: ComponentFixture<OwnerNextCodeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OwnerNextCodeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OwnerNextCodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
