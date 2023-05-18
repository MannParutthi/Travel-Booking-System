import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CreateBookingService } from './create-booking.service';

@Component({
  selector: 'app-create-booking',
  templateUrl: './create-booking.component.html',
  styleUrls: ['./create-booking.component.scss']
})
export class CreateBookingComponent implements OnInit {

  formGroup: FormGroup = this.formBuilder.group({
    'id': [0, []],
    'customerId': [null, []],
    'travelPackageId': [null, []],
    'departureDate': [null, []],
    'bookingStatus': [null, []],
  });

  createBookingAPIResponse: any;

  allBookingsList: any[] = [];

  allCustomersList: any[] = [];

  allTravelPackagesList: any[] = [];

  displayedColumns: string[] = ['id', 'customerId', 'travelPackageId', 'departureDate', 'bookingStatus'];

  constructor(private formBuilder: FormBuilder, private createBookingService: CreateBookingService) { }

  ngOnInit(): void {
    this.getAllCustomers();
    this.getAllTravelPackages();
    this.getAllBookings();
  }

  createBooking() {
    console.log("createBookingFormGroup ==> " + JSON.stringify(this.formGroup.getRawValue()));
    this.createBookingService.createBooking(this.formGroup.getRawValue()).subscribe((res) => {
      console.log("createBookingAPIResponse ==> " + res);
      this.createBookingAPIResponse = res;
      this.getAllBookings();
    });
  }

  getAllBookings() {
    this.createBookingService.getAllBookings().subscribe((res) => {
      this.allBookingsList = res;
      console.log("getAllBookings ==> " + res);
    });
  }

  getAllCustomers() {
    this.createBookingService.getAllCustomers().subscribe((res) => {
      this.allCustomersList = res;
      console.log("getAllCustomers ==> " + res);
    });
  }

  getAllTravelPackages() {
    this.createBookingService.getAllTravelPackages().subscribe((res) => {
      this.allTravelPackagesList = res;
      console.log("getAllTravelPackages ==> " + res);
    });
  }
}
