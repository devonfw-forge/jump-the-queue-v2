import { Component, OnInit, Input } from '@angular/core';
import { AccessCode } from 'src/app/shared/backendModels/interfaces';
@Component({
  selector: 'app-visitor-code',
  templateUrl: './visitor-code.component.html',
  styleUrls: ['./visitor-code.component.scss']
})
export class VisitorCodeComponent implements OnInit {
  @Input() visitorCode: AccessCode;

  constructor() { }

  ngOnInit() { }

}
