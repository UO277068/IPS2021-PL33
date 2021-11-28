package igu.action;

import java.util.List;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;
import Logica.crud.dto.DiagnosticoDto;

public class ListDiagnosticoByIdAction {
	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	String id;
	public ListDiagnosticoByIdAction(String id) {
		this.id=id;
	}
	public List<DiagnosticoDto> execute(){

		List<DiagnosticoDto> nombre=null;
		try {
			nombre = service.listDiagnosticoById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nombre;
		
	}
}
