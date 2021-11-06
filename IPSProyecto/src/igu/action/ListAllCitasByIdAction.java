package igu.action;

import java.util.List;

import Logica.BusinessFactory;

import Logica.crud.CitaCrudService;
import Logica.crud.dto.CitaDto;

public class ListAllCitasByIdAction {
	private CitaCrudService service = BusinessFactory.forCitaCrudService();
	private int id_medico;
	public ListAllCitasByIdAction(int id_medico) {
		this.id_medico=id_medico;
	}
	public List<CitaDto> execute(){

		List<CitaDto> nombre=null;
		try {
			nombre = service.listAllCitasById(id_medico);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nombre;
		
	}
}
