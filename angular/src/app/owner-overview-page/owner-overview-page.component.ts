import { Component, OnInit, OnDestroy } from '@angular/core';
import { QueueService } from './../shared/services/queue.service';
import { AccessCodeService } from './../shared/services/access-code.service';
import { Queue, AccessCode } from './../shared/backendModels/interfaces';
import { Subscription } from 'rxjs';

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

  constructor(private queueService: QueueService, private accessCodeService: AccessCodeService) { }

  ngOnInit() {
    this.queueSub = this.queueService.getTodaysQueue().subscribe(queue => {
      this.queue = queue;
      if (this.queue.started) {
        this.codeSub = this.accessCodeService.getCurrentCode(this.queue).subscribe(code => {
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

  ngOnDestroy() {
    // TODO: Check this cause is not firing on destroy
    this.queueSub.unsubscribe();
    this.codeSub.unsubscribe();
  }

}
