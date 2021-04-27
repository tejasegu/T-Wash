import { HttpClient, HttpEvent, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Bookings } from 'src/app/Classes/bookings';
import { User } from 'src/app/Classes/user';

const API_URL = 'http://localhost:8079/washers';
const API_URI = 'http://localhost:1112';


@Injectable({
  providedIn: 'root'
})
export class WasherService {
  body:any;
  data: FormData=new FormData();
  constructor(private http: HttpClient) { }

  getListOfOrders(id:string){
    return this.http.get<Bookings[]>(API_URL+"/bookings/find/washer/"+id);
  }
  setSttatus(status:string, id:string){
    
    return this.http.put(API_URL+"/bookings/update/"+id+"/"+status, this.body,{ responseType: 'text' })
  }

  getListOfScheduledOrders(id:string){
    return this.http.get<Bookings[]>(API_URL+"/bookings/scheduled/washer/"+id)
  }

  getListOfWashersByArea(area:string){
    return this.http.get<User[]>(API_URL+"/area/"+area)
  }
  setAccStatus(id:any,status:String){
    return this.http.put(API_URL+"/set/"+id+"/"+status, this.body, {responseType:'text'})

  }
  setImg(id:string, file:File)
  {let formData = new FormData();
      
    formData.append('image', file,id);
    const headers = new HttpHeaders();
        headers.append('Content-Type', 'multipart/form-data');
        headers.append('Response-Type', 'text');

    return this.http.post(API_URI+'/images/'+id+'/add',formData, {responseType:'text'})}
   
  getImg(id:string){
    return this.http.get(API_URL+'/images/'+id, {responseType:'text'})
  }
}

