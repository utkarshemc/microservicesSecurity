import { Injectable } from '@angular/core';
import { HttpClientService } from './httpclient.service';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

export class User{
  constructor(
    public status:string,
     ) {}
  
}

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  userId: string;
  Admin: boolean;
  emailId: string;
  constructor(
    private httpClient:HttpClient
  ) { }

  authenticate(username, password) {
    return this.httpClient.post<any>('http://localhost:8080/user/login',{username,password}).pipe(
     map(
       userData => {
        sessionStorage.setItem('username',username);
        let tokenStr= 'Bearer '+userData.jwtToken;
        sessionStorage.setItem('token', tokenStr);
        return userData;
       }
     )

    );
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    console.log(!(user === null))
    return !(user === null)
  }

  isAdminJwt(){
    let jwt = sessionStorage.getItem('token');
    let jwtData = jwt.split('.')[1];
    let decodedJwtJsonData = window.atob(jwtData);
    let decodedJwtData = JSON.parse(decodedJwtJsonData);

    let Role:string= decodedJwtData.aud;
    console.log(Role);

    if(Role.includes('ADMIN'))
    {
      let isAdmin = true;
      return isAdmin;
    }
    else {
      let isAdmin = false;
      return isAdmin;
    }
  }

  // setAdmin(jwt)
  // {
  //   sessionStorage.setItem('token', jwt);
  // }

   async isAdmin(jwt){
    //let jwt = sessionStorage.getItem('token');
    let jwtData = jwt.split('.')[1];
    let decodedJwtJsonData = window.atob(jwtData);
    let decodedJwtData = JSON.parse(decodedJwtJsonData);

    var Role = decodedJwtData.groups;
    


    //let Role:string= decodedJwtData.groups;
    //console.log(Role);

    if(Role.indexOf("Admin") > -1)
    {
      this.Admin = true;
      return this.Admin;
    }
    else {
      this.Admin = false;
      return this.Admin;
    }
  }

   isAdminvalue()
  {
    return this.Admin;
  };

  getCurrentUserId(){
    //let jwt = sessionStorage.getItem('token');
    //let jwtData = jwt.split('.')[1];
    //let decodedJwtJsonData = window.atob(jwtData);
    //let decodedJwtData = JSON.parse(decodedJwtJsonData);

    //let currentUserId:number= decodedJwtData.jti;

    //return currentUserId;
    //if(currentUserId != null){
    //  return currentUserId;
    //}
    //else {
    //  return 0;
    //}
    return this.userId;
  }

  getCurrentEmailId(){
    return this.emailId;
  }

  async getCurrentUserRole(){
    return this.Admin;
  }

  setCurrentUserId(data, emailId){
      this.userId = data;
      this.emailId = emailId;
  }
  

  logOut() {
    sessionStorage.removeItem('username')
  }
}