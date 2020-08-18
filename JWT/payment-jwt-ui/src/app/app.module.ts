import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AddUserComponent } from './add-user/add-user.component';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { BasicAuthHttpInterceptorService } from './services/basic-auth-http-interceptor.service';
import {MatRadioModule} from '@angular/material/radio';
import { AddPaymentComponent } from './add-payment/add-payment.component';
import { PaymentComponent } from './payment/payment.component'
import { AlertModule } from './_alert';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    AddUserComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    LogoutComponent,
    AddPaymentComponent,
    PaymentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatRadioModule,
    AlertModule,
  ],
  providers: [
    {
      provide:HTTP_INTERCEPTORS, useClass:BasicAuthHttpInterceptorService, multi:true 
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
