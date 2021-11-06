package igu.action;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;

public class UpdateCausasInHistorialAction {

	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	private String causa;
	private String id;
	private String idPaciente;
	
	public UpdateCausasInHistorialAction(String id, String idPaciente, String causa) {
		this.causa=causa;
		this.id=id;
		this.idPaciente=idPaciente;
	}

	public void execute() {
		try {
			service.updateCausasInHistorial(id, idPaciente, causa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
	}
}
