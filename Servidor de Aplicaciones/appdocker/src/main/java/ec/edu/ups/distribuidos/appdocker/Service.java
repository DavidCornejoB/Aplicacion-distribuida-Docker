package ec.edu.ups.distribuidos.appdocker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import ec.edu.ups.distribuidos.ejb.VehiculoFacade;
import ec.edu.ups.distribuidos.modelo.Vehiculo;

@Path("/service")
@OpenAPIDefinition(info = @Info(title = "Saludos Resource", description = "Servicios que entregar saludos", version = "1.0"))
public class Service {
	
	@EJB
	private VehiculoFacade vehiculoFacade;

	@GET
	@Path("/all")
	@Produces("application/json")
	@Counted(description = "Listar Vehículos", absolute = true)
	@Timed(name = "saludo1-time", description = "Tiempo de procesamiento de saludo 1", unit = MetricUnits.MILLISECONDS, absolute = true)
	@Operation(description = "Invocar a endpoint HelloWorld con respuesta en JSON", summary = "call getHelloWorldJSON")
	@APIResponse(responseCode = "200", description = "Saludo respuesta listar",
				content = @Content(mediaType = MediaType.APPLICATION_JSON,
					schema = @Schema(implementation = String.class)))
	public String getVehiculos() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(vehiculoFacade.findAll());
	}

	@POST
	@Path("/create")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Counted(description = "Crear Vehiculo", absolute = true)
	@Timed(name = "saludo2-time", description = "Tiempo de procesamiento de saludo 2", unit = MetricUnits.MILLISECONDS, absolute = true)
	@Operation(description = "Invocar a endpoint HelloWorld con respuesta en JSON", summary = "call getHelloWorldJSON")
	@APIResponse(responseCode = "200", description = "Saludo respuesta crear",
				content = @Content(mediaType = MediaType.APPLICATION_JSON,
					schema = @Schema(implementation = String.class)))
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
	@Counted(description = "Eliminar Vehiculo", absolute = true)
	@Timed(name = "saludo3-time", description = "Tiempo de procesamiento de saludo 3", unit = MetricUnits.MILLISECONDS, absolute = true)
	@Operation(description = "Invocar a endpoint HelloWorld con respuesta en JSON", summary = "call getHelloWorldJSON")
	@APIResponse(responseCode = "200", description = "Saludo respuesta eliminar",
				content = @Content(mediaType = MediaType.APPLICATION_JSON,
					schema = @Schema(implementation = String.class)))
	public boolean deleteVehiculo(@FormParam("matricula") String matricula){
		Vehiculo vehiculo = vehiculoFacade.find(matricula);
		return vehiculoFacade.remove(vehiculo);
	}
	

}
