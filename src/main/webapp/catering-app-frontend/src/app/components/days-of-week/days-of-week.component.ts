import {Component, OnInit, ViewChild} from '@angular/core';
import {AgGridAngular} from "ag-grid-angular";
import {HttpClient} from "@angular/common/http";
import {DaysData} from "./DaysData";

@Component({
  selector: 'app-days-of-week',
  templateUrl: './days-of-week.component.html',
  styleUrls: ['./days-of-week.component.css']
})
export class DaysOfWeekComponent implements OnInit {

  @ViewChild('agGridProduct') agGridProduct: AgGridAngular;
  @ViewChild('agGridDays') agGridDays: AgGridAngular;

  public daysData: DaysData | undefined;
  public rowData: any;

  rowProductData: any;
  rowDaysData: any;

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.rowProductData = this.http.get("http://localhost:8080/products/all");
    this.rowDaysData = this.http.get("http://localhost:8080/products/days/all").subscribe(
      x => {
        this.daysData = x;
        Object.values(this.daysData).filter(x => x).forEach(x => {
          this.extractedOneDayWithOneProductOfArrays(x);
        });
      }
    );
  }

  private extractedOneDayWithOneProductOfArrays(x) {
    let currDay = x.dayName;

    let productName = '';
    let description = '';
    let cost = undefined;

    if (x.productList.length === 0) {
      this.update(currDay, undefined, undefined, undefined)

    } else {
      for (let productModel of x.productList) {
        productName = productModel.name;
        description = productModel.description;
        cost = productModel.cost;
        this.update(currDay, productName, description, cost)
      }
    }
  }

  public update(dayName: string, name: string, desc: string, cost: number): void {
    this.agGridDays.api.updateRowData({
      add: [{
        dayName: dayName,
        make: name,
        model: desc,
        price: cost
      }]
    });
  }

  // getSelectedProductId() {
  //   const selectedNodes = this.agGridProduct.api.getSelectedNodes();
  //   return selectedNodes.map(node => node.data.id)
  // }

  productColumnDefs = [
    {
      headerName: "Id",
      field: "id",
      sortable: true,
      filter: true,
      maxWidth: 60
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
      field: 'dayName',
      rowGroup: true,
      hide: false,
    }
    ,
    {
      field: "make"
    },
    {
      field: 'model',
      // rowGroup: true
    },
    {
      field: "price"
    }
  ];
}
