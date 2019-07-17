import { Component, OnInit, OnDestroy } from '@angular/core';
import { QueueService } from './../shared/services/queue.service';
import { Queue } from './../shared/backendModels/interfaces';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-owner-overview-page',
  templateUrl: './owner-overview-page.component.html',
  styleUrls: ['./owner-overview-page.component.scss']
})
export class OwnerOverviewPageComponent implements OnInit, OnDestroy {
  private queue: Queue;
  private queueSub: Subscription;

  constructor(private queueService: QueueService) { }

  ngOnInit() {
    this.queueSub = this.queueService.getTodaysQueue().subscribe(queue => {
      this.queue = queue;
    });
  }

  getStartedQueue(event: Queue) {
    this.queue = event;
  }

  ngOnDestroy() {
    // TODO: Check this cause is not firing on destroy
    this.queueSub.unsubscribe();
  }

}
