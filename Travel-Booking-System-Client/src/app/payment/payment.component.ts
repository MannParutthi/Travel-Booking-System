import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { CreateBookingService } from '../create-booking/create-booking.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss']
})
export class PaymentComponent implements OnInit {
  bookingId!: string;

  constructor(private bookingService: CreateBookingService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.bookingId = params.get('bookingId') as string;
    });
  }

  confirmBooking() {
    this.bookingService.confirmBooking(this.bookingId).subscribe(
      (res) => {
        console.log("Booking confirmed for " + this.bookingId);
        this.router.navigate(['/create-booking']);
      },
      (error) => {
        console.log("Error confirming booking: " + error);
      }
    );
    console.log("booking confirmed for "+this.bookingId)
    this.router.navigate(['/create-booking']);
  }
}
