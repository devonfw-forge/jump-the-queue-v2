import { Component, Input, OnInit, OnDestroy, OnChanges, SimpleChanges, SimpleChange } from '@angular/core';
import { AccessCode, EstimatedTime } from 'src/app/shared/backendModels/interfaces';
import { AccessCodeService } from 'src/app/shared/services/access-code.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-visitor-estimated-time',
  templateUrl: './visitor-estimated-time.component.html',
  styleUrls: ['./visitor-estimated-time.component.scss']
})
export class VisitorEstimatedTimeComponent implements OnInit, OnDestroy, OnChanges {
  @Input() visitorCode: AccessCode;
  private estimatedTimeSub: Subscription;
  private estimated: EstimatedTime = new EstimatedTime();

  constructor(private accessCodeService: AccessCodeService) { }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes.visitorCode.currentValue) {
      this.estimatedTimeSub = this.accessCodeService.getEstimatedTimeByCode(this.visitorCode).subscribe(estimated => {
        this.estimated.defaultTimeByUserInMs = estimated.defaultTimeByUserInMs;
        this.estimated.miliseconds = estimated.miliseconds;
      });
    }
  }

  ngOnDestroy() {
    this.estimatedTimeSub.unsubscribe();
  }

}
