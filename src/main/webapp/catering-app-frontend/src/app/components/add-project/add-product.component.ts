import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-add-project',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  model: ProductModel = {
    name: '',
    description: '',
    cost: 0,
    kcal: 0,
    mass: 0,
    height: 0,
    weight: 0
  };

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
  }

  createProduct(): void {
    let url = "http://localhost:8080/product";
    this.http.post(url, this.model).subscribe(
      res => {
        location.reload()
      },
      error => {
        alert("Occurred error during creating product")
      }
    )
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

export interface ProductModel {
  name: string;
  description: string;
  cost: number;
  kcal: number;
  mass: number;
  height: number;
  weight: number;
}
