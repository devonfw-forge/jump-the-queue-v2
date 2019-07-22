import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AccessCode, EstimatedTime, NextCodeCto, RemainingCodes } from './../backendModels/interfaces';
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

  callNextCode(): Observable<NextCodeCto> {
    return this.http.post<NextCodeCto>(this.baseUrl + 'accesscodemanagement/v1/accesscode/next', {});
  }

  getCodeByUuid(uuid: {'uuid': string}): Observable<AccessCode> {
    return this.http.post<AccessCode>(this.baseUrl + 'accesscodemanagement/v1/accesscode/uuid', uuid);
  }

  getEstimatedTimeByCode(code: AccessCode): Observable<EstimatedTime> {
    return this.http.post<EstimatedTime>(this.baseUrl + 'accesscodemanagement/v1/accesscode/estimated', code);
  }

  getRemainingCodesCount(): Observable<RemainingCodes> {
    return this.http.post<RemainingCodes>(this.baseUrl + 'accesscodemanagement/v1/accesscode/remaining', {});
  }
}
