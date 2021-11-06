package igu.action;

import java.util.List;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;
import Logica.crud.dto.CitaDto;

public class ListAllCitasAction 
{
	private CitaCrudService service = BusinessFactory.forCitaCrudService();

	public List<CitaDto> execute(){

		List<CitaDto> nombre=null;
		try {
			nombre = service.listAllCitas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nombre;
		
	}
}
