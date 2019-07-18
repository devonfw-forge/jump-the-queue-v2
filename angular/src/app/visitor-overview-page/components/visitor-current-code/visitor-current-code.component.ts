import { Component, OnInit, OnDestroy } from '@angular/core';
import { AccessCodeService } from 'src/app/shared/services/access-code.service';
import { QueueService } from 'src/app/shared/services/queue.service';
import { AccessCode, Queue } from 'src/app/shared/backendModels/interfaces';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-visitor-current-code',
  templateUrl: './visitor-current-code.component.html',
  styleUrls: ['./visitor-current-code.component.scss']
})
export class VisitorCurrentCodeComponent implements OnInit, OnDestroy {
  private currentCode: AccessCode;
  private currentCodeSub: Subscription;
  private queue: Queue;
  private queueSub: Subscription;

  constructor(
    private accessCodeService: AccessCodeService,
    private queueService: QueueService
    ) { }

  ngOnInit() {
    //TODO: change backend so currentCore endpoint does not require todays queue
    this.queueSub = this.queueService.getTodaysQueue().subscribe(queue => {
      this.queue = queue;
      if (this.queue.started) {
        this.currentCodeSub = this.accessCodeService.getCurrentCode(this.queue).subscribe(
          currentCode => this.currentCode = currentCode
        );
      }
    });
  }

  ngOnDestroy() {
    this.currentCodeSub.unsubscribe();
    this.queueSub.unsubscribe();
  }
}
