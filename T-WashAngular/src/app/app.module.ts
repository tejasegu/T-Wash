import {  MatSortModule } from '@angular/material/sort';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './Components/login/login.component';
import { UserComponent } from './Components/user/user.component';
import { AdminComponent } from './Components/admin/admin.component';
import { AuthInterceptor } from './Helper/authinterceptor';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar';
import{ MatButtonModule} from '@angular/material/button';
import{MatIconModule} from '@angular/material/icon';
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatBadgeModule} from '@angular/material/badge';
import {MatMenuModule} from '@angular/material/menu';
import { HomeComponent } from './Components/home/home.component';
import {MatListModule} from '@angular/material/list';
import { SignupComponent } from './Components/signup/signup.component';
import { MyordersComponent } from './Components/myorders/myorders.component';
import { ProfileComponent } from './Components/profile/profile.component';
import {MatTableModule} from '@angular/material/table';
import {MatCardModule} from '@angular/material/card';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatTabsModule} from '@angular/material/tabs';
import {MatDialogModule} from '@angular/material/dialog';
import { ReviewsdataComponent } from './Components/reviewsdata/reviewsdata.component';
import { ShowimageComponent } from './Components/showimage/showimage.component';
import { ReversePipe } from './Components/myorders/reverse';
import { BookingComponent } from './Components/booking/booking.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatStepperModule} from '@angular/material/stepper';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatRadioModule} from '@angular/material/radio';
import {DatePipe} from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MatPaginatorModule } from '@angular/material/paginator';
import { BookingmanagementComponent } from './Components/bookingmanagement/bookingmanagement.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { ReviewmanagementComponent } from './Components/reviewmanagement/reviewmanagement.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { LayoutModule } from '@angular/cdk/layout';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserComponent,
    AdminComponent,
    HomeComponent,
    SignupComponent,
    MyordersComponent,
    ProfileComponent,
    ReviewsdataComponent,
    ShowimageComponent,
    ReversePipe,
    BookingComponent,
    BookingmanagementComponent,
    ReviewmanagementComponent,
    DashboardComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    FlexLayoutModule,
    MatSidenavModule,
    MatBadgeModule,
    MatMenuModule,
    MatListModule,
    MatTableModule,
    MatCardModule,
    MatExpansionModule,
    MatTabsModule,
    MatDialogModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatStepperModule,
    MatInputModule,
    MatSelectModule,
    MatRadioModule,
    MatPaginatorModule,
    MatSortModule,
    MatProgressSpinnerModule,
    MatGridListModule,
    LayoutModule,
    MatSlideToggleModule
    
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }, DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
