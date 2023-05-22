import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CreateBookingService } from './create-booking.service';
import { Router } from '@angular/router';

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
    'departureDate': [null, []]
  });

  createBookingAPIResponse: any;

  allBookingsList: any[] = [];

  allCustomersList: any[] = [];

  allTravelPackagesList: any[] = [];

  displayedColumns: string[] = ['id', 'customerId', 'travelPackageId', 'departureDate', 'bookingStatus'];

  constructor(private formBuilder: FormBuilder, private createBookingService: CreateBookingService, private router: Router) { }

  ngOnInit(): void {
    this.getAllCustomers();
    this.getAllTravelPackages();
    this.getAllBookings();
  }

  createBooking() {
    debugger;
    console.log("createBookingFormGroup ==> " + JSON.stringify(this.formGroup.getRawValue()));
    this.createBookingService.createBooking(this.formGroup.getRawValue()).subscribe((res) => {
      console.log("createBookingAPIResponse ==> " + res);
      this.createBookingAPIResponse = res;
      this.getAllBookings();

      const bookingId = JSON.parse(res).bookingId;

      // Navigate to the payment page with the booking ID parameter.
      this.router.navigate(['/payment', bookingId]);
    }, (error) => {
      console.log("createBookingAPIError ==> " + error);
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
