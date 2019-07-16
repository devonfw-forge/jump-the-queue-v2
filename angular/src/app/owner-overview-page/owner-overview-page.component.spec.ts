import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerOverviewPageComponent } from './owner-overview-page.component';

describe('OwnerOverviewPageComponent', () => {
  let component: OwnerOverviewPageComponent;
  let fixture: ComponentFixture<OwnerOverviewPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OwnerOverviewPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OwnerOverviewPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
