import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserComponent } from './user/user.component';
import { AddUserComponent } from './add-user/add-user.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { AuthGaurdService } from './services/auth-guard.service';
import { AddPaymentComponent } from './add-payment/add-payment.component';
import { PaymentComponent } from './payment/payment.component';
import { OktaCallbackComponent } from '@okta/okta-angular';
import { OktaAuthGuard } from '@okta/okta-angular';
import { AdminGuard } from './admin.guard';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';

export function onAuthRequired({ oktaAuth, router }) {
  router.navigate(['/login']);
}

const routes: Routes = [
  { path:'users', component: UserComponent, canActivate:[OktaAuthGuard]},
  { path:'adduser', component: AddUserComponent},
  { path: 'login', component: LoginComponent},
  { path: 'logout', component: LogoutComponent, canActivate:[AuthGaurdService]},
  { path:'addpayment', component: AddPaymentComponent, canActivate:[OktaAuthGuard]},
  { path:'payments', component: PaymentComponent, canActivate:[OktaAuthGuard]},
  {path: 'implicit/callback', component: OktaCallbackComponent},
  {path: 'header', component: HeaderComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  {
       path: 'home',
      component: HomeComponent
  } ,
   
     {
       path: 'callback',
       component: OktaCallbackComponent
    }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


