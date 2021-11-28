package igu.action;

import java.util.List;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;
import Logica.crud.dto.DiagnosticoDto;

public class ListDiagnosticoAction {
	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	private String id;
	
	public ListDiagnosticoAction(String id) {
		this.id=id;
	}

	public List<DiagnosticoDto> execute() throws Exception {

		List<DiagnosticoDto> lista = service.listDiagnostico(id);
		return lista;
		
	}
}
