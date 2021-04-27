import { Router } from '@angular/router';
import { TokenStorageService } from './../../Services/Security/token-storage.service';
import { UseService } from './../../Services/Service/use.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/Classes/user';
import { WasherService } from 'src/app/Services/Service/washer.service';
import { Addons } from 'src/app/Classes/addons';
import { DatePipe } from '@angular/common';

const locapi='http://www.google.com/maps/place/'
@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {

  form:any={
       bookedon:Date,
       scheduledlater:false,
        scheduleddate:Date,
        userid:"",
        username:"",
        number:null,
        carname:"",
        carnumber:"",
        area:"",
        location:"",
        washerid:"",
        washername:"",
        washernumber:0,
        packages:"",
        addon:"",
        orderstatus:"Booked",
        paymenttype:"",
        transactionid:"null",
        paymentstatus:"Not Completed",
        amount:0

  }
  type:string="";
  small=false;
  medium=false;
  large=false;
  open=false;
  types=['Hatchback','SUV','XUV'];
  mobNumberPattern = "^[0-9]{10}$";
  smallpackage=[{name:'Bronze', value:500},{name:'Silver',value:600},{name:'Gold',value:700}];
  mediumpackage=[{name:'Bronze', value:550},{name:'Silver',value:650},{name:'Gold',value:750}];
  largepackage=[{name:'Bronze', value:600},{name:'Silver',value:700},{name:'Gold',value:800}]
  areas=["Market","Police Station","Main Road","Public Park"];
  currLat:number=0;
  currLng:number=0;
  washermessage="";
  washers:User[]=[];
  washer:any;
  washpackage:any;
  addons:Addons[]=[];
  addonselected:any;
  addonvalue:number=0;
  todaydateTime= Date.now();
  dateTime=new Date();

  constructor(private washerservice:WasherService, private userservice:UseService, private datepipe:DatePipe, private tokenstorage:TokenStorageService, private router:Router) {
    this.form.userid=this. tokenstorage.getUser().id;
    this.dateTime.setDate(this.dateTime.getDate() + 3); 
  }

  ngOnInit(): void {
   this.userservice.getaddons().subscribe(data=>{this.addons=data}, error=>{console.log(error)})
 
  }
 
  booknow(){
    this.open=true;

  }
  schedule(){
    this.open=true;
    this.form.scheduledlater=true;
  }
  getlocation(){
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(position => {

        this.currLat = position.coords.latitude;
        this.currLng = position.coords.longitude;
        this.form.location=locapi+this.currLat+","+this.currLng;
       
      });
    }
    else {
      alert("Geolocation is not supported by this browser.");
    }
  }

  next1(){

    this.washerservice.getListOfWashersByArea(this.form.area).subscribe(data=>{if(!!data){this.washermessage="Currently all washers are busy. Please try after some time"}this.washers=data, this.washermessage=""},error=>{console.log(error); this.washermessage="All Washers are currently busy please try after some time"})
    if(this.type.match("Hatchback")){
      this.small=true;
      this.medium=false;
      this.large=false;
    }
    if(this.type.match("SUV")){
      this.medium=true;
      this.small=false;
      this.large=false;
    }
    if(this.type.match("XUV")){
      this.large=true;
      this.medium=false;
      this.small=false;
    }

  }
  next2(){

    this.form.washerid=this.washer.id;
    this.form.washername=this.washer.name;
    this.form.washernumber=this.washer.mobilenumber;
    this.form.packages=this.washpackage.name;
    this.form.addon=this.addonselected.addonname;
    this.form.bookedon=new Date();
    if(!this.addonselected.price){
      this.form.amount=parseInt(this.washpackage.value);
     
    }
    this.form.amount=parseInt(this.washpackage.value)+parseInt(this.addonselected.price);
   
    console.log(this.form)
  }
 submitcash(){
   this.userservice.addcashbooking(this.form).subscribe(data=>{alert(data); this.router.navigate(['home'])},error=>{alert("Something went wrong please try again"), console.log(error)})
 }
 submitonline(){
   this.userservice.addonlinebooking(this.form).subscribe(data=>{alert(data);}, error=>{alert(error)})
 }
  }


