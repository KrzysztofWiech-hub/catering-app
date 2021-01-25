import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AddProductComponent} from "./components/add-product/add-product.component";
import {AboutComponent} from "./components/about/about.component";
import {HomeComponent} from "./components/home/home.component";
import {DaysOfWeekComponent} from "./components/days-of-week/days-of-week.component";


const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'add-product', component: AddProductComponent},
  {path: 'days-of-week', component: DaysOfWeekComponent},
  {path: 'about', component: AboutComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

export const routingComponent = [HomeComponent, AddProductComponent, DaysOfWeekComponent, AboutComponent];
