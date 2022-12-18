import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EliminarVehiculoPage } from './eliminar-vehiculo.page';

const routes: Routes = [
  {
    path: '',
    component: EliminarVehiculoPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EliminarVehiculoPageRoutingModule {}
