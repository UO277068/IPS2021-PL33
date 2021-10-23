package igu.action;

import java.util.List;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;
import Logica.crud.dto.MedicoDto;

public class ListAllMedicosAction 
{
private CitaCrudService service = BusinessFactory.forCitaCrudService();
	
	public ListAllMedicosAction() {

	}

	public List<MedicoDto> execute(){

		List<MedicoDto> medicos =null;
		try {
			medicos = service.listAllMedicos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return medicos;
		
	}
}
