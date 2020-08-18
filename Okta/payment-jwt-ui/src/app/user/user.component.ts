import { Component, OnInit } from '@angular/core';
import { HttpClientService, User } from '../services/httpclient.service';
import { AlertService } from '../_alert';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  users:User[];
  public error: any;

  constructor(
    private httpClientService:HttpClientService, protected alertService: AlertService
  ) { }

  ngOnInit() {
    this.httpClientService.getUsers().subscribe(
      response=>this.handleSuccessfulResponse(response),
      error => {this.error = error.error;
        this.alertService.error(this.error.error);
      }
    );
  }

  deleteUser(user: User): void {
    this.httpClientService.deleteUser(user)
      .subscribe( data => {
        this.users = this.users.filter(u => u !== user);
        this.alertService.success("Succesfully Deleted User " + data.username);
      },
      error => {this.error = error;
        this.alertService.error("Unable To Delete Payment" + this.error);
      }
      )
  };

  handleSuccessfulResponse(response)
  {
    this.users=response;
  }

}
