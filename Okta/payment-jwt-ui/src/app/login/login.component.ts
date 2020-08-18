// import { Component, OnInit } from '@angular/core';
// import { Router } from '@angular/router';
// import { AuthenticationService } from '../services/authentication.service';
// import { AlertService } from '../_alert';

// @Component({
//   selector: 'app-login',
//   templateUrl: './login.component.html',
//   styleUrls: ['./login.component.css']
// })
// export class LoginComponent implements OnInit {

//   username = ''
//   password = ''
//   invalidLogin = false
//   public error: any;

//   constructor(private router: Router,
//     private loginservice: AuthenticationService,
//     protected alertService: AlertService) { }

//   ngOnInit() {
//   }

//   checkLogin() {
//     (this.loginservice.authenticate(this.username, this.password).subscribe(
//       data => {
//         this.router.navigate(['payments'])
//         this.invalidLogin = false
//         this.alertService.success(data);
//       },
//       error => {
//         this.invalidLogin = true
//         this.error = error.error; 
//         this.alertService.error(this.error);   }
//     )
//     );

//   }

// }
import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart} from '@angular/router';

import { OktaAuthService } from '@okta/okta-angular';
import * as OktaSignIn from '@okta/okta-signin-widget';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-login',
  template: `<div id="okta-signin-container"></div>`,
  styles: []
})
export class LoginComponent implements OnInit {
  widget = new OktaSignIn({
    baseUrl: 'https://dev-512109.okta.com'
  });

  constructor(private oktaAuth: OktaAuthService, router: Router,  private authService: AuthenticationService,) {
    // Show the widget when prompted, otherwise remove it from the DOM.
    router.events.forEach(event => {
      if (event instanceof NavigationStart) {
        switch(event.url) {
          case '/login':
          case '/payments':
            break;
          default:
            this.widget.remove();
            break;
        }
      }
    });
  }

 async ngOnInit() {
    this.widget.renderEl({
      el: '#okta-signin-container'},
      (res) => {
        if (res.status === 'SUCCESS') {
          this.oktaAuth.loginRedirect('/payments', { sessionToken: res.session.token });
          // Hide the widget
          this.widget.hide();
        }
      },
      (err) => {
        throw err;
      }
    );
  }
}