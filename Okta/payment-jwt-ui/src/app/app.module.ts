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
import { OktaAuthModule } from '@okta/okta-angular';
//import { AuthRoutingModule } from './auth-routing.module';
import { OKTA_CONFIG} from '@okta/okta-angular';
import { HomeComponent } from './home/home.component';

const config = {
  issuer: 'https://dev-512109.okta.com/oauth2/default',
  redirectUri: 'http://localhost:4200/implicit/callback',
  clientId: '0oakjotfruiFbl2er4x6',
  pkce: true
};


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
    OktaAuthModule.initAuth(config),
    //AuthRoutingModule
  ],
  providers: [
    {
       provide:HTTP_INTERCEPTORS, useClass:BasicAuthHttpInterceptorService, multi:true
    },
     { provide: OKTA_CONFIG, useValue: config }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
