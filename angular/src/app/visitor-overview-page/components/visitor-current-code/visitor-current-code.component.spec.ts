import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisitorCurrentCodeComponent } from './visitor-current-code.component';

describe('VisitorCurrentCodeComponent', () => {
  let component: VisitorCurrentCodeComponent;
  let fixture: ComponentFixture<VisitorCurrentCodeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisitorCurrentCodeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisitorCurrentCodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
