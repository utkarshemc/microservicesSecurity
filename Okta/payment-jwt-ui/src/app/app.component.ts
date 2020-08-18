import { Component, OnInit } from '@angular/core';
import { OktaAuthService } from '@okta/okta-angular';
import { AuthenticationService } from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'payment-gateway';
  isAuthenticated: boolean;

  constructor(public oktaAuth: OktaAuthService, private authService: AuthenticationService,) {
    // Subscribe to authentication state changes
    // this.oktaAuth.$authenticationState.subscribe(
    //   (isAuthenticated: boolean)  => this.isAuthenticated = isAuthenticated
    // );
  }

  async ngOnInit() {
    //this.isAuthenticated = await this.oktaAuth.isAuthenticated();
  }

  login() {
    this.oktaAuth.loginRedirect('/payments');
  }

  
}
