import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Direction, Status} from './../backendModels/enums';
import { AccessCode, FilterAccessCode, Sort, Pageable } from './../backendModels/interfaces';

@Injectable({
  providedIn: 'root'
})
export class AccessCodeService {

  private baseUrl = environment.baseUrlRestServices;

  constructor(private http: HttpClient) { }

  getCurrentCode(queueId: number): Observable<AccessCode> {

    // MOdifica en java para no hacer esto anda... y que devuelva el objeto tal cual o un objeto con todo a null si no esta..
    const criteria: FilterAccessCode = new FilterAccessCode();
    criteria.queueId = queueId;
    criteria.status = Status.Attending;
    const page = new Pageable();
    page.pageNumber = 0;
    page.pageSize = 1;
    const sort: Sort = new Sort();
    sort.direction = Direction.ASC;
    sort.property = 'createdDate';

    page.sort = [];
    page.sort.push(sort);
    criteria.pageable = page;
    return this.http.post<AccessCode>(this.baseUrl + 'accesscodemanagement/v1/accesscode/search', criteria)
    .pipe(
      map((response) => {
        debugger;
      if (response['content'].length === 1) {
        return response['content'][0];
      } else {
        return new AccessCode();
      }
      })
    );
  }

  callNextCode(): Observable<AccessCode> {
    return this.http.post<AccessCode>(this.baseUrl + 'accesscodemanagement/v1/accesscode/next', {});
  }
}
