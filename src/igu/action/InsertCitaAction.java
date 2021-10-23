package igu.action;


import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;
import Logica.crud.dto.CitaDto;

public class InsertCitaAction
{
 private CitaCrudService service = BusinessFactory.forCitaCrudService();
 private CitaDto cita;
	
	public InsertCitaAction(CitaDto dto) 
	{
      this.cita=dto;
	}

	public void execute(){

		try {
			service.InsertCita(cita);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
