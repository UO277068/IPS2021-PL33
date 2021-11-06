package igu.action;

import java.util.List;

import Logica.BusinessFactory;
import Logica.crud.CitaCrudService;
import Logica.crud.dto.JornadaDto;

public class ListJornadaLaboralByMedicoAction 
{
	 private CitaCrudService service = BusinessFactory.forCitaCrudService();
	    
	    private String id;
		
		public ListJornadaLaboralByMedicoAction(String idMedico) 
		{
	      this.id=idMedico;
		}

		public List<JornadaDto> execute(){

			List<JornadaDto> jornada =null;
			try {
				jornada = service.listJornadaLaboralByMedico(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return jornada;
			
		}
}
