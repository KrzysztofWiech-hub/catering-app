import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-add-project',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {

  }

  columnDefs = [
    {
      headerName: "Make",
      field: "make"
    },
    {
      headerName: "Model",
      field: "model"
    },
    {
      headerName: "Price",
      field: "price"
    }];

  rowData = [
    {make: "Toyota", model: "Celica", price: 35000},
    {make: "Ford", model: "Mondeo", price: 32000},
    {make: "Porsche", model: "Boxter", price: 72000}
  ];

}
