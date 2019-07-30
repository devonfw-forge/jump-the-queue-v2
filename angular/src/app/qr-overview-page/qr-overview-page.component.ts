import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-qr-overview-page',
  templateUrl: './qr-overview-page.component.html',
  styleUrls: ['./qr-overview-page.component.scss']
})
export class QrOverviewPageComponent implements OnInit {
  public myAngularxQrCode: string;
  constructor() {
    this.myAngularxQrCode = environment.qrUrl;
  }

  ngOnInit() {
  }

}
