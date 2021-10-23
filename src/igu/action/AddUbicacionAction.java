package igu.action;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;

public class AddUbicacionAction {

	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	private String id;
	private String ubicacion;
	
	public AddUbicacionAction(String id, String ubicacion) {
		this.id=id;
		this.ubicacion=ubicacion;
	}

	public void execute() throws Exception {

		service.addUbicacion(id, ubicacion);
		
	}
}
