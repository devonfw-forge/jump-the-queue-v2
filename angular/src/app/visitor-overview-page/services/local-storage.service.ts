import { Injectable } from '@angular/core';
import * as uuid from 'uuid';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {
  private storageKey = environment.localStorageUuidKey;

  constructor() { }

  setUuid() {
    localStorage.setItem(this.storageKey, uuid.v4());
  }

  getUuid(): string {
    let value = localStorage.getItem(this.storageKey);
    if (!value) {
      this.setUuid();
      value = localStorage.getItem(this.storageKey);
    }
    return value;
  }

  renewUuid() {
    localStorage.removeItem(this.storageKey);
    return this.getUuid();
  }
}
