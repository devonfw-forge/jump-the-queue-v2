import { Injectable } from '@angular/core';
import { AuthService } from '../../core/auth.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private authService: AuthService, private router: Router) { }

  login(username: string, password: string) {
    if (username === 'adcenter' && password === 'adcenter') {
      this.authService.setLogged(true);
      this.router.navigate(['owner']);
    }
  }

  logout(): void {
    this.authService.setLogged(false);
    this.router.navigate(['login']);
  }
}
