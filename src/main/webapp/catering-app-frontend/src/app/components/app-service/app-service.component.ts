import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-app-service',
  templateUrl: './app-service.component.html',
  styleUrls: ['./app-service.component.css']
})
export class AppServiceComponent implements OnInit {

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  rootURL = '/api';

  getAllProducts() {
    return this.http.get(this.rootURL + '/users');
  }

  addProduct(user: any, id: number) {
    user.id = id;
    return this.http.post(this.rootURL + '/user', user);
  }

}
