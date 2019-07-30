import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { Subscription } from 'rxjs';
import { AccessCode } from 'src/app/shared/backendModels/interfaces';
import { AccessCodeService } from 'src/app/shared/services/access-code.service';
import { ServerSideEventsService } from 'src/app/shared/services/server-side-events.service';
import { Status } from 'src/app/shared/backendModels/enums';

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

  constructor(
    private accessCodeService: AccessCodeService,
    private serverSideEventsService: ServerSideEventsService
    ) { }

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
    const source = this.serverSideEventsService.getStream();
    source.addEventListener('NEW_CODE_ADDED', (data: any) => {
      let parsedCode = new AccessCode();
      parsedCode = JSON.parse(data.data);
      if (parsedCode.status === Status.Waiting) {
        this.remainingCodesCount++;
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
