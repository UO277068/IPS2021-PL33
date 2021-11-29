package igu.action;

import java.util.List;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;
import Logica.crud.dto.DiagnosticoDto;

public class ListAllDiagnosticosAction {
	private CitaCrudService service = BusinessFactory.forCitaCrudService();

	public List<DiagnosticoDto> execute(){

		List<DiagnosticoDto> nombre=null;
		try {
			nombre = service.listAllDiagnosticos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nombre;
		
	}
}
