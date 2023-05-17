import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './home-page/home-page.component';
import { CreateCustomerComponent } from './create-customer/create-customer.component';
import { GetReportComponent } from './get-report/get-report.component';
import { CreateBookingComponent } from './create-booking/create-booking.component';
import { CreateTravelPackageComponent } from './create-travel-package/create-travel-package.component';
import { UpdateBookingComponent } from './update-booking/update-booking.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: "home", component: HomePageComponent },
  { path: "create-customer", component: CreateCustomerComponent },
  { path: "create-travel-package", component: CreateTravelPackageComponent },
  { path: "create-booking", component: CreateBookingComponent },
  { path: "update-booking", component: UpdateBookingComponent },
  { path: "get-report", component: GetReportComponent },
  { path: "**", redirectTo: 'home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
