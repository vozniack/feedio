import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'home',
    loadComponent: () => import('./modules/home/home.component').then(m => m.HomeComponent),
    canActivate: []
  },
  {
    path: 'login',
    loadComponent: () => import('./core/login/login.component').then(m => m.LoginComponent),
  },
  {
    path: '**',
    redirectTo: 'home',
  },
];
