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
    public userid:number,
    public amount:number,
    public method:string,
  ){}
}

export class PaymentResponse{
  constructor(
    public transactionId:number,
    public date: Date,
    public userId:number,
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
    return this.httpClient.get<User[]>('http://localhost:8080/user/allUser');
  }

  public deleteUser(user) {

    return this.httpClient.delete<User>("http://localhost:8080/user/deleteUser" + "/"+ user.id);
  }

  public createUser(user) {

    const param = {responseType: 'text'};
    return this.httpClient.post("http://localhost:8080/user/signup", user, {responseType: 'text'} );
  }

  public createPayment(pay) {
    return this.httpClient.post("http://localhost:8085/payment/newPayment", pay, {responseType: 'text'});
  }

  getPayments()
  {
    return this.httpClient.get<PaymentResponse[]>('http://localhost:8080/user/allPayment');
  }

  public deletePayment(payResponse) {

    return this.httpClient.delete<Text>("http://localhost:8085/payment/deletePayment" + "/"+ payResponse.transactionId);
  }
}
