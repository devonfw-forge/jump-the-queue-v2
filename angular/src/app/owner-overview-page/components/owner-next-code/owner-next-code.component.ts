import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { Subscription } from 'rxjs';
import { AccessCode } from 'src/app/shared/backendModels/interfaces';
import { AccessCodeService } from 'src/app/shared/services/access-code.service';

@Component({
  selector: 'app-owner-next-code',
  templateUrl: './owner-next-code.component.html',
  styleUrls: ['./owner-next-code.component.scss']
})
export class OwnerNextCodeComponent implements OnInit, OnDestroy {
@Output() updateCurrentCode = new EventEmitter();
@Input() currentCode: AccessCode;
private nextCodeSub: Subscription;
private remainingCodesCount: number;
private remainingCodesCountSub: Subscription;
private buttonStatus: string;

  constructor(private accessCodeService: AccessCodeService) { }

  ngOnInit() {
    this.remainingCodesCountSub = this.accessCodeService.getRemainingCodesCount().subscribe(v => {
      this.remainingCodesCount = v.remainingCodes;
      if (this.remainingCodesCount > 0) {
        this.buttonStatus = 'Call Next';
      } else if (this.remainingCodesCount === 0 && this.currentCode.code) {
        this.buttonStatus = 'Finish Current';
      } else {
        this.buttonStatus = 'Call Next';
      }
    });
  }

  callNext() {
    this.nextCodeSub = this.accessCodeService.callNextCode().subscribe(nextCodeCto => {
      this.remainingCodesCount = nextCodeCto.remainingCodes.remainingCodes;
      if (!nextCodeCto.accessCode) {
        this.buttonStatus = 'Call Next';
      } else if (nextCodeCto.remainingCodes.remainingCodes === 0) {
        this.buttonStatus = 'Finish Current';
      } else {
        this.buttonStatus = 'Call Next';
      }
      this.updateCurrentCode.emit(nextCodeCto.accessCode);
    });
  }

  ngOnDestroy() {
    this.remainingCodesCountSub.unsubscribe();
    if (this.nextCodeSub) this.nextCodeSub.unsubscribe();
  }
}
