import { Component, OnInit, Input } from '@angular/core';
import { AccessCode, Queue } from 'src/app/shared/backendModels/interfaces';

@Component({
  selector: 'app-visitor-current-code',
  templateUrl: './visitor-current-code.component.html',
  styleUrls: ['./visitor-current-code.component.scss']
})
export class VisitorCurrentCodeComponent implements OnInit {
  @Input() currentCode: AccessCode;
  @Input() queue: Queue;

  constructor() { }

  ngOnInit() {}
}
