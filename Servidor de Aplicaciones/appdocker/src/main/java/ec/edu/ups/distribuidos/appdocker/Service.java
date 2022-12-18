package ec.edu.ups.distribuidos.appdocker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ec.edu.ups.distribuidos.ejb.VehiculoFacade;
import ec.edu.ups.distribuidos.modelo.Vehiculo;

@Path("/service")
public class Service {
	
	@EJB
	private VehiculoFacade vehiculoFacade;

	@GET
	@Path("/all")
	@Produces("application/json")
	public String getVehiculos() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(vehiculoFacade.findAll());
	}

	@POST
	@Path("/create")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public boolean createVehiculo(@FormParam("matricula") String matricula, @FormParam("marca") String marca,
							 @FormParam("modelo") String modelo, @FormParam("color") String color){
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setMatricula(matricula);
		vehiculo.setMarca(marca);
		vehiculo.setModelo(modelo);
		vehiculo.setColor(color);

		return vehiculoFacade.create(vehiculo);
	}
	
	@POST
	@Path("/delete")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public boolean deleteVehiculo(@FormParam("matricula") String matricula){
		Vehiculo vehiculo = vehiculoFacade.find(matricula);
		return vehiculoFacade.remove(vehiculo);
	}
	

}
