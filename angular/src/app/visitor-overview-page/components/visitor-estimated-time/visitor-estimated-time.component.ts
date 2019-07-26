import { Component, Input, OnInit, EventEmitter, Output, OnDestroy, OnChanges, SimpleChanges, SimpleChange } from '@angular/core';
import { AccessCode, EstimatedTime } from 'src/app/shared/backendModels/interfaces';
import { AccessCodeService } from 'src/app/shared/services/access-code.service';
import { Subscription } from 'rxjs';
import { LocalStorageService } from '../../services/local-storage.service';
import { Status } from 'src/app/shared/backendModels/enums';

@Component({
  selector: 'app-visitor-estimated-time',
  templateUrl: './visitor-estimated-time.component.html',
  styleUrls: ['./visitor-estimated-time.component.scss']
})
export class VisitorEstimatedTimeComponent implements OnInit, OnDestroy, OnChanges {
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
  }

  ngOnChanges(changes: SimpleChanges) {
    if ((changes.visitorCode && changes.visitorCode.currentValue) || (changes.currentCode && !changes.currentCode.firstChange)) {
      this.estimatedTimeSub = this.accessCodeService.getEstimatedTimeByCode(this.visitorCode).subscribe(estimated => {
        this.estimated.defaultTimeByUserInMs = estimated.defaultTimeByUserInMs;
        this.estimated.miliseconds = estimated.miliseconds;
      });
    }
    if (this.visitorCode && changes.currentCode && changes.currentCode.previousValue &&
       this.visitorCode.code === changes.currentCode.previousValue.code &&
       this.visitorCode.code !== changes.currentCode.currentValue.code) {
          // TODO: refactor this
          this.visitorCode.status = Status.Attended;
       }
  }

  getNewCode() {
    const uuid = { uuid: '' };
    uuid.uuid = this.localStorageService.renewUuid();
    this.visitorCodeSub = this.accessCodeService.getCodeByUuid(uuid).subscribe(content =>
      this.updateVisitorCode.emit(content['accessCode'])
    );
  }

  ngOnDestroy() {
    this.estimatedTimeSub.unsubscribe();
    this.visitorCodeSub.unsubscribe();
  }

}
