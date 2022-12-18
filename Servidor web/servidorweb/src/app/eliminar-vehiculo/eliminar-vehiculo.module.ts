import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { EliminarVehiculoPageRoutingModule } from './eliminar-vehiculo-routing.module';

import { EliminarVehiculoPage } from './eliminar-vehiculo.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    EliminarVehiculoPageRoutingModule
  ],
  declarations: [EliminarVehiculoPage]
})
export class EliminarVehiculoPageModule {}
