import { WasherService } from './Services/Service/washer.service';
import { Notifications } from './Classes/notifications';
import { UseService } from './Services/Service/use.service';
import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './Services/Security/token-storage.service';
import { MatSlideToggleChange } from '@angular/material/slide-toggle';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements  OnInit  {
  private roles: string[] = [];
  isLoggedIn = false;
  admin = false;
  user = false;
  washer = false;
  username?: string;
  id:string='';
  count=0;
  notification:Notifications[]=[];
  isChecked:any;
  users:any;

  constructor(private tokenStorageService: TokenStorageService, private userservice:UseService, private washerservice:WasherService) { }

  ngOnInit(): void {

    
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
  
      this.admin = this.roles.includes('ROLE_ADMIN');
      this.user = this.roles.includes('ROLE_USER');
      this.washer = this.roles.includes('ROLE_WASHER');
  
      this.username = user.username;
      this.id=user.id;
      console.log(this.id)
      this.userservice.getNotificationCount(this.id).subscribe(data=>{this.count=data
      console.log(this.count)}, err => {
        this.count=0;
      }
      )

      this.userservice.getListOfNotifications(this.id).subscribe(data=>{this.notification=data},err=>{console.log(err.message)});

      this.userservice.getUserById(this.id).subscribe(data=>{this.users=data.status; if(this.users==="Active"){
        this.isChecked=true;
      } else{this.isChecked=false;}console.log(this.users)})
      
      
    } 

   


  }

 
 
  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

  handleChange(event: MatSlideToggleChange) {
    if (this.isChecked) {
      this.washerservice.setAccStatus(this.id,'Active').subscribe(data=>{alert(data), this.isChecked=true}, error=>{this.isChecked=true})
    } 
    else{
      this.washerservice.setAccStatus(this.id,'In Active').subscribe(data=>{alert(data), this.isChecked=false}, error=>{this.isChecked=false;})

    }
  }

  
  


}
