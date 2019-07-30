import { Component, Input, OnInit, EventEmitter, Output, OnDestroy } from '@angular/core';
import { AccessCode, EstimatedTime, CodeUuid } from 'src/app/shared/backendModels/interfaces';
import { AccessCodeService } from 'src/app/shared/services/access-code.service';
import { Subscription } from 'rxjs';
import { LocalStorageService } from '../../services/local-storage.service';
import { Status } from 'src/app/shared/backendModels/enums';

@Component({
  selector: 'app-visitor-estimated-time',
  templateUrl: './visitor-estimated-time.component.html',
  styleUrls: ['./visitor-estimated-time.component.scss']
})
export class VisitorEstimatedTimeComponent implements OnInit, OnDestroy {
  @Output() updateVisitorCode = new EventEmitter();
  @Input() visitorCode: AccessCode;
  @Input() currentCode: AccessCode;
  private estimatedTimeSub: Subscription;
  private estimated: EstimatedTime = new EstimatedTime();
  private visitorCodeSub: Subscription;

  constructor(
    private accessCodeService: AccessCodeService,
    private localStorageService: LocalStorageService
  ) {}

  ngOnInit() {
    if (this.visitorCode && this.visitorCode.status === Status.Waiting) {
      this.estimatedTimeSub = this.accessCodeService.getEstimatedTimeByCode(this.visitorCode).subscribe(estimatedTimeResponse => {
        this.estimated = estimatedTimeResponse;
      });
    }
  }

  getNewCode() {
    const codeUuid = new CodeUuid();
    codeUuid.uuid = this.localStorageService.renewUuid();
    this.visitorCodeSub = this.accessCodeService.getCodeByUuid(codeUuid).subscribe(content =>
      this.updateVisitorCode.emit(content['accessCode'])
    );
  }

  ngOnDestroy() {
    this.estimatedTimeSub.unsubscribe();
    this.visitorCodeSub.unsubscribe();
  }

}
