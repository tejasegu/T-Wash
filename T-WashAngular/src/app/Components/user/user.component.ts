import { AuthService } from './../../Services/Security/authservice.service';
import { UseService } from './../../Services/Service/use.service';
import { AdminService } from './../../Services/Service/admin.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { UserId } from 'src/app/Classes/userwithid';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})


export class UserComponent implements OnInit {

  search:any;
  displayedColumns: string[] = ['id', 'name', 'gender', 'email','mobilenumber','area','role','status','delete'];
  dataSource!: MatTableDataSource<UserId>;
  form:any={
    id:null,
    name:'',
    gender:'',
    password:'',
    email:'',
    mobilenumber:'',
    town:'',
    area:'',
    role:'',
    status:''
  }
  add:any={
    id:null,
    name:'',
    gender:'',
    password:'',
    email:'',
    mobilenumber:'',
    town:'Palakollu',
    area:'',
    role:'',
    status:'Active'
  }
  editable=false;
  mobPattern= "^[789]\d{9}$";
  areas=["Market","Police Station","Main Road","Public Park"];
  genders=['Male','Female'];
  roles=['ROLE_USER','ROLE_WASHER']
  constructor( private adminservice:AdminService, private userservice:UseService, private authservice:AuthService) {
    this.adminservice.getAllUsers().subscribe(data=>{this.dataSource=new MatTableDataSource(data);  this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;});
   }
  ngAfterViewInit(): void {

  }

  
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;

  
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  ngOnInit(): void {
   
    
  }
  
  Delete(id:string){
    this.adminservice.deleteUsers(id).subscribe(data=>{alert(data); window.location.reload()},error=>{alert('Some thing went wrong please try again')})
  }

  searchuser(){
    this.adminservice.findUserById(this.search).subscribe(data=>{this.form=data},error=>{console.log(error) ;if(error.status==404){alert("User Not Found")}})
    this.editable=true;
  }
  onSubmit2(){
    this.editable=true;
    this.userservice.updatUserById(this.form.id, this.form).subscribe(data=>{alert(data); window.location.reload()},error=>{console.log(error)})
  }
  cancelUpdate(){
    this.editable=false;
  }
  onSubmit(){

    this.authservice.register(this.add).subscribe(data=>{alert(data); window.location.reload()}, error=>{
      console.log(error);
        if(error.status==500){
          alert("Email or mobile already registered");
        }
    })
  }
}


 

 

 
  



