import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { AccessCode } from './../backendModels/interfaces';
import { Queue } from '../backendModels/interfaces';

@Injectable({
  providedIn: 'root'
})
export class AccessCodeService {

  private baseUrl = environment.baseUrlRestServices;

  constructor(private http: HttpClient) { }

  getCurrentCode(queue: Queue): Observable<AccessCode> {
    return this.http.post<AccessCode>(this.baseUrl + 'accesscodemanagement/v1/accesscode/current', queue);
  }

  callNextCode(): Observable<AccessCode> {
    return this.http.post<AccessCode>(this.baseUrl + 'accesscodemanagement/v1/accesscode/next', {});
  }
}
