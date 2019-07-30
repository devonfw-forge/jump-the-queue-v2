import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { LoginService } from '../login-page/services/login.service';
import { AccessCode, Queue } from './../shared/backendModels/interfaces';
import { AccessCodeService } from './../shared/services/access-code.service';
import { QueueService } from './../shared/services/queue.service';

@Component({
  selector: 'app-owner-overview-page',
  templateUrl: './owner-overview-page.component.html',
  styleUrls: ['./owner-overview-page.component.scss']
})
export class OwnerOverviewPageComponent implements OnInit, OnDestroy {
  private queue: Queue;
  private queueSub: Subscription;
  private currentCode: AccessCode;
  private codeSub: Subscription;

  constructor(
    private queueService: QueueService,
    private accessCodeService: AccessCodeService,
    private loginService: LoginService
  ) { }

  ngOnInit() {
    this.queueSub = this.queueService.getTodaysQueue().subscribe(queue => {
      this.queue = queue;
      if (this.queue.started) {
        this.codeSub = this.accessCodeService.getCurrentCode().subscribe(code => {
          this.currentCode = code;
        });
      }
    });
  }

  getStartedQueue(event: Queue) {
    this.queue = event;
  }

  refreshCurrentCode(event: AccessCode) {
    this.currentCode = event;
  }

  onLogout() {
    this.loginService.logout();
  }

  ngOnDestroy() {
    this.queueSub.unsubscribe();
    this.codeSub.unsubscribe();
  }

}
