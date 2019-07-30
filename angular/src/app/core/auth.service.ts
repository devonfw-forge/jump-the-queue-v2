import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private username: string;
  private logged = false;

  constructor() { }

  public isLogged(): boolean {
    return this.logged;
  }

  public setLogged(login: boolean): void {
    this.logged = login;
  }

  public getUsername(): string {
    return this.username;
  }

  public setUsername(username: string) {
    this.username = username;
  }

}
