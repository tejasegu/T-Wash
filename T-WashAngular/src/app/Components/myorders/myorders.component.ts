import { UseService } from './../../Services/Service/use.service';
import { TokenStorageService } from 'src/app/Services/Security/token-storage.service';
import { Component,Inject,OnInit} from '@angular/core';
import { Bookings } from 'src/app/Classes/bookings';
import { Review } from 'src/app/Classes/review';
import { ReviewsdataComponent } from '../reviewsdata/reviewsdata.component';
import { WasherService } from 'src/app/Services/Service/washer.service';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-myorders',
  templateUrl: './myorders.component.html',
  styleUrls: ['./myorders.component.css']
})
export class MyordersComponent implements OnInit {
orders:Bookings[]=[];
scheduled:Bookings[]=[];
public counts = [{status:"Booked", logo:"Booked"},{status:"Accepted",logo:"Accepted",},{status:"In progress",logo:"local_car_wash"},
  {status:"Completed",logo:"done"}];
  selectedFile: any;

constructor(public dialog: MatDialog, private tokenstorage:TokenStorageService , private userservice:UseService, private washerservice:WasherService) { }

review:Review={id:"",username:"",userid:"",washerid:"",rating:0,review:""};
iswasher=false;
isuser=false;
roles:any;
message:any;
schmessage:any;
imgmessage:any;
imgdata:any;
retrivedimg:any;

  
  ngOnInit(): void {
    const user=this.tokenstorage.getUser()
    this.roles=user.roles;
    this.iswasher=this.roles.includes('ROLE_WASHER');
    this.isuser=this.roles.includes('ROLE_USER');
    if(this.isuser){
      this.userservice.getListOfOrders(user.id).subscribe(data=>{
        if(data){
          this.orders=data;
         
        }
        else{this.message="No Bookings" }
        }, error=>{console.log(error)}
        );

        this.userservice.getListOfScheduledOrders(user.id).subscribe(data=>{
          if(data){
            this.scheduled=data;
          }
          else{
            this.schmessage="No Bookings"
          }
           }, error=>{console.log(error)})
    }
    if(this.iswasher){
      this.washerservice.getListOfOrders(user.id).subscribe(data=>{
        if(!data){
          this.message="No Bookings"
        }
        this.orders=data; }, error=>{console.log(error)});

        this.washerservice.getListOfScheduledOrders(user.id).subscribe(data=>{
          if(!data){
            this.schmessage="No Bookings"
          }
          this.scheduled=data; }, error=>{console.log(error)})
    }


  }

  openDialog(userid:string, washerid:string,username:string): void {
    const dialogRef = this.dialog.open(ReviewsdataComponent, {width: '600px', height:'300px',
      
      data: {username:username, userid:userid ,washerid:washerid,rating:this.review.rating,review:this.review.review}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.review = result;
      this.userservice.postReview(result).subscribe(data=>{{alert(data); window.location.reload()}}, error=>{console.log(error); alert('Something went wrong')})
    });
  }
  Accepted( id:any){
    this.washerservice.setSttatus('Accepted',id).subscribe(data=>{{alert(data); window.location.reload()}}, error=>{console.log(error); alert('Something went wrong')})
  }
 Rejected( id:any){
    this.washerservice.setSttatus('Rejected',id).subscribe(data=>{{alert(data); window.location.reload()}}, error=>{console.log(error); alert('Something went wrong')})
  }
  Start( id:any){
    this.washerservice.setSttatus('In progress',id).subscribe(data=>{{alert(data); window.location.reload()}}, error=>{console.log(error); alert('Something went wrong')})
  }
  Completed( id:any){
    this.washerservice.setSttatus('Completed',id).subscribe(data=>{{alert(data); window.location.reload()}}, error=>{console.log(error); alert('Something went wrong')})
  }

  onFileChanged(event:any) {
    this.selectedFile = event.target.files.item(0)
  }

 apload(id:string){
  console.log(this.selectedFile)
  this.washerservice.setImg(id, this.selectedFile).subscribe(data=>{this.imgmessage=data, alert(data)}, error=>{console.log(error)})
  }
 showImage(id:string){
   this.washerservice.getImg(id).subscribe(data=>{this.imgdata=data; },error=>{console.log(error)})
   setTimeout(()=>{this.retrivedimg = 'data:image/jpeg;base64,'+ this.imgdata;}, 1000) 
   console.log(this.retrivedimg)
   setTimeout(()=>{const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
    
    data: {image:this.retrivedimg}
  });}, 2000)
   
 

 }
navigate(location:any){
  window.open(location),'_blank';
}
}

@Component({
  selector: 'dialog-overview-example-dialog',
  templateUrl: 'dialog-overview-example-dialog.html',
})
export class DialogOverviewExampleDialog {

  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}