import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ServerSideEventsService {

  private currentCodeStreamUrl = 'http://localhost:8081/stream/subscribe';
  constructor() { }

  getStream(): EventSource {
    return new EventSource(this.currentCodeStreamUrl);
  }
}
