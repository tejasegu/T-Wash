import { AdminService } from './../../Services/Service/admin.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Review } from 'src/app/Classes/review';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-reviewmanagement',
  templateUrl: './reviewmanagement.component.html',
  styleUrls: ['./reviewmanagement.component.css']
})
export class ReviewmanagementComponent implements OnInit {
  displayedColumns: string[] = [ 'userid', 'username', 'washerid','rating','review'];
  dataSource!: MatTableDataSource<Review>;
  dataSource2!: MatTableDataSource<Review>;
  rating:any=0;
search:any;

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;
  constructor( private adminservice:AdminService) { }

  ngOnInit(): void {
    this.adminservice.getAllReviews().subscribe(data=>{this.dataSource=new MatTableDataSource(data); this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;},error=>{console.log(error)})
  }
 
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
 
  searchReview(){
    this.adminservice.getReviewsBySpecificWasherId(this.search).subscribe(data=>{this.dataSource2=new MatTableDataSource(data); if(!data){alert("REVIEWS Not Found FOR ID")}},error=>{console.log(error) ;if(error.status==401){alert("REVIEWS Not Found")}})
    this.adminservice.getRatingBySpecificWasherID(this.search).subscribe(data=>{this.rating=data},error=>{console.log(error) })
}
}
