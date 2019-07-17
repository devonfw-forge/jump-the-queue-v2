import { Component, OnInit, Input } from '@angular/core';
import { AccessCode } from 'src/app/shared/backendModels/interfaces';

@Component({
  selector: 'app-owner-current-code',
  templateUrl: './owner-current-code.component.html',
  styleUrls: ['./owner-current-code.component.scss']
})
export class OwnerCurrentCodeComponent implements OnInit {
  @Input() currentCode: AccessCode;

  constructor() { }

  ngOnInit() {
  }

}
