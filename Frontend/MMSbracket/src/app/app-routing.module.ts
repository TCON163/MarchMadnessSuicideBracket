import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './pages/register/register.component';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full' },
 {path: "home" , component: HomeComponent, children:[
  {path: "t", component:RegisterComponent},
 ]},
 {path: "register", component: RegisterComponent},
 
]

@NgModule({
  
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
