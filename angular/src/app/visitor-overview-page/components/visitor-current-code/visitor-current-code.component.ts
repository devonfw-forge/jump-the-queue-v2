import { Component, OnInit, OnDestroy, Output, EventEmitter } from '@angular/core';
import { AccessCodeService } from 'src/app/shared/services/access-code.service';
import { QueueService } from 'src/app/shared/services/queue.service';
import { AccessCode, Queue } from 'src/app/shared/backendModels/interfaces';
import { Subscription } from 'rxjs';
import { ServerSideEventsService } from 'src/app/shared/services/server-side-events.service';
import { SseTopic } from 'src/app/shared/backendModels/enums';

@Component({
  selector: 'app-visitor-current-code',
  templateUrl: './visitor-current-code.component.html',
  styleUrls: ['./visitor-current-code.component.scss']
})
export class VisitorCurrentCodeComponent implements OnInit, OnDestroy {
  @Output() updateCurrentCode = new EventEmitter();
  private currentCode: AccessCode;
  private currentCodeSub: Subscription;
  private queue: Queue;
  private queueSub: Subscription;

  constructor(
    private accessCodeService: AccessCodeService,
    private queueService: QueueService,
    private serverSideEventsService: ServerSideEventsService
    ) { }

  ngOnInit() {
    this.queueSub = this.queueService.getTodaysQueue().subscribe(queue => {
      const source = this.serverSideEventsService.getStream();
      this.queue = queue;
      if (this.queue.started) {
        this.currentCodeSub = this.accessCodeService.getCurrentCode().subscribe(code => {
          this.currentCode = code;
          this.subscribeAccessCodeSseTopics(source);
        });
      } else {
        source.addEventListener(SseTopic.QUEUE_STARTED, (data: any) => {
          let parsedQueue = new Queue();
          parsedQueue = JSON.parse(data.data);
          this.queue = parsedQueue;
        });
        this.currentCodeSub = this.accessCodeService.getCurrentCode().subscribe(code => {
          this.currentCode = code;
          this.subscribeAccessCodeSseTopics(source);
        });
      }
    });
  }

  subscribeAccessCodeSseTopics(source: EventSource) {
    source.addEventListener(SseTopic.CURRENT_CODE_CHANGED, (data: any) => {
      let parsedCode = new AccessCode();
      parsedCode = JSON.parse(data.data);
      this.updateCurrentCode.emit(parsedCode);
      this.currentCode = parsedCode;
    });
    source.addEventListener(SseTopic.CURRENT_CODE_CHANGED_NULL, (data: any) => {
      let parsedCode = new AccessCode();
      parsedCode = JSON.parse(data.data);
      this.updateCurrentCode.emit(parsedCode);
      this.currentCode = parsedCode;
    });
  }

  ngOnDestroy() {
    this.currentCodeSub.unsubscribe();
    this.queueSub.unsubscribe();
  }
}
