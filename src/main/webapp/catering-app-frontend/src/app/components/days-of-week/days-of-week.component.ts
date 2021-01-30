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

  rowProductData: any;
  rowDaysData: any;

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.getProductGridData();
    this.getDaysOfWeekGridData();
  }

  // getSelectedProductId() {
  //   const selectedNodes = this.agGridProduct.api.getSelectedNodes();
  //   return selectedNodes.map(node => node.data.id)
  // }

  // getSelectedDayName() {
  //   const selectedNodes = this.agGridProduct.api.getSelectedNodes();
  //   return selectedNodes.map(node => node.data.id)
  // }
  //
  addProductToDayName(productId, dayName): void {
    let url = "http://localhost:8080/product/" + productId + "/day/" + dayName;
    this.http.put(url, null).subscribe(
      res => {
        location.reload()
      },
      error => {
        alert("Occurred error during added product to day of weekend")
      }
    )
  }

  private getDaysOfWeekGridData() {
    this.rowDaysData = this.http.get("http://localhost:8080/products/days/all").subscribe(
      x => {
        this.daysData = x;
        Object.values(this.daysData).filter(x => x).forEach(x => {
          this.extractedOneDayWithOneProductOfArrays(x);
        });
      }
    );
  }

  private getProductGridData() {
    this.rowProductData = this.http.get("http://localhost:8080/products/all");
  }

  private extractedOneDayWithOneProductOfArrays(x) {
    let currDay = x.dayName;

    let productId = undefined;
    let productName = '';
    let productDescription = '';
    let productCost = undefined;
    let productKcal = undefined;
    let productMass = undefined;
    let productHeight = undefined;
    let productWeight = undefined;

    if (x.productList.length === 0) {
      this.update(currDay, undefined, '', '',
        undefined, undefined, undefined, undefined,
        undefined)

    } else {
      for (let productModel of x.productList) {
        productId = productModel.id;
        productName = productModel.name;
        productDescription = productModel.description;
        productCost = productModel.cost;
        productKcal = productModel.kcal;
        productMass = productModel.mass;
        productHeight = productModel.height;
        productWeight = productModel.weight;
        this.update(currDay, productId, productName, productDescription, productCost,
          productKcal, productMass, productHeight, productWeight)
      }
    }
  }

  public update(dayName: string, productId: number, productName: string, productDescription: string,
                productCost: number, productKcal: number, productMass: number, productHeight: number,
                productWeight: number): void {
    this.agGridDays.api.updateRowData({
      add: [{
        dayName: dayName,
        id: productId,
        name: productName,
        description: productDescription,
        cost: productCost,
        kcal: productKcal,
        mass: productMass,
        height: productHeight,
        weight: productWeight
      }]
    });
  }

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
    },
    {
      headerName: "Days of week",
      field: "value",
      editable: true,
      cellEditor: 'agRichSelectCellEditor',
      cellEditorParams: {
        values: [
          'Monday',
          'Tuesday',
          'Wednesday',
          'Thursday',
          'Friday',
          'Saturday',
          'Sunday'
        ],
      },
      // minWidth: 470
    }];

  onCellValueChanged(params) {
    let productId = params.data.id;
    let dayValue = params.data.value;
    this.addProductToDayName(productId, dayValue);
  }

  daysColumnDefs = [
    {
      headerName: "Day name",
      field: 'dayName',
      rowGroup: true,
      hide: false,
      maxWidth: 100
    },
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
}
