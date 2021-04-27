import { Bookings } from './../../Classes/bookings';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserId } from 'src/app/Classes/userwithid';
import { Review } from 'src/app/Classes/review';

const API_URL="http://localhost:8079/admin"
@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http:HttpClient) { }
  getAllUsers(){
    return this.http.get<UserId[]>(API_URL+'/get')
  }
  deleteUsers(id:string){
    return this.http.delete(API_URL+'/delete/'+id, { responseType: 'text' })
  }
  findUserById(id:string){
    return this.http.get<UserId>(API_URL+"/find/"+id)
  }
  getAllBookings(){
    return this.http.get<Bookings[]>(API_URL+"/bookings")
  }
  getBookingById(id:string){
    return this.http.get(API_URL+"/bookings/find/"+id)
  }
  deleteBookingByID(id:string){
    return this.http.delete(API_URL+"/bookings/delete/"+id, { responseType: 'text' })
  }
  getAllReviews(){
    return this.http.get<Review[]>(API_URL+"/reviews")
  }
  getReviewsBySpecificWasherId(id:string){
    return this.http.get<Review[]>(API_URL+"/reviews/"+id)
  }
  getRatingBySpecificWasherID(id:string){
    return this.http.get(API_URL+"/rating/"+id, {responseType:'text'})
  }
  getTotalIncome(){
    return this.http.get(API_URL+"/income",{responseType:'text'})
  }
}
