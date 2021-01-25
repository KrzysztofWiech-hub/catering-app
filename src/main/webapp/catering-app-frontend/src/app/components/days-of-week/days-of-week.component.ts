import {Component, OnInit, ViewChild} from '@angular/core';
import {AgGridAngular} from "ag-grid-angular";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-days-of-week',
  templateUrl: './days-of-week.component.html',
  styleUrls: ['./days-of-week.component.css']
})
export class DaysOfWeekComponent implements OnInit {

  @ViewChild('agGridProduct') agGridProduct: AgGridAngular;
  @ViewChild('agGridDays') agGridDays: AgGridAngular;

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.rowProductData = this.http.get("http://localhost:8080/products/all");
    this.rowDaysData = this.http.get("http://localhost:8080/products/days/all")
  }

  rowProductData: any;
  rowDaysData: any;

  modelProduct: ProductModel = {
    name: '',
    description: '',
    cost: null,
    kcal: null,
    mass: null,
    height: null,
    weight: null
  };

  modelDays: DaysModel = {
    dayName: ''
  };

  getSelectedProductId() {
    const selectedNodes = this.agGridProduct.api.getSelectedNodes();
    return selectedNodes.map(node => node.data.id)
  }

  productColumnDefs = [
    {
      headerName: "Id",
      field: "id",
      sortable: true,
      filter: true,
      maxWidth: 40
    },
    {
      headerName: "Product name",
      field: "name",
      sortable: true,
      filter: true
    },
    {
      headerName: "Product description",
      field: "description",
      minWidth: 470
    },
    {
      headerName: "Price",
      field: "cost",
      sortable: true,
      maxWidth: 100
    },
    {
      headerName: "Kcal",
      field: "kcal",
      sortable: true,
      maxWidth: 100
    },
    {
      headerName: "Mass",
      field: "mass",
      sortable: true,
      maxWidth: 100
    }];

  daysColumnDefs = [
    {
      field: 'country',
      rowGroup: true,
      hide: true,
    },
    {
      field: 'year',
      rowGroup: true,
      hide: true,
    },
    {
      field: 'sport',
      minWidth: 200,
    },
    {
      field: 'athlete',
      minWidth: 200,
    },
    { field: 'gold' },
    { field: 'silver' },
    { field: 'bronze' },
    { field: 'total' },
    { field: 'age' },
    {
      field: 'date',
      minWidth: 140,
    },
  ];


}

export interface ProductModel {
  name: string;
  description: string;
  cost: number;
  kcal: number;
  mass: number;
  height: number;
  weight: number;
}

export interface DaysModel {
  dayName: string;
  // description: string;
  // cost: number;
  // kcal: number;
  // mass: number;
  // height: number;
  // weight: number;
}
