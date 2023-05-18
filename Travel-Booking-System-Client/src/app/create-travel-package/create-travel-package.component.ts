import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup,Validators } from '@angular/forms';
import { CreateTravelPackageService } from './create-travel-package.service';

@Component({
  selector: 'app-create-travel-package',
  templateUrl: './create-travel-package.component.html',
  styleUrls: ['./create-travel-package.component.scss']
})
export class CreateTravelPackageComponent implements OnInit {

  formGroup: FormGroup = this.formBuilder.group({
  
    'destinationCity': [null, []],
    'destinationCountry': [null, []],
    'noOfDays': [0, []],
    'noOfNights': [0, []],
    'hotelName': [null, []],
    'price': [0, []],
    'activitiesIncluded': [null, []],
  });


  createPackageAPIResponse: any;

  allPackagesList: any[] = [];

  displayedColumns: string[] = ['id', 'destinationCity', 'destinationCountry', 'hotelName', 'noOfDays','noOfNights', 'price','activitiesIncluded'];

  constructor(private formBuilder: FormBuilder, private packageService: CreateTravelPackageService) { }

  ngOnInit(): void {
    this.getAllPackages();
  }

  createPackage() {
    let payload = this.formGroup.getRawValue()
    payload.activitiesIncluded = payload.activitiesIncluded.split(',');
    this.packageService.createPackage(payload).subscribe((res) => {
      this.createPackageAPIResponse = res;
      console.log("createPackageAPIResponse ==> " + this.createPackageAPIResponse);
      this.getAllPackages();
    });
  }

  getAllPackages() {
    debugger;
    this.packageService.getAllPackages().subscribe((res) => {
      this.allPackagesList = res;
      console.log("getAllPackages ==> " + res);
    });
  }




}
