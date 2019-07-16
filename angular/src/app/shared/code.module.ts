import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatInputModule, MatButtonModule } from '@angular/material';
import { FormsModule } from '@angular/forms';
import { AuthGuardService } from '../core/auth-guard.service';
import { AuthService } from '../core/auth.service';
import { HttpClientModule } from '@angular/common/http';
import { QueueLogoComponent } from './components/queue-logo/queue-logo.component';

@NgModule({
    imports: [
        MatInputModule,
        MatButtonModule,
        FormsModule,
        HttpClientModule
    ],
    exports: [
        CommonModule,
        MatInputModule,
        MatButtonModule,
        FormsModule
    ],
    providers: [
        HttpClientModule,
        AuthGuardService,
        AuthService
    ],
    declarations: [QueueLogoComponent]
})
export class CoreModule { }
