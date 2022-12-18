import { Component, OnInit } from '@angular/core';
import axios from "axios";

@Component({
  selector: 'app-eliminar-vehiculo',
  templateUrl: './eliminar-vehiculo.page.html',
  styleUrls: ['./eliminar-vehiculo.page.scss'],
})
export class EliminarVehiculoPage implements OnInit {

  vehiculo = {
    matricula: "",
    marca: "",
    modelo: "",
    color: ""
  }

  vehiculos = [];
  searchedCar: any;

  constructor() { }

  ngOnInit() {
    this.getCars();
  }

  /**
   * FUNCIÓN PARA OBTENER TODOS LOS VEHÍCULOS INGRESADOS
   */
  async getCars(){
    let response = await axios.get('/appdocker/rs/service/all');
    for(let vehiculo of response.data){
      console.log("Matricula: " + vehiculo.matricula + ", Marca: " + vehiculo.marca + ", Modelo: " + vehiculo.modelo + ", Color: " + vehiculo.color);
      this.vehiculos = response.data;
      this.searchedCar = this.vehiculos;
    }
  }

  /**
   * FUNCIÓN PARA ELIMINAR UN VEHÍCULO
   * @param matricula CORRESPONDE A LA MATRÍCULA CON LA QUE SE BUSCARÁ AL VEHICULO A ELIMINAR
   * EN EL BACKEND
   */
  async eliminarVehiculo(matricula: any){
    let response = await axios.post('/appdocker/rs/service/delete', new URLSearchParams({
      matricula: matricula
    }));
    console.log(response.data);
  }

}
