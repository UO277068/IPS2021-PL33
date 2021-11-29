package igu.action;

import java.util.List;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;
import Logica.crud.dto.VacunaDto;

public class ListVacunaByIdAction {
	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	String id;
	public ListVacunaByIdAction(String id) {
		this.id=id;
	}
	public List<VacunaDto> execute(){

		List<VacunaDto> nombre=null;
		try {
			nombre = service.listVacunaById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nombre;
		
	}
}
