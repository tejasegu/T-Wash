import { HTTP_INTERCEPTORS, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';

import { TokenStorageService } from '../Services/Security/token-storage.service';
import { Observable } from 'rxjs';

const TOKEN_HEADER_KEY = 'Authorization';     

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private token: TokenStorageService) { }
  
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let authService = this.token.getToken();
    let tokenizedReq = req.clone(
      {
        headers: req.headers.set('Authorization', 'Bearer ' + authService)
      }
    )
    return next.handle(tokenizedReq)
  }
}

