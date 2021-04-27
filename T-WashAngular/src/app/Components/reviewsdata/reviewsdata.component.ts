import { Component, EventEmitter, Inject, Input, OnInit, Output, ViewEncapsulation } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatDialogRef } from '@angular/material/dialog';
import { Review } from 'src/app/Classes/review';
import { MyordersComponent } from '../myorders/myorders.component';

@Component({
  selector: 'app-reviewsdata',
  templateUrl: './reviewsdata.component.html',
  styleUrls: ['./reviewsdata.component.css'],
})
export class ReviewsdataComponent {

  constructor(
    public dialogRef: MatDialogRef<MyordersComponent>,
    @Inject(MAT_DIALOG_DATA) public data:Review ) {}
    ratin=2;

  onNoClick(): void {
    this.dialogRef.close();
  }
 
  
}
