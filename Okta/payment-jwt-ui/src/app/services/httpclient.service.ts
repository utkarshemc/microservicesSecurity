import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

export class User{
  constructor(
    public id:number,
    public username:string,
    public password:string,
    public roles:string,
    public active:boolean,
  ) {}
}

export class Payment{
  constructor(
    public userid:string,
    public amount:number,
    public method:string,
    public emailid:string
  ){}
}

export class PaymentResponse{
  constructor(
    public transactionId:number,
    public date: Date,
    public userId:string,
    public amount:number,
    public method:string,
  ){}
}

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(
    private httpClient:HttpClient
  ) { 
  }

  getUsers()
  {
    return this.httpClient.get<User[]>('http://localhost:9094/user/allUser');
  }

  public deleteUser(user) {

    return this.httpClient.delete<User>("http://localhost:9094/user/deleteUser" + "/"+ user.id);
  }

  public createUser(user) {

    const param = {responseType: 'text'};
    return this.httpClient.post("http://localhost:9094/user/signup", user, {responseType: 'text'} );
  }

  public createPayment(pay) {
    return this.httpClient.post("http://localhost:9095/payment/newPayment", pay, {responseType: 'text'});
  }

  getPayments()
  {
    return this.httpClient.get<PaymentResponse[]>('http://localhost:9095/payment/allPayment');
  }

  public deletePayment(payResponse) {

    return this.httpClient.delete<Text>("http://localhost:9095/payment/deletePayment" + "/"+ payResponse.transactionId);
  }
}
