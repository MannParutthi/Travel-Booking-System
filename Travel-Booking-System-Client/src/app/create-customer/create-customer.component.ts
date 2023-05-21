import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CreateCustomerService } from './create-customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.scss']
})
export class CreateCustomerComponent implements OnInit {

  formGroup: FormGroup = this.formBuilder.group({
    'id': [0, []],
    'firstName': [null, []],
    'lastName': [null, []],
    'userType': [null, []],
    'dateOfBirth': [null, []],
    'email': [null, []],
    'password': [null, []]
  });

  createCustomerAPIResponse: any;

  allCustomersList: any[] = [];

  displayedColumns: string[] = ['id', 'firstName', 'lastName', 'userType', 'dateOfBirth', 'email'];

  constructor(private _router: Router, private formBuilder: FormBuilder, private createCustomerService: CreateCustomerService) { }

  ngOnInit(): void {
    this.getAllCustomers();
    if(localStorage.getItem("user")!=null) {
      this._router.navigateByUrl('/home')
    }
  }

  createCustomer() {
    this.createCustomerService.createCustomer(this.formGroup.getRawValue()).subscribe((res) => {
      this.createCustomerAPIResponse = res;
      this.getAllCustomers();
    });
  }

  getAllCustomers() {
    this.createCustomerService.getAllCustomers().subscribe((res) => {
      this.allCustomersList = res;
    });
  }

}
