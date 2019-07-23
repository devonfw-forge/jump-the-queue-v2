import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { VisitorOverviewPageComponent } from './visitor-overview-page/visitor-overview-page.component';
import { LoginPageComponent } from '../app/login-page/login-page.component';
import { OwnerOverviewPageComponent } from '../app/owner-overview-page/owner-overview-page.component';

import { AuthGuardService } from './core/auth-guard.service';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginPageComponent },
  { path: 'my-code', component: VisitorOverviewPageComponent },
  { path: 'owner', component: OwnerOverviewPageComponent, canActivate: [AuthGuardService] },
  { path: '**', component: LoginPageComponent }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
