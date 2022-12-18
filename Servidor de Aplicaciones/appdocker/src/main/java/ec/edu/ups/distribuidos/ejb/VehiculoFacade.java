package ec.edu.ups.distribuidos.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.distribuidos.modelo.Vehiculo;

@Stateless
public class VehiculoFacade extends AbstractFacade<Vehiculo>{
	
	@PersistenceContext(unitName = "appdockerPersistenceUnit")
	private EntityManager entityManager;
	
	public VehiculoFacade() {
		super(Vehiculo.class);
	}
	
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}


}
