import { TestBed } from '@angular/core/testing';

import { ServerSideEventsService } from './server-side-events.service';

describe('ServerSideEventsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ServerSideEventsService = TestBed.get(ServerSideEventsService);
    expect(service).toBeTruthy();
  });
});
