import { User } from './../../Classes/user';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { AuthService } from 'src/app/Services/Security/authservice.service';
import { TokenStorageService } from 'src/app/Services/Security/token-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  isAdmin=false;
  roles=["ROLE_USER","ROLE_WASHER"];
  form: User = {
    name: "",
    gender:"",
    password:"",
    email: "",
    mobilenumber: +91,
    town:"Palakollu",
    area:"",
    role: "ROLE_USER",
    status:"Active",
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';


  constructor(private authService: AuthService, private tokenStorageService: TokenStorageService, private route:Router) { }
  mobNumberPattern = "^((\\91-?)|0)?[0-9]{10}$";  
  towns="Palakollu";
  areas=["Market","Police Station","Main Road","Public Park"];
 
  ngOnInit(): void {
    
     
  }

  onSubmit(): void {
    this.authService.register(this.form).subscribe(
      data => {
        
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        alert(data)
        this.route.navigate(['/login'])

      },
      error => {
        console.log(error);
        if(error.status==500){
          this.errorMessage ="Email or mobile already registered";
        this.isSignUpFailed = true;
        }
        
      }
    );
  }
}

