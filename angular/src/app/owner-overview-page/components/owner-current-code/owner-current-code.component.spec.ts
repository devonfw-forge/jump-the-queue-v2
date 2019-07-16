import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerCurrentCodeComponent } from './owner-current-code.component';

describe('OwnerCurrentCodeComponent', () => {
  let component: OwnerCurrentCodeComponent;
  let fixture: ComponentFixture<OwnerCurrentCodeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OwnerCurrentCodeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OwnerCurrentCodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
