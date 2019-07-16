import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisitorOverviewPageComponent } from './visitor-overview-page.component';

describe('VisitorOverviewPageComponent', () => {
  let component: VisitorOverviewPageComponent;
  let fixture: ComponentFixture<VisitorOverviewPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisitorOverviewPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisitorOverviewPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
