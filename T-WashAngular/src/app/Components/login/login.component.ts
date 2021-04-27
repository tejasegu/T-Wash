import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../Services/Security/authservice.service';
import { TokenStorageService } from '../../Services/Security/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: any = {
    username: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  username:String='';
  loading=false;
  constructor(private router: Router,private authService: AuthService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
      this.username=this.tokenStorage.getUser().username;
      setTimeout(() => {
        setTimeout(() => {
          this.router.navigate(['home']);
        });
      }, 1000);
    }
  }
  onSubmit(): void {
    const { username, password } = this.form;

    this.loading = true;
    this.authService.login(username, password).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
       
          this.reloadPage();
        
       
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
        this.loading = false;
      }
    );
    
  }

  reloadPage(): void {
   window.location.reload() ;
   
  }

}
