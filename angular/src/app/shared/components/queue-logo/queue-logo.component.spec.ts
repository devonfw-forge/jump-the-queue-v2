import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QueueLogoComponent } from './queue-logo.component';

describe('QueueLogoComponent', () => {
  let component: QueueLogoComponent;
  let fixture: ComponentFixture<QueueLogoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QueueLogoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QueueLogoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
