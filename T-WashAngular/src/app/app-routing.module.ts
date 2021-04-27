import { BookingmanagementComponent } from './Components/bookingmanagement/bookingmanagement.component';
import { SignupComponent } from './Components/signup/signup.component';

import { HomeComponent } from './Components/home/home.component';
import { AdminComponent } from './Components/admin/admin.component';
import { UserComponent } from './Components/user/user.component';
import { NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './Components/login/login.component';
import { AuthGuard } from './Services/Security/auth.guard';
import { ProfileComponent } from './Components/profile/profile.component';
import { MyordersComponent } from './Components/myorders/myorders.component';
import { BookingComponent } from './Components/booking/booking.component';
import { ReviewmanagementComponent } from './Components/reviewmanagement/reviewmanagement.component';
import { DashboardComponent } from './dashboard/dashboard.component';

const routes: Routes = [
  {path:'home', component:HomeComponent},
  { path: 'usersmanagement', component:UserComponent,canActivate:[AuthGuard], data:{roles:['ROLE_ADMIN']} },
  { path: 'bookingmanagement', component:BookingmanagementComponent,canActivate:[AuthGuard], data:{roles:['ROLE_ADMIN']} },
  { path: 'reviewsmanagement', component:ReviewmanagementComponent,canActivate:[AuthGuard], data:{roles:['ROLE_ADMIN']} },
  { path: 'dashboard', component:DashboardComponent,canActivate:[AuthGuard], data:{roles:['ROLE_ADMIN']} },

  { path: 'login', component: LoginComponent },
  {path:'signup', component:SignupComponent},
  { path: 'admin', component: AdminComponent,canActivate:[AuthGuard], data: {roles:["ROLE_ADMIN"]} },
  {path:'',component:HomeComponent},
  { path: 'profile', component: ProfileComponent,canActivate:[AuthGuard], data:{roles:['ROLE_USER','ROLE_ADMIN','ROLE_WASHER']}},
  { path: 'orders', component: MyordersComponent,canActivate:[AuthGuard], data:{roles:['ROLE_USER','ROLE_ADMIN','ROLE_WASHER']}},
  { path: 'book', component: BookingComponent, canActivate:[AuthGuard], data:{roles:['ROLE_USER','ROLE_ADMIN','ROLE_WASHER']}},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
