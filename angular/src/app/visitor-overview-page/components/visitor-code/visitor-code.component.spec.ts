import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisitorCodeComponent } from './visitor-code.component';

describe('VisitorCodeComponent', () => {
  let component: VisitorCodeComponent;
  let fixture: ComponentFixture<VisitorCodeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisitorCodeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisitorCodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
