package igu.action;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;

public class UpdatePrescripcionInHistorialAction {

	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	private String prescripcion;
	private String id;
	
	public UpdatePrescripcionInHistorialAction(String id, String prescripcion) {
		this.prescripcion=prescripcion;
		this.id=id;
	}

	public void execute() {
		try {
			service.updatePrescripcionInHistorial(id, prescripcion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
	}
}
