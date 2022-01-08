import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './pages/register/register.component';
import { TourneyListComponent } from './pages/tourney-list/tourney-list.component';
import { TourneyComponent } from './pages/tourney/tourney.component';
import { ManageTourneyComponent } from './pages/manage-tourney/manage-tourney.component';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full' },
 {path: "home" , component: HomeComponent, children:[
  {path: "", component:TourneyListComponent},
  {path: "tourney/:tourneyId", component: TourneyComponent},
  {path: "manage/tourney/:tourneyId", component: ManageTourneyComponent}
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
