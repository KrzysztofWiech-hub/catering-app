import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AddProductComponent} from "./components/add-project/add-product.component";
import {AboutComponent} from "./components/about/about.component";
import {HomeComponent} from "./components/home/home.component";


const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'add-product', component: AddProductComponent},
  {path: 'about', component: AboutComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

export const routingComponent = [AddProductComponent, AboutComponent, HomeComponent];
