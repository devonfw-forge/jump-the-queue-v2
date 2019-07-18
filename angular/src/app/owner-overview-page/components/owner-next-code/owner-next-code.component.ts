import { Component, OnInit, Output, EventEmitter, OnDestroy } from '@angular/core';
import { AccessCodeService } from 'src/app/shared/services/access-code.service';
import { Subscription } from 'rxjs';
import { AccessCode } from 'src/app/shared/backendModels/interfaces';

@Component({
  selector: 'app-owner-next-code',
  templateUrl: './owner-next-code.component.html',
  styleUrls: ['./owner-next-code.component.scss']
})
export class OwnerNextCodeComponent implements OnInit, OnDestroy {
@Output() updateCurrentCode = new EventEmitter();
private nextCodeSub: Subscription;

  constructor(private accessCodeService: AccessCodeService) { }

  ngOnInit() {
  }

  callNext() {
    this.nextCodeSub = this.accessCodeService.callNextCode().subscribe(
      currentCode => this.updateCurrentCode.emit(currentCode)
    );
  }

  ngOnDestroy() {
    this.nextCodeSub.unsubscribe();
  }
}

//TODO: disable button when there are no more codes in queue