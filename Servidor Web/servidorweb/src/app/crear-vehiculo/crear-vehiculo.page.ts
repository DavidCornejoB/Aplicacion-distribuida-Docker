import { Component, OnInit } from '@angular/core';
import { AlertController } from '@ionic/angular';
import axios from "axios";

@Component({
  selector: 'app-crear-vehiculo',
  templateUrl: './crear-vehiculo.page.html',
  styleUrls: ['./crear-vehiculo.page.scss'],
})
export class CrearVehiculoPage implements OnInit {

  vehiculo = {
    matricula: "",
    marca: "",
    modelo: "",
    color: ""
  }

  constructor(
    public alertController: AlertController,
  ) { }

  ngOnInit() {
  }

  async alertaGuardarVehiculo() {
    const alert = await this.alertController.create({
      header: "Insertar Nuevo Vehículo",
      message: "Está seguro de que desea insertar un nuevo vehículo con esos datos?",
      buttons: [
        {
          text: 'Si',
          handler: () => {//SI
            console.log("Vehículo insertado");
            this.crearVehiculo();
          }//SI
        },
        {
          text: 'No',
          handler: () => {
            console.log("No se inserta vehículo");
          }
        }
    ]
    });
    await alert.present()
    let result = await alert.onDidDismiss();
    console.log(result);
  }

  async crearVehiculo(){
    console.log("Matricula: " + this.vehiculo.matricula + ", Marca: " + this.vehiculo.marca + ", Modelo: " + this.vehiculo.modelo + ", Color: " + this.vehiculo.color);
    
    let response = await axios.post('/appdocker/rs/service/create', new URLSearchParams({
      matricula: this.vehiculo.matricula,
      marca: this.vehiculo.marca,
      modelo: this.vehiculo.modelo,
      color: this.vehiculo.color,
    }));
    console.log(response.data);
  }

}
