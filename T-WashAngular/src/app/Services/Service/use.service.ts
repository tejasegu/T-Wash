import { Notifications } from './../../Classes/notifications';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenStorageService } from '../Security/token-storage.service';
import { User } from 'src/app/Classes/user';
import { Bookings } from 'src/app/Classes/bookings';
import { Review } from 'src/app/Classes/review';
import { Addons } from 'src/app/Classes/addons';
export interface addon{
  id:String,
  addonname:String,
  price:String

}

const API_URL = 'http://localhost:8079/users';
@Injectable({
  providedIn: 'root'
})

export class UseService implements OnInit  {
  authToken:any=this.auth.getToken();    ;
  constructor(private http: HttpClient, private auth:TokenStorageService) { }
  ngOnInit(): void {
    
  }
 
  

  getUserById(id:string): Observable<any> {
    return this.http.get(API_URL + '/get/'+id);
   
  }
  updatUserById(id:string, user:User){
    return this.http.put(API_URL + '/update/'+id, user, { responseType: 'text' })
  }
  getNotificationCount(id:string):Observable<any>{
    return this.http.get(API_URL+"/notification/count/"+id , { responseType: 'text' } );
  }
  getListOfNotifications(id:string){
    return this.http.get<Notifications[]>(API_URL+"/notification/find/"+id  );

  }
  getListOfOrders(id:string){
    return this.http.get<Bookings[]>(API_URL+"/bookings/find/user/"+id);
  }
  postReview(revie:Review){
    return this.http.post(API_URL+'/review',revie,{ responseType: 'text' });
  }
  getListOfScheduledOrders(id:string){
    return this.http.get<Bookings[]>(API_URL+"/bookings/scheduled/user/"+id);
  }
  getaddons(){
    return this.http.get<Addons[]>(API_URL+"/addon")
  }
  addcashbooking(form:any){
    return this.http.post(API_URL+"/bookings/cash",form,{ responseType: 'text' });

  }
  addonlinebooking(form:any){
    return this.http.post("http://localhost:1112/booking/online",form, { responseType: 'text' } );

  }
}
