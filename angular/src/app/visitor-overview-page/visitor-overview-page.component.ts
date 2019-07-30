import { Component, OnInit, OnDestroy } from '@angular/core';
import { AccessCodeService } from './../shared/services/access-code.service';
import { LocalStorageService } from './services/local-storage.service';
import { AccessCode } from '../shared/backendModels/interfaces';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-visitor-overview-page',
  templateUrl: './visitor-overview-page.component.html',
  styleUrls: ['./visitor-overview-page.component.scss']
})
export class VisitorOverviewPageComponent implements OnInit, OnDestroy {
  private currentCode: AccessCode;
  private visitorCode: AccessCode;
  private visitorCodeSub: Subscription;

  constructor(
    private accessCodeService: AccessCodeService,
    private localStorageService: LocalStorageService
  ) {}

  ngOnInit() {
    const uuid = {uuid: ''};
    uuid.uuid = this.localStorageService.getUuid();
    this.visitorCodeSub = this.accessCodeService.getCodeByUuid(uuid).subscribe(content => {
      this.visitorCode = content['accessCode'];
    });
  }

  refreshVisitorCode(event) {
    this.visitorCode = event;
  }

  refreshCurrentCode(event) {
    this.currentCode = event;
  }

  ngOnDestroy() {
    this.visitorCodeSub.unsubscribe();
  }
}
