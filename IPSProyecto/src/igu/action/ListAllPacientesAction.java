package igu.action;

import java.util.List;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;
import Logica.crud.dto.PacienteDto;

public class ListAllPacientesAction {
	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	
	public ListAllPacientesAction() {

	}

	public List<PacienteDto> execute(){

		List<PacienteDto> nombre=null;
		try {
			nombre = service.listAllPacientes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nombre;
		
	}
}
