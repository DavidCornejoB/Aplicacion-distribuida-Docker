import { Component, OnInit } from '@angular/core';
import { AlertController } from '@ionic/angular';
import axios from "axios";

@Component({
  selector: 'app-listar-vehiculos',
  templateUrl: './listar-vehiculos.page.html',
  styleUrls: ['./listar-vehiculos.page.scss'],
})
export class ListarVehiculosPage implements OnInit {

  vehiculo = {
    matricula: "",
    marca: "",
    modelo: "",
    color: ""
  }

  vehiculos: any = [];
  searchedCar: any;

  constructor(
    public alertController: AlertController
  ) { }

  ngOnInit() {
    this.getCars();
  }

  async getCars(){
    let response = await axios.get('/appdocker/rs/service/all');
    for(let vehiculo of response.data){
      console.log("Matricula: " + vehiculo.matricula + ", Marca: " + vehiculo.marca + ", Modelo: " + vehiculo.modelo + ", Color: " + vehiculo.color);
      this.vehiculos = response.data;
      this.searchedCar = this.vehiculos;

    }
  }

}
