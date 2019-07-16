import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StartQueueComponent } from './start-queue.component';

describe('StartQueueComponent', () => {
  let component: StartQueueComponent;
  let fixture: ComponentFixture<StartQueueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StartQueueComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StartQueueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
