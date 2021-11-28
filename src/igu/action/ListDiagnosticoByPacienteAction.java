package igu.action;

import java.util.List;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;
import Logica.crud.dto.DiagnosticoDto;

public class ListDiagnosticoByPacienteAction {
	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	private String id;
	
	public ListDiagnosticoByPacienteAction(String id) {
		this.id=id;
	}

	public List<DiagnosticoDto> execute() throws Exception {

		List<DiagnosticoDto> lista = service.listDiagnosticoByPaciente(id);
		return lista;
		
	}
}
