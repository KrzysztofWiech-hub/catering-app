import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule, routingComponent} from './app-routing.module';
import {AppComponent} from './app.component';
import {SidebarComponent} from './components/sidebar/sidebar.component';
import {HomeComponent} from './components/home/home.component';
import {AboutComponent} from "./components/about/about.component";
import {AppServiceComponent} from './components/app-service/app-service.component';
import {AgGridModule} from 'ag-grid-angular';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    HomeComponent,
    AboutComponent,
    routingComponent,
    AppServiceComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AgGridModule.withComponents([])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
