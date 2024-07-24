import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { LayoutComponent } from './pages/layout/layout.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import {RegisterComponent} from "./pages/register/register.component";
import {CloseEventsComponent} from "./pages/close-events/close-events.component";
import {AllEventsComponent} from "./pages/all-events/all-events.component";
import {UserListComponent} from "./pages/user-list/user-list.component";

export const routes: Routes = [
  {
    path: '', redirectTo:'login' , pathMatch:'full'
  },
  {
    path:'login',
    component:LoginComponent
  },
  {
    path:'register',
    component:RegisterComponent
  },
  {
    path:'',
    component:LayoutComponent,
    children:[
      {
        path:'dashboard',
        component:DashboardComponent
      },
      {
        path:'close-events',
        component:CloseEventsComponent
      },
      {
        path:'all-events',
        component:AllEventsComponent
      },
      {
        path:'user-list',
        component:UserListComponent
      }
    ]

  }
];
