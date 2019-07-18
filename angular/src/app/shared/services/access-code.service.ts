import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
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

  getCodeByUuid(uuid: {'uuid': string}): Observable<AccessCode> {
    return this.http.post<AccessCode>(this.baseUrl + 'accesscodemanagement/v1/accesscode/uuid', uuid);
  }
}
