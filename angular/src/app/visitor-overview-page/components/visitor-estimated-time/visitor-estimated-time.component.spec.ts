import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisitorEstimatedTimeComponent } from './visitor-estimated-time.component';

describe('VisitorEstimatedTimeComponent', () => {
  let component: VisitorEstimatedTimeComponent;
  let fixture: ComponentFixture<VisitorEstimatedTimeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisitorEstimatedTimeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisitorEstimatedTimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
