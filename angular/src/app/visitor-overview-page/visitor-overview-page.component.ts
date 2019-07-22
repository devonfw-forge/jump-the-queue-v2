import { Component, OnInit, OnDestroy } from '@angular/core';
import { QueueService } from './../shared/services/queue.service';
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
  private visitorCode: AccessCode;
  private visitorCodeSub: Subscription;

  constructor(
    private queueService: QueueService,
    private accessCodeService: AccessCodeService,
    private localStorageService: LocalStorageService
  ) {}

  ngOnInit() {
    // Get uuid
    const uuid = {uuid: ''};
    uuid.uuid = this.localStorageService.getUuid();
    // Get code
    //TODO: change backend to return just eto and not cto
    this.visitorCodeSub = this.accessCodeService.getCodeByUuid(uuid).subscribe(content => this.visitorCode = content['accessCode']);
  }

  refreshVisitorCode(event) {
    this.visitorCode = event;
  }

  ngOnDestroy() {
    this.visitorCodeSub.unsubscribe();
  }
}
