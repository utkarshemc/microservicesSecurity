import { Component, OnInit } from '@angular/core';
import { HttpClientService, Payment } from '../services/httpclient.service';
import { AuthenticationService } from '../services/authentication.service';
import { AlertService } from '../_alert';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  payments:PaymentResponse[];
  public error: any;

  constructor(
    private httpClientService:HttpClientService, public loginService:AuthenticationService,protected alertService: AlertService
  ) { }

  ngOnInit() {
    this.httpClientService.getPayments().subscribe(
      response=>this.handleSuccessfulResponse(response),
      error => {this.error = error.error.message;
      }
    );
  }

  deletePayment(pay: PaymentResponse): void {
    this.httpClientService.deletePayment(pay)
      .subscribe( data => {
        this.payments = this.payments.filter(u => u !== pay);
        this.alertService.success("Succesfully Deleted Payment ");
      },
      error => {this.error = error.error;
      this.alertService.error("Unable To Delete Payment" + this.error);
      }

      )
  };

  handleSuccessfulResponse(response)
  {
    this.payments=response;
  }

}
