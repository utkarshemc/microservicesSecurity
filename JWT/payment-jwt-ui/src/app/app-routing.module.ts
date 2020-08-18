import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserComponent } from './user/user.component';
import { AddUserComponent } from './add-user/add-user.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { AuthGaurdService } from './services/auth-guard.service';
import { AddPaymentComponent } from './add-payment/add-payment.component';
import { PaymentComponent } from './payment/payment.component';


const routes: Routes = [
  { path:'users', component: UserComponent, canActivate:[AuthGaurdService]},
  { path:'adduser', component: AddUserComponent},
  { path: 'login', component: LoginComponent},
  { path: 'logout', component: LogoutComponent, canActivate:[AuthGaurdService]},
  { path:'addpayment', component: AddPaymentComponent, canActivate:[AuthGaurdService]},
  { path:'payments', component: PaymentComponent, canActivate:[AuthGaurdService]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
