import { Component, OnInit } from '@angular/core';
import { OktaAuthService } from '@okta/okta-angular';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
  isAuthenticated: boolean;
  isAdmin: boolean;

  constructor(public oktaAuth: OktaAuthService,private router: Router, private authService: AuthenticationService,) {
  }

  async ngOnInit() {
    //this.isAdmin = await this.getAdminDetails();
    //this.isAuthenticated = await this.oktaAuth.isAuthenticated();
    const userClaims = await this.oktaAuth.getUser();
    const userId =  userClaims.sub;
    const emailId = userClaims.email;
    const accessToken = await this.oktaAuth.getAccessToken();
    this.authService.setCurrentUserId(userId, emailId);
    //this.authService.setAdmin(accessToken);
    //this.isAuthenticated = await this.oktaAuth.isAuthenticated();
    // Subscribe to authentication state changes
    //this.oktaAuth.$authenticationState.subscribe(
    //  (isAuthenticated: boolean)  => this.isAuthenticated = isAuthenticated
    //);
    //this.router.navigate(['payments'])
  }

   

   
}
