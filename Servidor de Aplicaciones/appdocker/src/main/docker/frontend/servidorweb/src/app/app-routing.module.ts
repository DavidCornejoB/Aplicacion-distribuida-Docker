import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'home',
    loadChildren: () => import('./home/home.module').then( m => m.HomePageModule)
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'crear-vehiculo',
    loadChildren: () => import('./crear-vehiculo/crear-vehiculo.module').then( m => m.CrearVehiculoPageModule)
  },
  {
    path: 'listar-vehiculos',
    loadChildren: () => import('./listar-vehiculos/listar-vehiculos.module').then( m => m.ListarVehiculosPageModule)
  },
  {
    path: 'eliminar-vehiculo',
    loadChildren: () => import('./eliminar-vehiculo/eliminar-vehiculo.module').then( m => m.EliminarVehiculoPageModule)
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
