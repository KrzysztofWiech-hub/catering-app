import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AgGridAngular} from "ag-grid-angular";

@Component({
  selector: 'app-add-project',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  @ViewChild('agGrid') agGrid: AgGridAngular;

  model: ProductModel = {
    name: '',
    description: '',
    cost: null,
    kcal: null,
    mass: null,
    height: null,
    weight: null
  };

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.rowData = this.http.get("http://localhost:8080/products/all")
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

  deleteProduct(): void {
    let selectedProductId = this.getSelectedProductId();
    let url = "http://localhost:8080/product/" + selectedProductId;
    this.http.delete(url).subscribe(
      res => {
        location.reload()
      },
      error => {
        alert("Occurred error during deleting product")
      }
    )
  }

  getSelectedProductId() {
    const selectedNodes = this.agGrid.api.getSelectedNodes();
    return selectedNodes.map(node => node.data.id)
  }


  columnDefs = [
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

  rowData: any;

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
