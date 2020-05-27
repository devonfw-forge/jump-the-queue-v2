import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material';
import { map } from 'rxjs/operators';
import { AuthService } from '../../core/auth.service';
import { environment } from 'src/environments/environment';
import { Owner } from 'src/app/shared/backendModels/interfaces';



@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl = environment.baseUrlRestServices;
  constructor(
    private authService: AuthService,
    private router: Router,
    private http: HttpClient,
    public snackBar: MatSnackBar
  ) { }

  login(username: string, password: string) {
    const payload = {
      username,
      password,
      /*
      pageable: {
        pageNumber: 0,
        pageSize: 1
      }
      */
    };

    this.http.post<Owner>(this.baseUrl + 'ownermanagement/v1/owner/search', payload).pipe(
      map(res => res['content'][0])
    ).subscribe(
      (owner: Owner) => {
        if (owner && owner.username === username && owner.password === password && owner.userType) {
          this.authService.setLogged(true);
          this.router.navigate(['owner']);
        } else {
          this.authService.setLogged(false);
          this.snackBar.open('Incorrect credentials', 'OK', {
            duration: 2000,
          });
        }
      },
      err => {
        this.snackBar.open('Server error', 'OK', {
          duration: 2000,
        });
      }
    );
  }

  logout(): void {
    this.authService.setLogged(false);
    this.router.navigate(['login']);
  }
}
