import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-qr-overview-page',
  templateUrl: './qr-overview-page.component.html',
  styleUrls: ['./qr-overview-page.component.scss']
})
export class QrOverviewPageComponent implements OnInit {
  private myAngularxQrCode: string;
  private host: string;
  private hostname: string;
  private origin: string;
  constructor() {
    this.myAngularxQrCode = environment.qrUrl;
    this.host = window.location.host;
    this.hostname = window.location.hostname;
    this.origin = window.location.origin;
  }

  ngOnInit() {
  }

}
