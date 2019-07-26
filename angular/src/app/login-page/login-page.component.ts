import { Component, OnInit } from '@angular/core';
import { LoginService } from './services/login.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  public username: string;
  public password: string;
  constructor(private loginService: LoginService) { }

  ngOnInit() {
    this.username = 'adcenter';
    this.password = 'adcenter';
  }

  onLogin() {
    this.loginService.login(this.username, this.password);
  }

  onLogout() {
    this.onLogout();
  }
}
