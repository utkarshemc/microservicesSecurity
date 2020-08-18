import { Component, OnInit } from '@angular/core';
import { HttpClientService, User } from '../services/httpclient.service';
import { AlertService } from '../_alert';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})

export class AddUserComponent implements OnInit {

  user: User = new User(0,"","","",true);
  public error: any;
  public success : any;
  actives: boolean[] = [true, false];

  constructor(
    private httpClientService: HttpClientService,protected alertService: AlertService
  ) { }



  ngOnInit() {
  }

  createUser(): void {
    this.httpClientService.createUser(this.user)
        .subscribe( data => {
          this.alertService.success(data);
        },
        error => {
          var obj = JSON.parse(error.error)
          this.alertService.error(obj.message);
        }
        );

  };

}
