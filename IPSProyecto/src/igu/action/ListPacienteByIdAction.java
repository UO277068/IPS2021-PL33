package igu.action;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;
import Logica.crud.dto.PacienteDto;

public class ListPacienteByIdAction {
	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	private String id;
	
	public ListPacienteByIdAction(String id) {
		this.id=id;
	}

	public PacienteDto execute() throws Exception {

		PacienteDto nombre = service.listPacienteById(id);
		return nombre;
		
	}
}
