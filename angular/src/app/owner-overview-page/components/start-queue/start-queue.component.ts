import { Component, OnInit, OnDestroy, Input, Output, EventEmitter } from '@angular/core';
import { Queue } from 'src/app/shared/backendModels/interfaces';
import { QueueService } from 'src/app/shared/services/queue.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-start-queue',
  templateUrl: './start-queue.component.html',
  styleUrls: ['./start-queue.component.scss']
})
export class StartQueueComponent implements OnInit, OnDestroy {
  @Input() queue: Queue;
  @Output() passStartedQueue = new EventEmitter();
  private queueSub: Subscription;

  constructor(private queueService: QueueService) { }

  ngOnInit() {
  }

  onStartQueue(event) {
    this.queueSub = this.queueService.startQueue(this.queue).subscribe(startedQueue => {
      this.passStartedQueue.emit(startedQueue);
    });
  }

  ngOnDestroy() {
    this.queueSub.unsubscribe();
  }
}
