import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QrOverviewPageComponent } from './qr-overview-page.component';

describe('QrOverviewPageComponent', () => {
  let component: QrOverviewPageComponent;
  let fixture: ComponentFixture<QrOverviewPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QrOverviewPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QrOverviewPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
