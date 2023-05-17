import { Component, OnInit } from '@angular/core';
import { GetReportService } from './get-report.service';

@Component({
  selector: 'app-get-report',
  templateUrl: './get-report.component.html',
  styleUrls: ['./get-report.component.scss']
})
export class GetReportComponent implements OnInit {

  reportData: any[] = [];

  displayedColumns: string[] = ['packageid', 'destination', 'totalNumberOfBookings', 'totalRevenueGenerated'];

  constructor(private getReportService: GetReportService) { }

  ngOnInit(): void {
    this.getReport();
  }

  getReport() {
    this.getReportService.getReport().subscribe((data: any) => {
      this.reportData = data;
      console.log("getReport ==> " + data);
    });
  }

}
