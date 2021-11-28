package igu.action;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;
import Logica.crud.dto.CitaDto;

public class ListCitaByIdAction {
	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	private int id;

	public ListCitaByIdAction(int id) {
		this.id = id;
	}

	public CitaDto execute(){

		CitaDto cita =null;
		try {
			cita = service.listCitaById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cita;
		
	}
}
