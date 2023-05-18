import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CreateCustomerService } from './create-customer.service';

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.scss']
})
export class CreateCustomerComponent implements OnInit {

  formGroup: FormGroup = this.formBuilder.group({
    'id': [0, []],
    'firstName': [null, []],
    'lastName': [null, []],
    'dateOfBirth': [null, []],
    'email': [null, []],
  });

  createCustomerAPIResponse: any;

  allCustomersList: any[] = [];

  displayedColumns: string[] = ['id', 'firstName', 'lastName', 'dateOfBirth', 'email'];

  constructor(private formBuilder: FormBuilder, private createCustomerService: CreateCustomerService) { }

  ngOnInit(): void {
    this.getAllCustomers();
  }

  createCustomer() {
    this.createCustomerService.createCustomer(this.formGroup.getRawValue()).subscribe((res) => {
      this.createCustomerAPIResponse = res;
      console.log("createCustomerAPIResponse ==> " + this.createCustomerAPIResponse);
      this.getAllCustomers();
    });
  }

  getAllCustomers() {
    this.createCustomerService.getAllCustomers().subscribe((res) => {
      this.allCustomersList = res;
      console.log("getAllCustomers ==> " + res);
    });
  }

}
