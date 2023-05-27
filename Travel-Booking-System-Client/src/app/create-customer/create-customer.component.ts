import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { CreateCustomerService } from './create-customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.scss']
})
export class CreateCustomerComponent implements OnInit {

  formGroup: FormGroup;

  createCustomerAPIResponse: any;

  allCustomersList: any[] = [];

  today = new Date();

  displayedColumns: string[] = ['id', 'firstName', 'lastName', 'userType', 'dateOfBirth', 'email'];

  constructor(
    private _router: Router,
    private formBuilder: FormBuilder,
    private createCustomerService: CreateCustomerService
  ) {
    this.formGroup = this.formBuilder.group({
      id: [0],
      firstName: [null, Validators.required],
      lastName: [null, Validators.required],
      userType: [null, Validators.required],
      dateOfBirth: ['', [Validators.required, this.maxDateValidator.bind(this)]],
      email: [null, [Validators.required, Validators.email]],
      password: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.getAllCustomers();
    if (localStorage.getItem('user') != null) {
      this._router.navigateByUrl('/home');
    }
  }

  createCustomer() {
    if (this.formGroup.valid) {
      this.createCustomerService.createCustomer(this.formGroup.getRawValue()).subscribe((res) => {
        this.createCustomerAPIResponse = res;
        this.getAllCustomers();
      });
    } else {
      this.formGroup.markAllAsTouched();
    }
  }

  getAllCustomers() {
    this.createCustomerService.getAllCustomers().subscribe((res) => {
      this.allCustomersList = res;
    });
  }

  maxDateValidator(control: AbstractControl): ValidationErrors | null {
    const selectedDate = control.value;
    const currentDate = new Date();

    return selectedDate <= currentDate ? null : { max: true };
  }
}
