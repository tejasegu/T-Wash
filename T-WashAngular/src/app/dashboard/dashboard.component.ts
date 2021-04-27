import { AdminService } from './../Services/Service/admin.service';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
 amount:any;
 orders:any;
 users:any;
  constructor(private adminservice:AdminService) {}
  ngOnInit(): void {
    this.adminservice.getAllBookings().subscribe(data=>{this.orders=data.length})
    this.adminservice.getAllUsers().subscribe(data=>{this.users=data.length})
    this.adminservice.getTotalIncome().subscribe(data=>{this.amount=data})
  }
}
