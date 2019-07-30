import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ServerSideEventsService {

  private streamUrl = environment.streamUrl;
  constructor() { }

  getStream(): EventSource {
    return new EventSource(this.streamUrl);
  }
}
