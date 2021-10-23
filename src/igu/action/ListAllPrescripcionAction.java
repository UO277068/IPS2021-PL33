package igu.action;

import java.util.List;

import Logica.BusinessFactory;

import Logica.crud.CitaCrudService;
import Logica.crud.dto.CitaDto;

public class ListAllPrescripcionAction {

private CitaCrudService service = BusinessFactory.forCitaCrudService();
	
	public ListAllPrescripcionAction() {

	}

	public List<CitaDto> execute(){

		List<CitaDto> citas =null;
		try {
			citas = service.listAllPrescripcion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return citas;
		
	}
}
