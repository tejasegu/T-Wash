import { WasherService } from './../../Services/Service/washer.service';
import { AdminService } from './../../Services/Service/admin.service';
import { Bookings } from './../../Classes/bookings';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-bookingmanagement',
  templateUrl: './bookingmanagement.component.html',
  styleUrls: ['./bookingmanagement.component.css']
})
export class BookingmanagementComponent implements AfterViewInit {

  displayedColumns: string[] = ['id', 'scheduledlater', 'scheduleddate', 'userid','username','washerid','washername','orderstatus','amount', 'delete'];
  dataSource!: MatTableDataSource<Bookings>;
err:any;
  search:any;
  form:any={
    bookedon:"",
    scheduledlater:"",
     scheduleddate:"",
     userid:"",
     username:"",
     number:0,
     carname:"",
     carnumber:"",
     area:"",
     location:"",
     washerid:"",
     washername:"",
     washernumber:0,
     packages:"",
     addon:"",
     orderstatus:"",
     paymenttype:"",
     transactionid:"",
     paymentstatus:"",
     amount:0

}
 loading=false;



  constructor(private adminservice:AdminService, private washerservice:WasherService) { 

    this.adminservice.getAllBookings().subscribe(data=>{this.dataSource=new MatTableDataSource(data);  this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;},error=>{console.log(error)});

  }


  ngAfterViewInit(): void {
    
  }

  
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;

  
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  ngOnInit(): void {

  
    
  }
  accept(id:string){
    this.loading=true;
   this.washerservice.setSttatus('Accepted',id).subscribe(data=>{ this.loading=false;alert(data); window.location.reload()},error=>{this.loading=false;alert('Some thing went wrong please try again')})
  }
  reject(id:string){
    this.washerservice.setSttatus('Rejected',id).subscribe(data=>{alert(data); window.location.reload()},error=>{alert('Some thing went wrong please try again')})
  }

  searchBooking(){
       this.adminservice.getBookingById(this.search).subscribe(data=>{this.form=data},error=>{this.err=error.error ; console.log(error);if(error.status==404){alert("Booking Not Found")}})
  }
  deleteBooking(id:string){
    this.adminservice.deleteBookingByID(id).subscribe(data=>{alert(data); window.location.reload()},error=>{alert('Some thing went wrong please try again')})
  }
}
