import { TokenStorageService } from './../../Services/Security/token-storage.service';
import { UseService } from './../../Services/Service/use.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/Classes/user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  form: User = {
    name: "",
    gender:"",
    password:"",
    email: "",
    mobilenumber:0,
    town:"",
    area:"",
    role: "",
    status:"",
  };
  edited=false;
  isSuccessful = false;
  isSignUpFailed = false;
  roles=["ROLE_USER","ROLE_WASHER"]
  areas=["Market","Police Station","Main Road","Public Park"];
  mobNumberPattern = "^[789]\d{9}$";  
  id:string="";
  
  constructor(private userservice:UseService, private token:TokenStorageService) { }

  ngOnInit(): void {

    this.id=this.token.getUser().id;
console.log(this.id)
    this.userservice.getUserById(this.id).subscribe(data=>{this.form=data},error=>{console.log(error)})
    
  }
  onSubmit(): void {
    this.edited=false;
    console.log(this.form)
    this.userservice.updatUserById(this.id,this.form).subscribe(data=>{alert(data)},error=>{ if(error.status==500){alert("Email or mobile number already exit"),console.log(error)}})
  }
  edit(){
    this.edited=true;
  }
  cancel(){
    this.edited=false;
    this.userservice.getUserById(this.id).subscribe(data=>{this.form=data},error=>{console.log(error)})
  }

}
