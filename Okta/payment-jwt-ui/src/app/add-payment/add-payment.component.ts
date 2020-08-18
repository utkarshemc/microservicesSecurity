import { Component, OnInit } from '@angular/core';
import { HttpClientService, Payment } from '../services/httpclient.service';
import { AuthenticationService } from '../services/authentication.service';
import { AlertService } from '../_alert';

@Component({
  selector: 'app-add-payment',
  templateUrl: './add-payment.component.html',
  styleUrls: ['./add-payment.component.css']
})
export class AddPaymentComponent implements OnInit {

  pay: Payment = new Payment("",0,"","");
  public error: any;

  constructor(
    private httpClientService: HttpClientService,
    private authService: AuthenticationService,
    protected alertService: AlertService
  ) { }

  ngOnInit() {
  }

  createPayment(): void {
    this.pay.userid = this.authService.getCurrentUserId();
    this.pay.emailid = this.authService.getCurrentEmailId();
    this.httpClientService.createPayment(this.pay)
        .subscribe( data => {
          this.alertService.success(data);
        },
        error => {var obj = JSON.parse(error.error)
          this.alertService.error(obj.message);
        });

  };

}
	