import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { AlertService } from '../_alert';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = ''
  password = ''
  invalidLogin = false
  public error: any;

  constructor(private router: Router,
    private loginservice: AuthenticationService,
    protected alertService: AlertService) { }

  ngOnInit() {
  }

  checkLogin() {
    (this.loginservice.authenticate(this.username, this.password).subscribe(
      data => {
        this.router.navigate(['payments'])
        this.invalidLogin = false
        this.alertService.success(data);
      },
      error => {
        this.invalidLogin = true
        this.error = error.error; 
        this.alertService.error(this.error);   }
    )
    );

  }

}