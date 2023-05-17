import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UpdateBookingService } from './update-booking.service';

@Component({
  selector: 'app-update-booking',
  templateUrl: './update-booking.component.html',
  styleUrls: ['./update-booking.component.scss']
})
export class UpdateBookingComponent implements OnInit {

  formGroup: FormGroup = this.formBuilder.group({
    'id': [0, []],
    'customerId': [null, []],
    'travelPackageId': [null, []],
    'departureDate': [null, []],
    'bookingStatus': [null, []],
  });

  updateBookingAPIResponse: any;

  allBookingsList: any[] = [];

  displayedColumns: string[] = ['id', 'customerId', 'travelPackageId', 'departureDate', 'bookingStatus'];

  constructor(private formBuilder: FormBuilder, private updateBookingService: UpdateBookingService) { }

  ngOnInit(): void {
    this.getAllBookings();
  }

  updateBooking() {
    console.log("createBookingFormGroup ==> " + JSON.stringify(this.formGroup.getRawValue()));
    this.updateBookingService.updateBooking(this.formGroup.getRawValue()).subscribe((res) => {
      console.log("updateBookingAPIResponse ==> " + res);
      this.updateBookingAPIResponse = res;
      this.getAllBookings();
    });
  }

  getAllBookings() {
    this.updateBookingService.getAllBookings().subscribe((res) => {
      this.allBookingsList = res;
      console.log("getAllBookings ==> " + res);
    });
  }

}
