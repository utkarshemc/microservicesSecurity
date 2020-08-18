import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { OktaAuthService } from '@okta/okta-angular';
//import { HomeComponent } from '../home/home.component'; 

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit {
  isAuthenticated: boolean;
  userId: string;
  constructor(public oktaAuth: OktaAuthService,
    private authService: AuthenticationService,
    //private homeService: HomeComponent
    ) {
      this.oktaAuth.$authenticationState.subscribe(
        (isAuthenticated: boolean)  => this.isAuthenticated = isAuthenticated
      );
  }

  async ngOnInit() {
    const accessToken = await this.oktaAuth.getAccessToken();
    const userClaims = await this.oktaAuth.getUser();
     this.userId =  userClaims.sub;
     const emailId = userClaims.email;
  //  this.isAuthenticated = await this.oktaAuth.isAuthenticated();
  
    // this.isAdmin = admin1;
    // Subscribe to authentication state changes
    // this.oktaAuth.$authenticationState.subscribe(
    //  (isAuthenticated: boolean)  => this.isAuthenticated = isAuthenticated
    // );

    
    //this.authService.setAdmin(accessToken);
    this.authService.setCurrentUserId(this.userId,emailId);
  }

  getCurrentUserId(){
   return this.userId;
  }


   async logout() {
    const issuer = 'https://dev-512109.okta.com/oauth2/default';
    const redirectUri = `http://localhost:4200`;
      // Read idToken before local session is cleared
      const idToken = await this.oktaAuth.getIdToken();
  
      // Clear local session
      await this.oktaAuth.logout('/');
  
      // Clear remote session
      window.location.href = `${issuer}/v1/logout?id_token_hint=${idToken}&post_logout_redirect_uri=${redirectUri}`;
    }
  

}
