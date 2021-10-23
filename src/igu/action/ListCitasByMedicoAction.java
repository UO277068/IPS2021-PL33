package igu.action;

import java.util.List;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;
import Logica.crud.dto.CitaDto;

public class ListCitasByMedicoAction 
{
    private CitaCrudService service = BusinessFactory.forCitaCrudService();
    
    private String id;
	
	public ListCitasByMedicoAction(String idMedico) 
	{
      this.id=idMedico;
	}

	public List<CitaDto> execute(){

		List<CitaDto> citas =null;
		try {
			citas = service.ListCitasByMedico(this.id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return citas;
		
	}
}
