import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Queue } from './../backendModels/interfaces';
import { Observable } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class QueueService {

  private baseUrl = environment.baseUrlRestServices;

  constructor(private http: HttpClient) {}

  getTodaysQueue(): Observable<Queue> {
    return this.http.get<Queue>(this.baseUrl + 'queuemanagement/v1/queue/daily/');
  }

  startQueue(queue: Queue): Observable<Queue> {
    return this.http.post<Queue>(this.baseUrl + 'queuemanagement/v1/queue/start', queue);
  }
}
