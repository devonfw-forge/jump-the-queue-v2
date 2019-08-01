import { Component, OnInit, OnDestroy } from '@angular/core';
import { AccessCodeService } from './../shared/services/access-code.service';
import { LocalStorageService } from './services/local-storage.service';
import { AccessCode, Queue, CodeUuid } from '../shared/backendModels/interfaces';
import { Subscription } from 'rxjs';
import { ServerSideEventsService } from '../shared/services/server-side-events.service';
import { QueueService } from '../shared/services/queue.service';
import { SseTopic, Status } from '../shared/backendModels/enums';

@Component({
  selector: 'app-visitor-overview-page',
  templateUrl: './visitor-overview-page.component.html',
  styleUrls: ['./visitor-overview-page.component.scss']
})
export class VisitorOverviewPageComponent implements OnInit, OnDestroy {
  private queue: Queue;
  private currentCode: AccessCode;
  private visitorCode: AccessCode;
  private visitorCodeSub: Subscription;
  private currentCodeSub: Subscription;
  private queueSub: Subscription;
  private sseStream: EventSource;

  constructor(
    private queueService: QueueService,
    private accessCodeService: AccessCodeService,
    private localStorageService: LocalStorageService,
    private serverSideEventsService: ServerSideEventsService
  ) {
    this.sseStream = this.serverSideEventsService.getStream();
  }

  ngOnInit() {
    this.queueSub = this.queueService.getTodaysQueue().subscribe(queue => {
      if (queue.started) {
        this.queue = queue;
        this.currentCodeSub = this.accessCodeService.getCurrentCode().subscribe(code => this.currentCode = code);
        const codeUuid = new CodeUuid();
        codeUuid.uuid = this.localStorageService.getUuid();
        this.visitorCodeSub = this.accessCodeService.getCodeByUuid(codeUuid).subscribe(content => this.visitorCode = content['accessCode']);
        this.subscribeAccessCodeSseTopics(this.sseStream);
      } else {
        const codeUuid = new CodeUuid();
        codeUuid.uuid = this.localStorageService.getUuid();
        this.visitorCodeSub = this.accessCodeService.getCodeByUuid(codeUuid).subscribe(
          content => this.visitorCode = content['accessCode']
        );
        // queue is not started go SSE
        this.sseStream.addEventListener(SseTopic.QUEUE_STARTED, (data: any) => {
          let parsedQueue = new Queue();
          parsedQueue = JSON.parse(data.data);
          this.queue = parsedQueue;
          this.currentCodeSub = this.accessCodeService.getCurrentCode().subscribe(code => this.currentCode = code);
          this.subscribeAccessCodeSseTopics(this.sseStream);
        });
      }
    });
  }

  subscribeAccessCodeSseTopics(source: EventSource) {
    source.addEventListener(SseTopic.CURRENT_CODE_CHANGED, (data: any) => {
      let parsedCode = new AccessCode();
      parsedCode = JSON.parse(data.data);
      this.currentCode = parsedCode;
      if (this.currentCode.uuid === this.visitorCode.uuid) {
        this.visitorCode = this.currentCode;
      }
      if (this.visitorCode.status === Status.Attending) {
        const codeUuid = new CodeUuid();
        codeUuid.uuid = this.localStorageService.getUuid();
        this.visitorCodeSub = this.accessCodeService.getCodeByUuid(codeUuid).subscribe(
          content => this.visitorCode = content['accessCode']
        );
      }
    });
    source.addEventListener(SseTopic.CURRENT_CODE_CHANGED_NULL, (data: any) => {
      let parsedCode = new AccessCode();
      parsedCode = JSON.parse(data.data);
      this.currentCode = parsedCode;
      const codeUuid = new CodeUuid();
      codeUuid.uuid = this.localStorageService.getUuid();
      this.visitorCodeSub = this.accessCodeService.getCodeByUuid(codeUuid).subscribe(content => this.visitorCode = content['accessCode']);
    });
  }

  refreshVisitorCode(event) {
    this.visitorCode = event;
  }

  ngOnDestroy() {
    this.sseStream.close();
    this.queueSub.unsubscribe();
    this.currentCodeSub.unsubscribe();
    this.visitorCodeSub.unsubscribe();
  }
}
